package com.jonbott.learningrxjava.Activities.NetworkExample.Recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.heshamfas.walmart_coding.R
import com.heshamfas.walmart_coding.entities.Product
import com.heshamfas.walmart_coding.shared.ItemClickedlambda
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ProductsViewAdapter(var onItemClicked: ItemClickedlambda): RecyclerView.Adapter<ProductViewHolder>() {

    internal val products = BehaviorRelay.createDefault(listOf<Product>())

    private val bag = CompositeDisposable()

    init {
        products.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    notifyDataSetChanged()
                }.addTo(bag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        val viewHolder = ProductViewHolder(view)

        view.setOnClickListener { v -> onItemClicked(v, viewHolder.adapterPosition) }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.value?.get(position)
        holder.configureWith(product)
    }

    override fun getItemCount(): Int = products.value!!.size

}