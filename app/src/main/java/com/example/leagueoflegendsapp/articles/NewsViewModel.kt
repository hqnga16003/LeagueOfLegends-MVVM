package com.example.leagueoflegendsapp.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapp.model.Article
import com.example.leagueoflegendsapp.repository.NewsRepository
import com.example.leagueoflegendsapp.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        NewsUiState(isLoading = true)
    )
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkMonitor.isOnline.collectLatest { isOnline ->
                if (isOnline) {
                    loadNews()
                } else if (_uiState.value.articles.isEmpty()) {
                    loadNews()
                }
            }
        }
    }

    fun loadNews() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val articles = repository.getArticles()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        articles = articles,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    fun refresh() {
        loadNews()
    }
}

data class NewsUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)
