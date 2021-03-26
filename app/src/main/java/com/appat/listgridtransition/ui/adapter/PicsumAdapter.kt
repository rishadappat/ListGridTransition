package com.appat.listgridtransition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appat.listgridtransition.databinding.GridItemBinding
import com.appat.listgridtransition.databinding.ListItemBinding
import com.appat.listgridtransition.roomdb.entities.Picsum
import com.appat.listgridtransition.ui.viewholder.GridViewHolder
import com.appat.listgridtransition.ui.viewholder.ListViewHolder
import com.appat.listgridtransition.utilities.Utility
import com.bumptech.glide.Glide

class PicsumAdapter(private val layoutManager: GridLayoutManager? = null, private val context: AppCompatActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        LIST,
        GRID
    }

    var items: ArrayList<Picsum>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.LIST.ordinal -> ListViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
            else -> GridViewHolder(GridItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items!![position]
        if(holder is ListViewHolder)
        {
            Glide.with(context).load(item.download_url).into(holder.itemBinding.image)
            holder.itemBinding.title.text = item.author
            holder.itemBinding.root.setOnClickListener {
                item.url.let {
                    Utility.openUrlInCustomTab(item.url!!, context)
                }
            }
        }
        if(holder is GridViewHolder)
        {
            Glide.with(context).load(item.download_url).into(holder.itemBinding.image)
            holder.itemBinding.title.text = item.author
            holder.itemBinding.root.setOnClickListener {
                item.url.let {
                    Utility.openUrlInCustomTab(item.url!!, context)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }

    fun setData(newItems: List<Picsum>)
    {
        if(items == null)
        {
            items = ArrayList()
        }
        items?.addAll(newItems)
        notifyDataSetChanged()
    }
}