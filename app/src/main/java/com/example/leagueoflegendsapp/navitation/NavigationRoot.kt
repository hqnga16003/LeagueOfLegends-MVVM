package com.example.leagueoflegendsapp.navitation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.leagueoflegendsapp.articles.NewsScreen
import com.example.leagueoflegendsapp.bookmarks.BookmarksScreen
import com.example.leagueoflegendsapp.detail.DetailViewModel
import com.example.leagueoflegendsapp.detail.NewsDetailScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(NewsRouter)
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
                is NewsRouter -> {
                    NavEntry(
                        key = key,
                    ) {
                        Scaffold(floatingActionButton = {
                            FloatingActionButton(onClick = {
                                backStack.add(BookmarksRouter)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Go to Bookmarks"
                                )
                            }
                        }) { innerPadding ->
                            NewsScreen(modifier = Modifier.padding(innerPadding), onArticleClick = {
                                backStack.add(NewDetailRouter(it))
                            })
                        }

                    }
                }

                is BookmarksRouter -> {
                    NavEntry(
                        key = key,
                    ) {
                        BookmarksScreen(onArticleClick = {
                            backStack.add(NewDetailRouter(it))
                        })
                    }
                }

                is NewDetailRouter -> {
                    NavEntry(
                        key = key,
                    ) {
                        val viewModel = hiltViewModel<DetailViewModel>()
                        NewsDetailScreen(viewModel, key.article)
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}