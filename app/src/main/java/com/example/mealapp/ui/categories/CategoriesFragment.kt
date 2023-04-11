package com.example.mealapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.R
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.databinding.FragmentCategoriesBinding
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<CategoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        viewModel.categories.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess { items ->
                setupUI(items)
            }

            result.doIfFailure { message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })

        viewModel.getCategories()
        return binding.root
    }

    fun setupUI(categories: CategoriesModel) {
        val categoriesAdapter = CategoriesAdapter(categories.categories)
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesAdapter
        }


        categoriesAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("Filter_Value", it.strCategory)
                putSerializable("Filter_Type", "Category")
            }
            findNavController().navigate(
                R.id.action_navigation_categories_to_mealFragment,bundle
            )
        }
        //define setonquerytextlinstener is method will be called whenever i type something in text bar
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                // to filter the data start with lastname given in a search bar
                val filteredList  = categories.categories.filter {
                    it.strCategory?.startsWith(newText, ignoreCase = true)?:false
                }

                // filteredPeople.setPeople
                categoriesAdapter.updateData(filteredList)
                return true
            }
        })
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
