package com.weichai.wms.recyclerviewandcheckboxdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view.view.*

class MyRecyclerViewAdapter() : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private val datas = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    private var isShowBox: Boolean = false
    private var map = HashMap<Int, Boolean>()
    private var itemClick: ((View, Int) -> Unit)? = null

    init {
        //初始化map集合,默认为不选中
        datas.forEach {
            map.put(it, false)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: MyRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    //点击item选中CheckBox
    fun selectedItem(position: Int) {
        //对当前状态取反
        map[position] = !map[position]!!
        notifyItemChanged(position);
    }

    //返回集合给MainActivity
    fun getMap() = map

    fun setOnItemClick(itemClick: (view: View, position: Int) -> Unit) {
        this.itemClick = itemClick
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Int) {
            itemView.textview.text = data.toString()
            itemView.tag = adapterPosition
            itemView.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                //用map保存
                map[adapterPosition] = isChecked
            }

            if (map[adapterPosition] == null) {
                map[adapterPosition] = false
            }

            itemView.checkbox.isChecked = map[adapterPosition]!!

            itemView.setOnClickListener { itemClick?.invoke(itemView, adapterPosition) }
        }

    }

}