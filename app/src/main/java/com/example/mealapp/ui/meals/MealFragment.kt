package com.example.mealapp.ui.meals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.R
import com.example.mealapp.data.model.meals.MealsModelModel
import com.example.mealapp.databinding.FragmentMealBinding
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MealViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_meal, container, false)
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        viewModel.meal.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess { items ->
                setupUI(items)
            }

            result.doIfFailure { message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })
       var filterValue = arguments?.getSerializable("Filter_Value") as String
        var filterType = arguments?.getSerializable("Filter_Type") as String

        viewModel.getMealByFilter(filterType,filterValue)
        return binding.root
    }

    fun setupUI(meal: MealsModelModel) {
        val mealAdapter = MealAdapter(meal.meals)
        binding.rvMeal.apply {
            //layoutManager = GridLayoutManager(context,2)
            layoutManager=LinearLayoutManager(context)
            adapter = mealAdapter
        }


        mealAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("MealId", it.idMeal)
            }
           findNavController().navigate(
                R.id.action_mealFragment_to_mealDetailsFragment, bundle
           )
        }
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