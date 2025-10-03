package com.example.recipeapp2

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.recipeapp2.model.response.MealResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealsCategoriesScreen(navController: NavController, viewModel : MealsCategoriesViewModel) {

    val recipeCategories = listOf( "American","British","Canadian","Chinese","Croatian",
        "Dutch","Egyptian","Filipino","French","Greek","Indian","Irish","Italian","Jamaican",
        "Japanese","Kenyan","Malaysian","Mexican","Moroccan","Polish","Portuguese","Russian",
        "Spanish","Thai","Tunisian","Turkish","Ukrainian","Unknown","Vietnamese"
    )


    var recipeObjects = emptyList<RememberMeals>()
    for (item in recipeCategories) {
        val rememberedMeals = remember { mutableStateOf(emptyList<MealResponse>())}
        val obj = RememberMeals(rememberedMeals, item)
        recipeObjects += obj
    }
    for (index in 0 until recipeCategories.size) {

        viewModel.getMeals({ response ->
            val mealsFromTheApi = response?.categories
            recipeObjects[index].rememberedMeal.value = mealsFromTheApi.orEmpty()
        }, c = recipeCategories[index])

    }
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                  IconButton(onClick = { navController.navigate(MealsScreen.SavedRecipesScreen.route) }) {
                      Icon(imageVector = Icons.TwoTone.List, contentDescription = "Top Bar Icon")
                  }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.tertiary,
                ),
                title = {
                    Text("Recipe Search")
                }
            )
        }
    ){

        LazyColumn(userScrollEnabled = true,
            modifier = Modifier.padding(it)) {
            for (index in 0 until recipeCategories.size) {
                stickyHeader {
                    Text(text = recipeCategories[index], modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium)
                    LazyRow {
                        recipeObjects.forEach { recipe ->
                            if (recipeCategories[index] == recipe.country)
                                items(recipe.rememberedMeal.value) {
                                    MealCategory(it, navController)
                                }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MealCategory(meal: MealResponse, navController: NavController) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).clickable { navController.navigate(MealsScreen.DetailsScreen.route + "/${meal.idMeal}") }, border = BorderStroke(3.dp, color = Color.DarkGray),


    ) {

        Column() {
            Text(text = meal.idMeal)
            Row( modifier = Modifier.animateContentSize()) {
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
