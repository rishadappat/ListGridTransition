package com.appat.listgridtransition.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.appat.graphicov.webservice.service.Status
import com.appat.graphicov.webservice.service.WebService
import com.appat.listgridtransition.R
import com.appat.listgridtransition.databinding.ActivityMainBinding
import com.appat.listgridtransition.roomdb.viewmodel.PicsumViewModel
import com.appat.listgridtransition.roomdb.viewmodel.ViewModelFactory
import com.appat.listgridtransition.ui.adapter.PicsumAdapter
import com.appat.listgridtransition.webservice.api.PicsumApi
import com.appat.listgridtransition.webservice.serviceinterface.PicsumService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var picsumViewModel: PicsumViewModel

    private lateinit var api: PicsumApi

    private var layoutManager: GridLayoutManager? = null

    private var adapter: PicsumAdapter? = null

    var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        api = PicsumApi(WebService().getPicsumService(PicsumService::class.java))
        setViewModel()

        layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = layoutManager
        adapter = PicsumAdapter(layoutManager, this)
        binding.recyclerView.adapter = adapter
    }

    private fun setViewModel()
    {
        val factory = ViewModelFactory(api)
        picsumViewModel = ViewModelProvider(this, factory).get(PicsumViewModel::class.java)
        getLists()
    }

    private fun getLists()
    {
        val params = HashMap<String, Int>()
        params["page"] = page
        params["limit"] = 30
        lifecycleScope.launch {
            picsumViewModel.loadListFromWeb(params)
        }
        picsumViewModel.getLists().observe(this, { resource ->
            resource.let { resourceData ->
                when (resourceData.status) {
                    Status.SUCCESS -> {
                        resourceData.data?.observe(
                            this@MainActivity,
                            { lists ->
                                if(page == 1)
                                {
                                    adapter?.items?.clear()
                                }
                                adapter?.setData(lists)
                            })
                    }
                    Status.LOADING -> {

                    }
                    else -> {

                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_layout -> {
                if (layoutManager?.spanCount == 1) {
                    layoutManager?.spanCount = 3
                    item.title = "list"
                } else {
                    layoutManager?.spanCount = 1
                    item.title = "grid"
                }
                adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}