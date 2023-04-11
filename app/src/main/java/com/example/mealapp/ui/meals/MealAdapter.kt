package com.example.mealapp.ui.meals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealapp.R
import com.example.mealapp.data.model.meals.MealModel
import com.example.mealapp.databinding.ItemMealBinding

class MealAdapter( var meals: List<MealModel?>?) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    var onItemClick: ((MealModel)-> Unit)?=null

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        val binding= ItemMealBinding.bind(view)

        fun handleData(item: MealModel?){
            item?.let {
                Glide.with(view).load(it.strMealThumb).placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivImage)
                binding.tvIdMeal.text=item.idMeal
                binding.tvstrMeal.text=item.strMeal


            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int= meals?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.handleData(meals?.get(position))
        holder.itemView.setOnClickListener{
            meals?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

}