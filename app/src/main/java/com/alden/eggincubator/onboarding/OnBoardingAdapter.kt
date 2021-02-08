package com.alden.eggincubator.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.alden.eggincubator.R
import com.alden.eggincubator.onboarding.entity.OnBoardingPage

class OnBoardingAdapter(private val onBoardingPageList: Array<OnBoardingPage> = OnBoardingPage.values()) :
    RecyclerView.Adapter<PageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return LayoutInflater.from(parent.context).inflate(PageHolder.LAYOUT, parent, false).let { PageHolder((it)) }
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.bind(onBoardingPageList[position])
    }

    override fun getItemCount() = onBoardingPageList.size
}

class PageHolder(private val v: View) : RecyclerView.ViewHolder(v) {
    fun bind(onBoardingPage: OnBoardingPage) {
        val res = v.context.resources

        val titleTv = v.findViewById<TextView>(R.id.titleTv)
        val subTitleTv = v.findViewById<TextView>(R.id.subTitleTv)
        val descTv = v.findViewById<TextView>(R.id.descTV)
        val img = v.findViewById<ImageView>(R.id.img)

        titleTv?.text = res.getString(onBoardingPage.titleResource)
        subTitleTv?.text = res.getString(onBoardingPage.subTitleResource)
        descTv?.text = res.getString(onBoardingPage.descriptionResources)
        img.setImageResource(onBoardingPage.logoResource)

    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.onboarding_layout_item
    }
}
