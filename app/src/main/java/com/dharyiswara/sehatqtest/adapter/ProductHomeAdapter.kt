package com.dharyiswara.sehatqtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.helper.extension.loadFromUrl
import com.dharyiswara.sehatqtest.model.ProductPromo
import kotlinx.android.synthetic.main.layout_item_product_home.view.*
import org.jetbrains.anko.backgroundDrawable

class ProductHomeAdapter(private val listener: (ProductPromo) -> Unit) :
    RecyclerView.Adapter<ProductHomeViewHolder>() {

    private val productList = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHomeViewHolder {
        return ProductHomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_product_home, parent, false), listener
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductHomeViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun replaceData(list: List<ProductPromo>) {
        productList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        productList.clear()
        notifyDataSetChanged()
    }

    fun getProductList(): List<ProductPromo> = productList

}


class ProductHomeViewHolder(view: View, private val listener: (ProductPromo) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(product: ProductPromo) {
        with(itemView) {
            ivProduct.loadFromUrl(product.imageUrl)
            tvTitle.text = product.title

            if (product.isLoved())
                ivLoved.backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.ic_loved)
            else
                ivLoved.backgroundDrawable =
                    ContextCompat.getDrawable(context, R.drawable.ic_not_loved)
            setOnClickListener {
                listener.invoke(product)
            }
        }

    }

}