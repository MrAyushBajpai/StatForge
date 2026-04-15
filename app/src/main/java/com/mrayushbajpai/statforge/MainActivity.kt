package com.mrayushbajpai.statforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrayushbajpai.statforge.data.repository.StatRepositoryImpl
import com.mrayushbajpai.statforge.ui.navigation.StatForgeNavGraph
import com.mrayushbajpai.statforge.ui.theme.StatForgeTheme
import com.mrayushbajpai.statforge.ui.viewmodel.StatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Manual DI for MVP foundation
        val repository = StatRepositoryImpl()
        val viewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StatViewModel(repository) as T
            }
        }

        enableEdgeToEdge()
        setContent {
            StatForgeTheme {
                val viewModel: StatViewModel = viewModel(factory = viewModelFactory)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        StatForgeNavGraph(
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
