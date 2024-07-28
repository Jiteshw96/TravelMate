package com.app.travelmate.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.travelmate.R
import com.app.travelmate.databinding.ViewHomeCarouselItemBinding


class HomeCarouselAdapter(private val destinationDetails: List<Int>) :
    RecyclerView.Adapter<HomeCarouselAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val mBinding = DataBindingUtil.inflate<ViewHomeCarouselItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.view_home_carousel_item,
            parent,
            false
        )
        return PagerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.setData(destinationDetails.get(position))
    }

    override fun getItemCount(): Int {
        return destinationDetails.size
    }

    inner class PagerViewHolder(private val mBinding: ViewHomeCarouselItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun setData(sliderImage: Int) {
            mBinding.imageView.setBackgroundResource(sliderImage)
        }
    }
}