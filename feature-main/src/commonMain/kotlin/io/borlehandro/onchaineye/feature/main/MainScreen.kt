package io.borlehandro.onchaineye.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

// TODO: Migrate to components and inject dependencies
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val state by viewModel.state.collectAsState()
    val tabs = listOf("Main", "Settings")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        TabRow(selectedTabIndex = state.selectedTab) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = state.selectedTab == index,
                    onClick = { viewModel.onTabSelected(index) },
                    text = { Text(tab) },
                )
            }
        }
    }
}
