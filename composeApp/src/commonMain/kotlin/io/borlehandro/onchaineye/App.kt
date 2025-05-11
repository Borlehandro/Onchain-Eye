package io.borlehandro.onchaineye

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.borlehandro.onchaineye.feature.main.MainScreen
import io.borlehandro.onchaineye.feature.main.MainScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // TODO: Migrate to components and inject dependencies
    val mainViewModel = remember { MainScreenViewModel() }

    MaterialTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MainScreen(mainViewModel)
        }
    }
}
