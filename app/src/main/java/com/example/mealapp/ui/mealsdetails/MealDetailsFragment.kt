package com.example.mealapp.ui.mealsdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mealapp.R
import com.example.mealapp.data.model.meals.details.MealDetailsModel
import com.example.mealapp.data.model.meals.details.MealModel
import com.example.mealapp.databinding.FragmentMealDetailsBinding
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private var _binding: FragmentMealDetailsBinding? = null
    private val binding get() = _binding!!
    private var mealId: String?= null
    private val viewModel by viewModels<MealDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        mealId=arguments?.getSerializable("MealId") as String
        viewModel.mealDetails.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess {items ->
                setupUI(items.meals?.get(0))
            }

            result.doIfFailure {message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })
        viewModel.getMealDetails(mealId.toString())

       return binding.root
    }


    fun setupUI(mealDetails: MealModel?) {



        view?.let {
            val meals:String=""
            Glide.with(it).load(mealDetails?.strMealThumb).placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivImage)
        }
        binding.tvTitle.text=mealDetails?.strMeal
        binding.tvYoutube.text=mealDetails?.strYoutube
        binding.tvTags.text=mealDetails?.strTags
        binding.tvCategory.text=mealDetails?.strCategory
        binding.tvArea.text=mealDetails?.strArea
        binding.tvIngredients.text=mealDetails?.strIngredient1
        binding.tvMeasures.text=mealDetails?.strMeasure1
        binding.tvInstructions.text=mealDetails?.strInstructions
    }

    private fun showErrorPopup(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}