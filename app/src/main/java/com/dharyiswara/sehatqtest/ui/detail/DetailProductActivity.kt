package com.dharyiswara.sehatqtest.ui.detail

import android.content.Intent
import androidx.core.content.ContextCompat
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.base.BaseActivity
import com.dharyiswara.sehatqtest.database.ProductRealm
import com.dharyiswara.sehatqtest.helper.extension.loadFromUrl
import com.dharyiswara.sehatqtest.model.ProductPromo
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail_product.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.toast

class DetailProductActivity : BaseActivity() {

    private var product: ProductPromo? = null

    private val productRealm by lazy {
        ProductRealm(Realm.getDefaultInstance(),
            {
                toast(getString(R.string.string_success_buy))
            },
            {
                toast(getString(R.string.string_failed_buy))
            })
    }

    companion object {

        const val PRODUCT = "product"

        const val INTENT_TYPE_TEXT = "text/plain"

    }

    override fun getLayoutResId(): Int = R.layout.activity_detail_product

    override fun initView() {
        super.initView()

        product = intent?.getSerializableExtra(PRODUCT) as ProductPromo
        product?.let {
            setProduct(it)
        }
    }

    override fun initEvent() {
        super.initEvent()

        btnBuy.setOnClickListener {
            product?.let {
                productRealm.buyProduct(it)
            }
        }

        ivShare.setOnClickListener {
            product?.let {
                share(it.title, it.price)
            }
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        ivLoved.setOnClickListener {
            product?.let {
                if (it.isLoved()) {
                    it.loved = 0
                    toast(getString(R.string.string_dislike))
                    ivLoved.backgroundDrawable =
                        ContextCompat.getDrawable(this, R.drawable.ic_not_loved)
                } else {
                    it.loved = 1
                    toast(getString(R.string.string_like))
                    ivLoved.backgroundDrawable =
                        ContextCompat.getDrawable(this, R.drawable.ic_loved)
                }
            }
        }
    }

    private fun setProduct(productPromo: ProductPromo) {
        ivProduct.loadFromUrl(productPromo.imageUrl)
        tvTitle.text = productPromo.title
        tvDesc.text = productPromo.description
        tvPrice.text = productPromo.price
        if (productPromo.isLoved())
            ivLoved.backgroundDrawable = ContextCompat.getDrawable(this, R.drawable.ic_loved)
        else
            ivLoved.backgroundDrawable =
                ContextCompat.getDrawable(this, R.drawable.ic_not_loved)

    }

    private fun share(title: String, price: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = INTENT_TYPE_TEXT
        val text = "Nikmati Promo Product $title dengan harga $price"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, getString(R.string.string_share)))
    }

}