package com.jonbott.learningrxjava.Activities.NetworkExample

import android.util.Log
import com.heshamfas.walmart_coding.entities.Product
import com.heshamfas.walmart_coding.entities.ProductResponse
import com.heshamfas.walmart_coding.model_layer.ModelLayer
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class ProductsPresenter {

    //Old Way
    val messages: BehaviorRelay<List<Product>>
        get() = modelLayer.products

    private var bag = CompositeDisposable()
    private val modelLayer = ModelLayer.shared //normally injected

    //New Way
    fun getProductsRx(pageNumber:Int, pageSize:Int): Single<ProductResponse> {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        return modelLayer.getProductsRx(pageNumber,pageSize)
    }

}