package com.alden.eggincubator.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alden.eggincubator.adapter.KonfirmasiAdapter
import com.alden.eggincubator.databinding.ActivityPrepareBinding


class PrepareActivity : AppCompatActivity() {
    lateinit var binding : ActivityPrepareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrepareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrayKeterangan = arrayListOf(
            "Pastikan steker tercolokan ke stop kontak",
            "Pastikan wadah telur benar",
            "Pastikan air sudah terisi",
            "Disarankan Membaca Do'a"
        )
        val mAdapter = KonfirmasiAdapter(arrayKeterangan)
        binding.rvKonfirmasi.apply {
            layoutManager = LinearLayoutManager(this@PrepareActivity)
            adapter = mAdapter
        }
        binding.btnPrepareNext.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }

    }
}