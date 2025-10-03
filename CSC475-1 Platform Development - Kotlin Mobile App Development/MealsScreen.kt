package com.example.recipeapp2

sealed class MealsScreen (val route:String) {
    object CategoriesScreen: MealsScreen("categories_screen")
    object DetailsScreen: MealsScreen("details_screen")
    object SavedRecipesScreen: MealsScreen("saved_recipes_screen")
}