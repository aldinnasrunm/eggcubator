package com.alden.eggincubator.onboarding.customView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager2.widget.ViewPager2
import com.alden.eggincubator.R
import com.alden.eggincubator.domain.OnBoardPreferencesManager
import com.alden.eggincubator.onboarding.OnBoardingAdapter
import com.alden.eggincubator.onboarding.entity.OnBoardingPage
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import setParallaxTransformation

class OnBoardingView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    val view = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
    val mSlider: ViewPager2 = view.findViewById(R.id.slider)
    val onboardingRoot: MotionLayout = view.findViewById(R.id.onboardingRoot)
    val nextBtn: Button = view.findViewById(R.id.nextBtn)
    val skipBtn: Button = view.findViewById(R.id.skipBtn)
    val startBtn: Button = view.findViewById(R.id.startBtn)
    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: OnBoardPreferencesManager

    init {

        setUpSlider(view)
        addingButtonsClickListeners()
        prefManager = OnBoardPreferencesManager(view.context)
    }

    private fun setUpSlider(view: View) {


        with(mSlider) {
            adapter = OnBoardingAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position, view.context)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener() {

        mSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
        }
        startBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
        Log.d("TAG", "setFirstTimeLaunchToFalse: isfalse")
        Toast.makeText(context, "drrr", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = mSlider?.currentItem?.plus(1) ?: 0
        mSlider?.setCurrentItem(nextSlidePos, true)
    }

    private fun navigateToMainACtivity() {
    }
}