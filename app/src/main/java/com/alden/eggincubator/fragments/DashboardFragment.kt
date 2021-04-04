package com.alden.eggincubator.fragments

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.FragmentDashboardBinding
import com.alden.eggincubator.models.RTDBDataClass
import com.google.firebase.database.*
import java.time.Duration
import java.time.LocalDateTime

private const val TAG = "DashboardFragment"

class DashboardFragment : Fragment() {
    val fbdb = FirebaseDatabase.getInstance()
    lateinit var refListener: ValueEventListener
    val refEgg = fbdb.getReference("EggData")
    lateinit var eggListener: ValueEventListener
    lateinit var ref: DatabaseReference
    lateinit var mightyDate: LocalDateTime
    lateinit var waterDate: LocalDateTime
    var estimateDay: Int = 0
    lateinit var binding: FragmentDashboardBinding
    val waterInterfal: Long = 4
    var needRun = true
    var needPopUp = true

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                Log.d(TAG, "handleOnBackPressed: dashboard fragment activity ended")
//                activity?.finish()
//            }
//        })
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isAnimationVisible(true)
        ref = fbdb.getReference("FirebaseIOT")
        getDate()
        initData()


    }

    fun getDate() {
        refEgg.child("day").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val x = snapshot.getValue().toString()
                estimateDay = x.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun initData() {


        refListener = ref.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                var dataH = snapshot.child("humidity").value.toString()
                var dataT = snapshot.child("temperature").value.toString()
                var dataL = snapshot.child("lampu1").value.toString()
                var dataD = snapshot.child("mightyDay").value.toString()
                var dataW = snapshot.child("waterDay").value.toString()
                var dataN = snapshot.child("eggName").value.toString()


                var rtdbDataClass = RTDBDataClass(
                    dataT,
                    dataH,
                    dataL,
                    dataD,
                    dataW,
                    dataN
                )
                initView(rtdbDataClass)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(data: RTDBDataClass) {
        val current: LocalDateTime = LocalDateTime.now()
        mightyDate = LocalDateTime.parse(data.mightyDay)
        waterDate = LocalDateTime.parse(data.waterDay)
        var exceededDay: String = Duration.between(mightyDate, current).toDays().toString()
        var exceededWater: String = Duration.between(current, waterDate).toDays().toString()
        val remainDay = estimateDay - exceededDay.toInt()
        var remainDayWater = exceededWater.toInt()

        if (remainDay < 1) {
            binding.tvShowRemainDay.text = "Telur kamu telah menetas"
        } else {

            binding.tvShowRemainDay.text = "$remainDay hari lagi telur\nkamu akan\nmenetas "
        }

        binding.tvTemperatureStatus.text = data.temperature + "°C"
        binding.tvKelembabanStatus.text = data.humidity + "%"
        binding.tvInkubasiStatus.text = "$exceededDay Hari"
        binding.tvInkubasiSub.text = "Telur telah diinkubasi $exceededDay hari"
        binding.tvEggName.text = "Jenis Telur : ${data.eggName}"

        if (remainDayWater > 0 ) {
            binding.tvAirStatus.text = "$exceededWater hari"
            binding.tvAirSub.text = "Ganti air dalam $exceededWater hari"
        } else {
            if (needPopUp){
                needPopUp = !needPopUp
                popUpUniverse(
                    "Ganti Air",
                    "Apakah Kamu sudah mengganti air?"
                )
            }
        }

        if (remainDay < 1 && needRun) {
            needRun = !needRun
            popUpCrackEgg()
        }

        isAnimationVisible(false)
    }

    private fun popUpCrackEgg() {
        val vView: View = LayoutInflater.from(context).inflate(R.layout.popup_crackegg, null, false)
        val btnCancel: Button = vView.findViewById(R.id.btnPopUpCancel)

        val uniAlert = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .setView(vView)
            .setCancelable(false)
        val uniAlertDialog = uniAlert.show()
        btnCancel.setOnClickListener {
            needRun = !needRun
            uniAlertDialog.cancel()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun popUpUniverse(title: String, subTitle: String) {
        //initialize View
        val vView: View = LayoutInflater.from(context).inflate(R.layout.popup_reboot, null, false)
        val btnOk: Button = vView.findViewById(R.id.btnPopUpOk)
        val btnCancel: Button = vView.findViewById(R.id.btnPopUpCancel)
        val tvTitle: TextView = vView.findViewById(R.id.tvPopUpTitle)
        val tvSubTitle: TextView = vView.findViewById(R.id.tvPopUpSubTitle)

        //initialize text
        tvTitle.text = title
        tvSubTitle.text = subTitle
        btnOk.text = "Sudah"
        btnCancel.text = "Belum"

        val universeAlert = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .setView(vView)
            .setCancelable(false)
        val universeAlertDialog = universeAlert.show()

        btnOk.setOnClickListener {
            updateWater()

            universeAlertDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            universeAlertDialog.cancel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateWater() {
        val current: LocalDateTime = LocalDateTime.now()
        ref.child("waterDay").setValue(current.plusDays(4L).toString())
        needPopUp = !needPopUp
    }

    private fun isAnimationVisible(isVisible: Boolean) {
        if (isVisible) {
            binding.llAnimation.visibility = View.VISIBLE
            binding.animationLoading.playAnimation()
        } else {
            binding.llAnimation.visibility = View.GONE
            binding.animationLoading.cancelAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ref.removeEventListener(refListener)
    }

    override fun onResume() {
        super.onResume()
        isAnimationVisible(true)
        getDate()
        initData()
    }
}