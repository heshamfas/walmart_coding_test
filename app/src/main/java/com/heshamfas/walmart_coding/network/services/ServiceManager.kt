package com.heshamfas.walmart_coding.network.services

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


//Code ServiceGenerator snippet used courtesy of Marcus Pöhls and Norman Peitek
//https://leanpub.com/retrofit-love-working-with-apis-on-android

object ServiceManager {
    //val API_BASE_URL = "https://jsonplaceholder.typicode.com" //http://localhost:3000";

    val API_BASE_URL = "https://mobile-tha-server.firebaseapp.com"
    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
                                  .baseUrl(API_BASE_URL)
                                   /* .client(httpClient)*/
                                  .addConverterFactory(GsonConverterFactory.create())
                                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //NOTE: Jon Bott - Add this line for RxRetrofit

    fun <S> createService(serviceClass: Class<S>): S {
        Log.d("ServiceGen product", object{}.javaClass.enclosingMethod.name)
        return createService(serviceClass, null)
    }

    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        Log.d("ServiceGen product", object{}.javaClass.enclosingMethod.name)
        if (authToken != null) {
            addRequestHeaders(authToken)
        }

        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

    private fun addRequestHeaders(authToken: String?) {
        Log.d("Service Genproduct", object{}.javaClass.enclosingMethod.name)
        httpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder().header("Authorization", authToken).method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        })
    }
}