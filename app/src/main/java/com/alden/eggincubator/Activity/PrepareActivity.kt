package com.alden.eggincubator.Activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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
            "Pastikan air sudah terisi"
        )

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            arrayKeterangan
        )
        binding.lvKonfirmasi.adapter = arrayAdapter

    }
}