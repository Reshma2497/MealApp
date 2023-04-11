package com.example.mealapp.ui.Ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.R
import com.example.mealapp.data.model.Ingredients.MealModel
import com.example.mealapp.databinding.ItemIngredientsBinding


class IngredientsAdapter (var ingredient: List<MealModel?>?): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemIngredientsBinding.bind(view)
        fun handleData(item: MealModel?) {
            item?.let {
                binding.strIngredient.text = item.strIngredient
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
        holder.handleData(ingredient?.get(position))
        holder.itemView.setOnClickListener {
            ingredient?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = ingredient?.size ?: 0

}