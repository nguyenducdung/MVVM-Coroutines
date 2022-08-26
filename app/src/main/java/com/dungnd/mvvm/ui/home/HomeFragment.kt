package com.dungnd.mvvm.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentHomeBinding
import com.dungnd.mvvm.ui.base.BaseFragment
import com.dungnd.mvvm.util.setOnSingClickListener

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val adapter = PhotoListAdapter()

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.

                Toast.makeText(requireContext(), "Bạn đã cấp quyền", Toast.LENGTH_LONG).show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(requireContext(), "Bạn đã từ chối quyền", Toast.LENGTH_LONG).show()
            }
        }

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
            binding.tvName.text = it
        }
        viewModel.photoList.observe(this) {
            binding.tvName.text = it.size.toString()
            adapter.photoList = it
            adapter.notifyDataSetChanged()
        }
        binding.btnGetContacts.setOnSingClickListener {
            getContacts()
        }

        viewModel.phoneList.observe(this) {
            Log.e("phone_number", it?.toString() ?:"")
        }
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

    private fun getContacts() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) -> {
                // You can use the API that requires the permission.
                //Tạo 1 uri để lấy danh bạ, lấy đc id và tên trong danh bạ
                val uri = Uri.parse("content://contacts/people")
                //Tạo 1 cái cursor (1 cái dùng để query lấy dữ liệu 1 cách thủ công)
                val cursor = requireActivity().contentResolver.query(uri, null, null, null, null)
                if ((cursor?.count ?: 0) > 0) {
                    //Bắt đầu
                    cursor?.moveToFirst()

                    while (cursor?.isAfterLast != true) {
                        val id = cursor?.getColumnIndex(
                            ContactsContract.Contacts._ID)?.let { cursor.getString(it) }

                        getPhone(id ?: "")

                        val name = cursor?.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME)?.let { cursor.getString(it) }

                        Toast.makeText(requireContext(), "$name _ $id _", Toast.LENGTH_LONG).show()

                        cursor?.moveToNext()
                    }

                    //Kết thúc
                    cursor.close()
                }
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun getPhone(id: String) {
        val cursor = activity?.contentResolver?.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)

        //Bắt đầu
        cursor?.moveToFirst()

        while (cursor?.isAfterLast != true) {
            val phone = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)?.let { cursor.getString(it) }
            Log.e("phone_number", phone ?:"")

            cursor?.moveToNext()
        }

        //Kết thúc
        cursor.close()
    }
}