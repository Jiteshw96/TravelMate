package com.app.travelmate.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.travelmate.R

import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.databinding.ViewCityItemBinding

class CityListDataAdapter(private val cityList: List<CityItem>) :
    RecyclerView.Adapter<CityListDataAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val mBinding = DataBindingUtil.inflate<ViewCityItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.view_city_item,
            parent,
            false
        )
        return CityViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setItemData(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    inner class CityViewHolder(private var mBinding: ViewCityItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun setItemData(city: CityItem) {
            mBinding.cityName.text = city.name
            mBinding.cityDetail.text = city.description
            mBinding.cityImage.setBackgroundResource(city.image)
        }

    }
}