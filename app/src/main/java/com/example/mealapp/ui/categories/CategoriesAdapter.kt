package com.example.mealapp.ui.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealapp.R
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.categories.CategoryModel
import com.example.mealapp.data.model.meals.MealModel
import com.example.mealapp.databinding.ItemCategoriesBinding


class CategoriesAdapter(var categories: List<CategoryModel>):RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onItemClick: ((CategoryModel)-> Unit)?= null

    class ViewHolder (val view: View): RecyclerView.ViewHolder(view) {
        val binding= ItemCategoriesBinding.bind(view)
        fun handleData(item: CategoryModel?){
            item?.let {
                Glide.with(view).load(it.strCategoryThumb).placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivImage)
                binding.tvCategory.text= item.strCategory
                binding.tvDescription.text=item.strCategoryDescription
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        holder.handleData(categories?.get(position))
        holder.itemView.setOnClickListener{
           categories?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount():  Int= categories.size?:0


    // update adapter with the filter data
    @SuppressLint("SuspiciousIndentation")
    fun updateData(filteredCategory:List<CategoryModel>) {
      categories = filteredCategory
        notifyDataSetChanged()
    }

}



