package com.dungnd.mvvm.ui.home

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dungnd.mvvm.R
import com.dungnd.mvvm.data.remote.model.Product
import com.dungnd.mvvm.databinding.FragmentHomeBinding
import com.dungnd.mvvm.ui.base.BaseActivity
import com.dungnd.mvvm.ui.base.BaseFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import org.json.JSONObject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val adapter = PhotoListAdapter()

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        adapter.onPhotoClick = {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf(
                    Pair("key", it?.title)
                )
            )
        }
        binding.rcvData.adapter = adapter
        viewModel.name.observe(this) {
//            binding.tvName.text = it
        }
        viewModel.photoList.observe(this) {
//            binding.tvName.text = it.size.toString()
            adapter.photoList = it
            adapter.notifyDataSetChanged()
        }

        //Lắng nghe khi dữ liệu thay đổi(hoặc có dữ liệu mới từ api)
        viewModel.productList.observe(this) {
            binding.tvName.text = it?.get(0)?.title ?:""
        }

        viewModel.isLoading.observe(this) {
            if (it == true) {
                (activity as? BaseActivity<*, *>)?.showLoading()
            } else {
                (activity as? BaseActivity<*, *>)?.hiddenLoading()
            }
        }
        initRemoteConfig()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val savedRecyclerLayoutState =
            savedInstanceState?.getParcelable("BUNDLE_RECYCLER_LAYOUT") as? Parcelable
        if (savedRecyclerLayoutState != null) {
            binding.rcvData.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            "BUNDLE_RECYCLER_LAYOUT",
            binding.rcvData.layoutManager?.onSaveInstanceState()
        )
    }

    private fun initRealtimeDatabase() {
        val database = FirebaseDatabase.getInstance("https://mvvm-coroutines-88636-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRef = database.getReference("category")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               //khi data trên firebase có sự thay đổi thì sẽ nhảy vào hàm này
                val data = snapshot.value as? HashMap<String, Any?>

                val data1 = data?.get("01") as? HashMap<String, Any>
                val name = data1?.get("name") as String
                val soLuong = data1?.get("so_luong") as Long
                val data2 = data?.get("02")

                Toast.makeText(requireContext(), "$name $soLuong $data2", Toast.LENGTH_LONG).show()

            }

            override fun onCancelled(error: DatabaseError) {
                //khi firebase lỗi sẽ nhảy vào đây

                Log.e("ahfjahjfasdf", error.toString())
            }

        })
    }

    private fun initRemoteConfig() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            val data = remoteConfig.getString("key_2")
            val product = Gson().fromJson(data, Product::class.java)
            Toast.makeText(requireContext(), "${product.name}", Toast.LENGTH_LONG).show()

        }
    }
}