package com.alden.eggincubator.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.FragmentSettingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "SettingFragment"
class SettingFragment : Fragment() {
    val fbdb = FirebaseDatabase.getInstance()
    var lampStatus: Boolean = false
    lateinit var binding: FragmentSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLampStatus()

        binding.cvReboot.setOnClickListener {

        }

        binding.cvShutDown.setOnClickListener {

        }

        binding.cvLamp.setOnClickListener {
            if(lampStatus){
                popUpUniverse(
                    actionToast(),
                    "Yakin ingin Mematikan Lampu?",
                    "Kalo lampunya mati nanti kedinginan lho"
                )
            }else {
                popUpUniverse(
                    changeLampStatus(false),
                    "Hidupkan Lampu?",
                    "Hidupkan lampu untuk menyinari duniaku mwehehehe"
                )
            }
        }
    }

    private fun initLampStatus() {
        val lampRef = fbdb.getReference("FirebaseIOT")
        lampRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var data = snapshot.child("lampu1").value.toString()
                setLampStatus(data)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "onCancelled: ", error.toException())
            }
        })
    }

    private fun setLampStatus(data: String) {
        lampStatus = data == "1"
        if (lampStatus){
            binding.tvLampStatus.text ="Matikan Lampu"
        }else{
            binding.tvLampStatus.text ="Hidupkan Lampu"
        }
    }


    private fun popUpUniverse(action: Unit, title: String, subTitle: String) {
        //initialize View
        val vView: View = LayoutInflater.from(context).inflate(R.layout.popup_reboot, null, false)
        val btnOk: Button = vView.findViewById(R.id.btnPopUpOk)
        val btnCancel: Button = vView.findViewById(R.id.btnPopUpCancel)
        val tvTitle: TextView = vView.findViewById(R.id.tvPopUpTitle)
        val tvSubTitle: TextView = vView.findViewById(R.id.tvPopUpSubTitle)

        //initialize text
        tvTitle.text = title
        tvSubTitle.text = subTitle

        val universeAlert = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .setView(vView)
            .setCancelable(false)
        val universeAlertDialog = universeAlert.show()

        btnOk.setOnClickListener {
            action
            universeAlertDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            Toast.makeText(context, "Cancel clicked", Toast.LENGTH_SHORT).show()
            universeAlertDialog.cancel()
        }
    }



    private fun changeLampStatus(wantDead : Boolean){
        if (wantDead){
            fbdb.getReference("FirebaseIOT").child("lampu1").setValue("0")
        }else{
            fbdb.getReference("FirebaseIOT").child("lampu1").setValue("1")
        }


    }



    private fun actionToast() {
        Toast.makeText(context, "here ok sir", Toast.LENGTH_SHORT).show()
    }

}
