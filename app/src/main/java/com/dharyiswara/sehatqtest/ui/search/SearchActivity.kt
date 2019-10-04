package com.dharyiswara.sehatqtest.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.adapter.ProductAdapter
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.helper.extension.hideKeyboard
import com.dharyiswara.sehatqtest.model.ProductPromo
import com.dharyiswara.sehatqtest.ui.detail.DetailProductActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class SearchActivity : BaseActivity() {

    private val productAdapter by lazy {
        ProductAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    private var productList = listOf<ProductPromo>()

    companion object {

        const val LIST_DATA = "list_data"

    }

    override fun getLayoutResId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

        productList = intent.getSerializableExtra(LIST_DATA) as List<ProductPromo>
        with(rvSearch) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = productAdapter
        }
    }

    override fun initEvent() {
        super.initEvent()

        ivBack.setOnClickListener {
            onBackPressed()
        }
        etSearch?.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.hideKeyboard()
                v.clearFocus()
            }
            return@setOnEditorActionListener false
        }

        etSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isEmpty()) {
                        productAdapter.clearData()
                    } else {
                        if (productAdapter.itemCount == 0)
                            productAdapter.replaceData(productList)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}