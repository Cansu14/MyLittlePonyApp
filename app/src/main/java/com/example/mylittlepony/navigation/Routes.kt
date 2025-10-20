package com.example.mylittlepony.navigation

sealed class Routes (val route: String){
    data object List : Routes("pony_list")
    data object Detail : Routes("pony_detail/{ponyId}"){
        fun createRoute(ponyId: Int) = "pony_detail/$ponyId"
    }
}