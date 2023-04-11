package com.example.mealapp.ui.Ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.R
import com.example.mealapp.data.model.Ingredients.IngredientsModel
import com.example.mealapp.databinding.FragmentIngredientsBinding
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<IngredientsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        viewModel.ingredient.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess { items ->
                setupUI(items)
            }

            result.doIfFailure { message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })

        viewModel.getIngredients()
        return binding.root
    }

    fun setupUI(ingredients: IngredientsModel) {
        val ingredientsAdapter = IngredientsAdapter(ingredients.meals)
        binding.rvIngredients.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter =ingredientsAdapter
        }


        ingredientsAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("Filter_Value", it.strIngredient)
                putSerializable("Filter_Type", "Ingredients")
            }
            findNavController().navigate(
                R.id.action_navigation_ingredients_to_mealFragment,bundle
            )
        }
    }
    //function to display error message
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}