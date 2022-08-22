package com.dungnd.mvvm.ui.login

import android.app.Activity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dungnd.mvvm.R
import com.dungnd.mvvm.databinding.FragmentLoginBinding
import com.dungnd.mvvm.ui.base.BaseFragment
import com.dungnd.mvvm.util.setOnSingClickListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleGoogleSignInResult(task)
            }
        }

    override fun layoutRes(): Int = R.layout.fragment_login

    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    //hàm chạy lần đầu khi view được khởi tạo xong
    override fun initView() {
        //hàm logout
        Firebase.auth.signOut()
        setupGoogleStuff()
        binding.btnLoginGoogle.setOnSingClickListener {
            val intent = mGoogleSignInClient?.signInIntent
            startForResult.launch(intent)
        }
        binding.btnLogin.setOnSingClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.login(email, pass)
            }
        }
        viewModel.loginSuccess.observe(this) {
            if (it == true) {
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            }
        }
        binding.abvActionBar.setTitleColor(R.color.purple_500)
        binding.abvActionBar.setOnClickImageLeft {
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Tôi vừa bấm back", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            viewModel.addUser(account)
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            Toast.makeText(requireContext(), account.toString(), Toast.LENGTH_LONG).show()
        } catch (e: ApiException) {
            e.printStackTrace()
            //showDialog báo lỗi
        }
    }

    private fun setupGoogleStuff() {
        val clientId = "709829683581-h48nnq13fvj9htf4opu87uojag8qri1k.apps.googleusercontent.com"
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }
}