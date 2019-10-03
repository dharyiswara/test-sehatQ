package com.dharyiswara.sehatqtest.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dharyiswara.sehatqtest.R
import com.dharyiswara.sehatqtest.helper.extension.loadFromUrl
import com.dharyiswara.sehatqtest.model.ProductPromo
import kotlinx.android.synthetic.main.layout_item_product.view.*
import org.jetbrains.anko.backgroundDrawable

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private val productList = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_product, parent, false)
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


class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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

            }
        }

    }

}