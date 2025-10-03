package com.example.recipeapp2

import androidx.compose.runtime.MutableState
import com.example.recipeapp2.model.response.MealResponse

data class RememberMeals(var rememberedMeal : MutableState<List<MealResponse>>,
                         var country: String)
