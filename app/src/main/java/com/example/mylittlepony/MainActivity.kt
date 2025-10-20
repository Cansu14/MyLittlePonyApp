package com.example.mylittlepony

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.mylittlepony.navigation.NavGraph
import com.example.mylittlepony.ui.theme.MyLittlePonyTheme
import com.example.mylittlepony.viewmodel.PonyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLittlePonyTheme {
                NavGraph()
            }
        }
    }
}
