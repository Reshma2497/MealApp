package com.example.mealapp.ui.areas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.R
import com.example.mealapp.data.model.Area.MealModel
import com.example.mealapp.databinding.ItemAreaBinding

class AreaAdapter(var area: List<MealModel?>?): RecyclerView.Adapter<AreaAdapter.ViewHolder>() {

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAreaBinding.bind(view)
        fun handleData(item: MealModel?) {
            item?.let {
                binding.strArea.text = item.strArea
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AreaAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_area, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AreaAdapter.ViewHolder, position: Int) {
        holder.handleData(area?.get(position))
        holder.itemView.setOnClickListener {
            area?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = area?.size ?: 0

}