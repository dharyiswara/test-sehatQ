package com.dharyiswara.sehatqtest.ui.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.adapter.ProductAdapter
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.database.ProductRealm
import com.dharyiswara.sehatqtest.ui.detail.DetailProductActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_purchase_history.*
import org.jetbrains.anko.startActivity

class PurchaseHistoryActivity : BaseActivity() {

    private val productRealm by lazy { ProductRealm(Realm.getDefaultInstance()) }

    private val productAdapter by lazy {
        ProductAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_purchase_history

    override fun initView() {
        super.initView()

        val product = productRealm.getProductList()
        with(rvHistory) {
            layoutManager = LinearLayoutManager(this@PurchaseHistoryActivity)
            adapter = productAdapter
        }
        productAdapter.replaceData(product)
    }

    override fun initEvent() {
        super.initEvent()

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}