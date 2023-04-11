package com.example.mealapp.ui.areas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.data.model.Area.AreasModelModel
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.errorhandling.ResultOf
import com.example.mealapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AreasViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val area=MutableLiveData<ResultOf<AreasModelModel>>()

    fun getAreas(){
        viewModelScope.launch {
            try {
                val result = repository.getArea()
               area.postValue(ResultOf.Success(result))
            }catch(ioe: IOException) {
                area.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                area.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
}