package com.example.leagueoflegendsapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.leagueoflegendsapp.champion_list.ChampionListScreen
import com.example.leagueoflegendsapp.champion_list.ChampionListViewModel
import com.example.leagueoflegendsapp.detail.ChampionDetailScreen
import com.example.leagueoflegendsapp.ui.theme.LeagueOfLegendsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeagueOfLegendsAppTheme {
                NavigationRoot()
            }
        }
    }
}

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(ChampionListScreen)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is ChampionListScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        val viewModel = hiltViewModel<ChampionListViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        ChampionListScreen(state, viewModel::onSearchTextChange) { championName ->
                            backStack.add(ChampionDetail(championName))
                        }
                    }
                }

                is ChampionDetail -> {
                    NavEntry(
                        key = key,
                    ) {
                        ChampionDetailScreen(key.championName)
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}

@Serializable
data object ChampionListScreen : NavKey

@Serializable
data class ChampionDetail(val championName: String) : NavKey