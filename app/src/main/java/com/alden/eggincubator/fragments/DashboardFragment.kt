package com.alden.eggincubator.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.FragmentDashboardBinding
import com.alden.eggincubator.models.CompleteTriggerData
import com.alden.eggincubator.models.RTDBDataClass
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DashboardFragment : Fragment() {
    val fbdb = FirebaseDatabase.getInstance()
    lateinit var vLivedata : CompleteTriggerData
    lateinit var mightyDate : LocalDateTime
    private var binding: FragmentDashboardBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vLivedata = CompleteTriggerData()
        vLivedata.status.value = false
        binding?.llAnimation?.visibility = View.VISIBLE
        initData()



    }

    private fun initData() {
        val ref = fbdb.getReference("FirebaseIOT")
        ref.addValueEventListener(object : ValueEventListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                var dataH = snapshot.child("humidity").value.toString()
                var dataT = snapshot.child("temperature").value.toString()
                var dataL = snapshot.child("lampu1").value.toString()
                var dataL2 = snapshot.child("lampu2").value.toString()
                var dataD = snapshot.child("mightyDay").value.toString()

                var rtdbDataClass = RTDBDataClass(
                    dataT,
                    dataH,
                    dataL,
                    dataL2,
                    dataD
                )
                initView(rtdbDataClass)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(data: RTDBDataClass) {
        val formatter = DateTimeFormatter.ofPattern("YYYY-MMMM-DD")
        val current : LocalDateTime = LocalDateTime.now()
//        val theDay = LocalDateTime.parse("2020-12-22T16:33:55.707")
        mightyDate = LocalDateTime.parse(data.mightyDay)
        var exceededDay : String = Duration.between(mightyDate,current).toDays().toString()
        val remainDay = 28 - exceededDay.toInt()

        binding?.tvShowRemainDay?.text = "$remainDay hari lagi telur\nkamu akan\nmenetas"
        binding?.tvTemperatureStatus?.text = data.temperature +"°C"
        binding?.tvKelembabanStatus?.text = data.humidity+"%"
        binding?.tvInkubasiStatus?.text = "$exceededDay Hari"
        binding?.tvInkubasiSub?.text = "Telur telah diinkubasi $exceededDay hari"
        binding?.llAnimation?.visibility = View.GONE
        vLivedata.status.value = true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}