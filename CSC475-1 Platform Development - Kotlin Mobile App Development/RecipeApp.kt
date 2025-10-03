package com.example.recipeapp2

import android.app.Application
import com.example.recipeapp2.data.Graph

class RecipeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}