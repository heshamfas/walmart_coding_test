package com.heshamfas.walmart_coding.network

import android.util.Log
//import com.github.kittinunf.result.Result
import com.heshamfas.walmart_coding.entities.Product
import com.heshamfas.walmart_coding.entities.ProductResponse
import com.heshamfas.walmart_coding.network.endpoints.RetrofitPlaceHolder
import com.heshamfas.walmart_coding.network.services.ServiceManager
import io.reactivex.Single

typealias MessageLambda = (Product?)->Unit
typealias MessagesLambda = (List<Product>?)->Unit

class NetworkLayer {
    companion object { val instance = NetworkLayer() }

    private val placeHolderApi: RetrofitPlaceHolder

    init {
        placeHolderApi = ServiceManager.createService(RetrofitPlaceHolder::class.java)
    }

    fun getProductRx(pageNumber: Int, pageSize:Int): Single<ProductResponse> {
        Log.d("NetworkLayer product", object{}.javaClass.enclosingMethod.name)
        return placeHolderApi.getProductRx(pageNumber,pageSize)
    }

}







