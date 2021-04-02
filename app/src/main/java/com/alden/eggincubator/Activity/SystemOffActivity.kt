package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivitySystemOffBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "SystemOffActivity"
class SystemOffActivity : AppCompatActivity() {
    lateinit var binding: ActivitySystemOffBinding
    lateinit var reflistener : ValueEventListener
    val fbdb = FirebaseDatabase.getInstance()
    val ref = fbdb.getReference("SettingData")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemOffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOnSystem.setOnClickListener {
            ref.child("isShutdown").setValue(0).addOnSuccessListener {
//                startActivity(Intent(this, ParentActivity::class.java))
//                finish()
            }
        }

        reflistener = ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var x = snapshot.child("isShutdown").value
                var y: Long = 0
                Log.d(TAG, "onDataChange sysOffActivity: $x")
                if (x == y) {
                    getFinish()
//                    finish()
                    Log.d(TAG, "finished: ")
                } else {
                    Log.d(TAG, "unfinished: ")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //nope
            }
        })


    }

    private fun getFinish(){
        startActivity(Intent(this, ParentActivity::class.java))
        finish()
    }

    override fun onBackPressed() {

    }

    override fun onDestroy() {
        super.onDestroy()
        ref.removeEventListener(reflistener)
    }
}