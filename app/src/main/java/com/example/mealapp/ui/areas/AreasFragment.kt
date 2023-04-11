package com.example.mealapp.ui.areas

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
import com.example.mealapp.data.model.Area.AreasModelModel
import com.example.mealapp.databinding.FragmentAreasBinding
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.mealapp.ui.errorhandling.ErrorHandling.doIfSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreasFragment : Fragment() {

    private var _binding: FragmentAreasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<AreasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreasBinding.inflate(inflater, container, false)

        viewModel.area.observe(viewLifecycleOwner, Observer { result ->
            //Implementated sealed classes for success and failure of the code
            result.doIfSuccess { items ->
                setupUI(items)
            }

            result.doIfFailure { message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })

        viewModel.getAreas()
        return binding.root
    }

    fun setupUI(area: AreasModelModel) {
        val areaAdapter = AreaAdapter(area.meals)
        binding.rvArea.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter =areaAdapter
        }


       areaAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("Filter_Value", it.strArea)
                putSerializable("Filter_Type", "Area")
            }
            findNavController().navigate(
               R.id.action_navigation_areas_to_mealFragment,bundle
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