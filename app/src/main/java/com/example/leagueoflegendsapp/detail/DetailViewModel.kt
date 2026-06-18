package com.example.leagueoflegendsapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapp.model.Article
import com.example.leagueoflegendsapp.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BookmarkRepository
) : ViewModel() {

    val bookmarks =
        repository.getBookmarks()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun saveArticle(
        article: Article
    ) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

    fun removeArticle(
        article: Article
    ) {
        viewModelScope.launch {
            repository.removeArticle(article)
        }
    }
}
