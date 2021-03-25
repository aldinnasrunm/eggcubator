package com.alden.eggincubator.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivityLoginBinding
import com.alden.eggincubator.fragments.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    val fbdb = FirebaseDatabase.getInstance()
    val settingRef = fbdb.getReference("SettingData")
    lateinit var settingListener: ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            cekPassword(binding.etId.text.toString())

        }

    }

    private fun cekPassword(id: String) {
        if (id.equals("3ggcubat0r")) {
            isReady()
        //            startActivity(Intent(this, ParentActivity::class.java))
            finish()
        } else {
            binding.etId.setError("ID yang kamu masukkan salah :(")
        }
    }



    private fun isReady() {


            settingListener = settingRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val isReset = snapshot.child("isReset").value.toString()
                    val isStart = snapshot.child("isStart").value.toString()
                    val stateReset = isReset == "1"
                    val stateStart = isStart == "1"
                    actionStartUp(stateReset, stateStart)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

    }

    private fun actionStartUp(stateReset: Boolean, stateStart: Boolean) {
        if (!stateReset && stateStart) {
//            Toast.makeText(this, "!reset, start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ParentActivity::class.java))
        } else if (stateReset && stateStart) {
//            Toast.makeText(this, "!reset, !start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        } else if (stateReset && !stateStart) {
//            Toast.makeText(this, "!reset, !start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        } else {
//            Toast.makeText(this, "reset, start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

}