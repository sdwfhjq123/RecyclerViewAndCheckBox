package com.weichai.wms.recyclerviewandcheckboxdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    private val adapter by lazy { MyRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        adapter.apply {
            setOnItemClick { view, position ->
                selectedItem(position)
            }
        }

        all.setOnClickListener { clickAll() }

        allNo.setOnClickListener { clickAllNo() }
    }

    fun clickAll() {
        val map = adapter.getMap()
        for (i in map.entries.withIndex()) {
            map.put(i.index, true)
        }
        adapter.notifyDataSetChanged()
    }

    fun clickAllNo() {
        val map = adapter.getMap()
        for (i in map.entries.withIndex()) {
            map.put(i.index, false)
        }
        adapter.notifyDataSetChanged()
    }
}
