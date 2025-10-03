package com.example.recipeapp2

import androidx.lifecycle.ViewModel
import com.example.recipeapp2.model.MealsRepository
import com.example.recipeapp2.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel () : ViewModel() {

    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit, c: String)  {
        val repository: MealsRepository = MealsRepository(c=c)
        repository.getMeals() {response ->
            successCallback(response)
        }
    }
}