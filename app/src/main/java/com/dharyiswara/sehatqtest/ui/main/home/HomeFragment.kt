package com.dharyiswara.sehatqtest.ui.main.home

import android.view.MotionEvent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseFragment
import com.dharyiswara.sehatqtest.helper.Status
import com.dharyiswara.sehatqtest.helper.extension.gone
import com.dharyiswara.sehatqtest.helper.extension.visible
import com.dharyiswara.sehatqtest.model.Homepage
import com.dharyiswara.sehatqtest.adapter.CategoryAdapter
import com.dharyiswara.sehatqtest.adapter.ProductHomeAdapter
import com.dharyiswara.sehatqtest.ui.detail.DetailProductActivity
import com.dharyiswara.sehatqtest.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    private val viewModel by inject<HomeViewModel>()

    private val categoryAdapter by lazy { CategoryAdapter() }

    private val productAdapter by lazy {
        ProductHomeAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()
        with(rvCategory) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
        with(rvProduct) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    override fun initEvent() {
        super.initEvent()
        ivListLoved.setOnClickListener {
            toast("List yg disukai")
        }
        etSearch.setOnTouchListener { _, event ->
            if (event?.action == MotionEvent.ACTION_DOWN) {
                if (!swHome.isRefreshing)
                    startActivity<SearchActivity>(
                        SearchActivity.LIST_DATA to productAdapter.getProductList()
                    )
            }
            false
        }

        swHome.setOnRefreshListener {
            loadingData(true)
        }
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        if (isFromSwipe) {
            rvCategory.gone()
            rvProduct.gone()
            categoryAdapter.clearData()
            productAdapter.clearData()
        }
        viewModel.getHomepage(isFromSwipe)
    }

    override fun observeData() {
        super.observeData()
        viewModel.homepageData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> startLoading()
                Status.SUCCESS -> {
                    stopLoading()
                    it.data?.let { response ->
                        getHomepageSuccess(response[0].homepage)
                    }
                }
                Status.EMPTY -> stopLoading()
                Status.ERROR -> {
                    stopLoading()
                    it.throwable?.printStackTrace()
                }
            }
        })
    }

    private fun startLoading() {
        swHome.isRefreshing = true
    }

    private fun stopLoading() {
        swHome.isRefreshing = false
    }

    private fun getHomepageSuccess(data: Homepage) {
        if (data.category.isNotEmpty()) {
            rvCategory.visible()
            categoryAdapter.replaceData(data.category)
        }
        if (data.productPromo.isNotEmpty()) {
            rvProduct.visible()
            productAdapter.replaceData(data.productPromo)
        }
    }

}