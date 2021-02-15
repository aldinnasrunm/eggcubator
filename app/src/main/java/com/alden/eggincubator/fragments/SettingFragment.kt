package com.alden.eggincubator.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.FragmentSettingBinding
import com.google.firebase.database.FirebaseDatabase
import kotlin.contracts.contract


class SettingFragment : Fragment() {
    val fbdb = FirebaseDatabase.getInstance()
    lateinit var binding: FragmentSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.cvReboot.setOnClickListener {
            popUpReboot()
        }
        binding.cvShutDown.setOnClickListener {
            popUpShutdown()
        }
    }

    private fun popUpShutdown() {

    }

    private fun popUpReboot() {
        val vReboot : View = LayoutInflater.from(context).inflate(R.layout.popup_reboot,null, false)
        val btnOk : Button = vReboot.findViewById(R.id.btnRebootOk)
        val btnCancel : Button = vReboot.findViewById(R.id.btnRebootCancel)
        val rebootDialog = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .setView(vReboot)
            .setCancelable(false)
        val rebootAlertDialog = rebootDialog.show()
        btnOk.setOnClickListener {
            Toast.makeText(context, "Ok clicked", Toast.LENGTH_SHORT).show()
            rebootAlertDialog.cancel()
        }
        btnCancel.setOnClickListener {
            Toast.makeText(context, "Cancel clicked", Toast.LENGTH_SHORT).show()
            rebootAlertDialog.cancel()
        }
    }
}