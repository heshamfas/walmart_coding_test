package com.heshamfas.walmart_coding.model_layer

import android.util.Log
import com.heshamfas.walmart_coding.entities.Product
import com.heshamfas.walmart_coding.entities.ProductResponse
import com.heshamfas.walmart_coding.network.NetworkLayer
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single

class ModelLayer {

    companion object {
        val shared = ModelLayer()
    }
    val products = BehaviorRelay.createDefault(listOf<Product>())

    private val networkLayer = NetworkLayer.instance


    private fun notifyOfError(errorMessage: String) {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        //notify user somehow
        println("❗️ Error occurred: $errorMessage")
    }

    fun getProductsRx(pageNumber:Int, pageSize: Int): Single<ProductResponse> {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        return networkLayer.getProductRx(pageNumber, pageSize)
    }


}





