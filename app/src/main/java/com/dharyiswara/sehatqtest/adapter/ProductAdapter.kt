package com.dharyiswara.sehatqtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.helper.extension.loadFromUrl
import com.dharyiswara.sehatqtest.model.ProductPromo
import kotlinx.android.synthetic.main.layout_item_product.view.*

class ProductAdapter(private val listener: (ProductPromo) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {

    private val productList = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_product, parent, false), listener
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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

}


class ProductViewHolder(view: View, private val listener: (ProductPromo) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(product: ProductPromo) {
        with(itemView) {
            ivProduct.loadFromUrl(product.imageUrl)
            tvTitle.text = product.title
            tvPrice.text = product.price
            setOnClickListener {
                listener.invoke(product)
            }
        }

    }

}