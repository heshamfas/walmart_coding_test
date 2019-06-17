package com.jonbott.learningrxjava.Activities.NetworkExample.Recycler

import android.support.v4.text.HtmlCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.heshamfas.walmart_coding.R
import com.heshamfas.walmart_coding.entities.Product
import com.squareup.picasso.Picasso

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val context = itemView.context
    val cardView: CardView
    val productIdTextView: TextView
    val productNameTextView: TextView
    val priceTextView: TextView
    val descriptionTextView: TextView
    val productImageView : ImageView

    init {
        cardView = itemView.findViewById(R.id.messageCardView)
        productIdTextView = itemView.findViewById(R.id.productIdTextView)
        productNameTextView = itemView.findViewById(R.id.product_name_TextView)
        this.priceTextView = itemView.findViewById(R.id.priceTextView)
        this.descriptionTextView = itemView.findViewById(R.id.descriptionTextView)
        productImageView = itemView.findViewById(R.id.product_imageView)
    }

    fun configureWith(product: Product?) {
//        Log.d(tag, Any().javaClass.enclosingMethod.toString())
//        this.productIdTextView.text = productproductId
        this.productNameTextView.text = product?.productName
        this.priceTextView.text = product?.price
        this.descriptionTextView.text = HtmlCompat.fromHtml(product?.longDescription?:"",HtmlCompat.FROM_HTML_MODE_LEGACY)
        Picasso.get()
                .load(context.getString(R.string.walmart_test_products_url,product?.productImage))
                .error(R.drawable.abc_btn_borderless_material)
                .into(productImageView)
    }
}