package com.app.travelmate.presentation.ui.activity.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.app.travelmate.R
import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.data.repository.DestinationRepositoryImpl
import com.app.travelmate.databinding.ActivityHomeBinding
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase
import com.app.travelmate.presentation.base.BaseActivity
import com.app.travelmate.presentation.ui.adapter.CityListDataAdapter
import com.app.travelmate.presentation.ui.adapter.HomeCarouselAdapter
import com.app.travelmate.presentation.ui.dialog.BottomSheetDialog
import com.app.travelmate.presentation.utils.Extensions.visibilityToggle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val mainViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            GetDestinationCatalogUseCase(DestinationRepositoryImpl())
        )
    }
    private lateinit var cityListAdapter: CityListDataAdapter

    private lateinit var homeCarouselAdapter: HomeCarouselAdapter

    private var cityListData = mutableListOf<CityItem>()

    override fun getLayout(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        setDestinationData()
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.homeScreenState.collectLatest { state ->
                        when {
                            state.isLoading -> {
                                dataBinding.progressBar.visibilityToggle(true)
                            }

                            state.isError != null -> {
                                dataBinding.progressBar.visibilityToggle(false)
                                state.isError.let { error ->
                                    val errorMessage = error.apiError ?: getString(
                                        error.genericError ?: R.string.generic_error_message
                                    )
                                    Toast.makeText(
                                        this@HomeActivity,
                                        errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            else -> {
                                dataBinding.progressBar.visibilityToggle(false)
                                setAdapterData()
                            }
                        }

                    }
                }

                launch {
                    mainViewModel.filterItemsLiveData.collectLatest { filterData ->
                        cityListData.clear()
                        cityListData.addAll(filterData)
                        if (this@HomeActivity::cityListAdapter.isInitialized) {
                            cityListAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }

    private fun setDestinationData() {
        mainViewModel.fetchDestinationData()
    }

    private fun setUpViews() {
        dataBinding.fab.setOnClickListener {
            val bottomSheetDetails =
                mainViewModel.getBottomSheetDetails(dataBinding.homeCarousel.viewPager.currentItem)
            val modal = BottomSheetDialog(bottomSheetDetails)
            supportFragmentManager.let { modal.show(it, BottomSheetDialog.TAG) }
        }

        dataBinding.homeCarousel.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                cityListData.clear()
                cityListData.addAll(mainViewModel.getCityItems(position))
                if (this@HomeActivity::cityListAdapter.isInitialized) {
                    cityListAdapter.notifyDataSetChanged()
                }
                resetSearchQuery()
            }
        })

        dataBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText)
                return true
            }
        })
    }


    private fun setAdapterData() {
        val images = mainViewModel.getHomeCarouselData()
        homeCarouselAdapter = HomeCarouselAdapter(images)
        cityListData.clear()
        cityListData.addAll(mainViewModel.getCityItems(0))
        cityListAdapter = CityListDataAdapter(cityListData)
        dataBinding.apply {
            homeCarousel.viewPager.adapter = homeCarouselAdapter
            itemList.adapter = cityListAdapter
            itemList.layoutManager = LinearLayoutManager(this@HomeActivity)
            TabLayoutMediator(homeCarousel.tabLayout, homeCarousel.viewPager) { tab, position ->
            }.attach()
        }
    }


    private fun performSearch(searchQuery: String?) {
        searchQuery?.let {
            mainViewModel.filterCityItems(
                dataBinding.homeCarousel.viewPager.currentItem,
                searchQuery
            )
        }
    }

    private fun resetSearchQuery() {
        dataBinding.search.setQuery("", false)
        dataBinding.search.clearFocus()
    }
}