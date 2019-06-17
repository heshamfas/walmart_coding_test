package com.heshamfas.walmart_coding.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.heshamfas.walmart_coding.R
import com.heshamfas.walmart_coding.entities.Product
import com.jonbott.learningrxjava.Activities.NetworkExample.ProductsPresenter
import com.jonbott.learningrxjava.Activities.NetworkExample.Recycler.ProductsViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductsActivity : AppCompatActivity() {

    private val presenter = ProductsPresenter()
    private val bag = CompositeDisposable()
    private var pageNumber:Int = 0
    private val pageSize:Int = 20
    private var listOfProducts = listOf<Product>()

    lateinit var adapter: ProductsViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("NWActivity product", object{}.javaClass.enclosingMethod.name)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        attachUI()

        //New Way
            loadData()


    }
    fun showProgress(show:Boolean):Unit{
       val loading_progress: ProgressBar= findViewById(R.id.loading_progress)
        if(show==true){
            loading_progress.visibility = (View.VISIBLE)
            //loading_progress.set
        }else loading_progress.visibility = (View.GONE)
    }

    private fun loadData() {
        showProgress(true)// never run this in the background
        GlobalScope.launch{   Log.d("NWActivity product", object{}.javaClass.enclosingMethod.name)

            presenter.getProductsRx(++pageNumber  , pageSize )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { productResponse ->
                        listOfProducts += productResponse.products
                        adapter.products.accept(listOfProducts)
                        showProgress(false)
        }.addTo(bag)
                    }

    }

    private fun attachUI() {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        val linearLayoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        networkRecyclerView.layoutManager = linearLayoutManager
        networkRecyclerView.setHasFixedSize(true)
        networkRecyclerView.addItemDecoration(dividerItemDecoration)
        networkRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)&& newState==0){
                    Toast.makeText(this@ProductsActivity, "new state $newState we have arrived to last item", Toast.LENGTH_LONG).show()
                    loadData()
                }
                val totalItemCount = recyclerView.layoutManager!!.itemCount
               /* if (!imageRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestPhoto()
                }*/
            }
        })

        initializeListView()
    }

    private fun initializeListView() {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        adapter = ProductsViewAdapter { view, position -> rowTapped(position) }
        networkRecyclerView.adapter = adapter
    }

    private fun rowTapped(position: Int) {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        println("🍄")
        println(adapter.products.value?.get(position))
    }

    override fun onDestroy() {
        Log.d("product", object{}.javaClass.enclosingMethod.name)
        super.onDestroy()
        bag.clear()
    }
}

