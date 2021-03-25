package com.alden.eggincubator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alden.eggincubator.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


private const val TAG = "LoginFragment"

class LoginFragment(transactionFragment: Unit) : Fragment() {
    val tFragment = transactionFragment
    val mAuth = FirebaseAuth.getInstance()
    lateinit var binding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnToRegister.setOnClickListener {
            tFragment
        }

    }
    
    
//    private fun createUser(){
//        mAuth.signInWithCustomToken(mCustomToken)
//            .addOnCompleteListener(this,
//                OnCompleteListener<AuthResult?> { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
////                        Log.d(TAG, "signInWithCustomToken:success")
//                        val user = mAuth.currentUser
////                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
////                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
//                        Toast.makeText(
//                            context, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
////                        updateUI(null)
//                    }
//                })
//    }
}