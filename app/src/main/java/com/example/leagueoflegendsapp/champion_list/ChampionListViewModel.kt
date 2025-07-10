package com.example.leagueoflegendsapp.champion_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapp.KtorClient
import com.example.leagueoflegendsapp.Result
import com.example.leagueoflegendsapp.champion.ChampionModel
import com.example.leagueoflegendsapp.champion.toChampionList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChampionListViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ChampionListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            when (val res = KtorClient.getAllChampions()) {
                is Result.Success -> {
                    _state.update {
                        it.copy(isLoading = false, champions = res.data.champion.toChampionList())
                    }
                }

                is Result.Error -> {

                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _state.update {
            it.copy(
                searchText = text, filterChampions = it.champions.filter { champion ->
                    champion.name.contains(
                        text, ignoreCase = true
                    )
                })
        }
    }
}


data class ChampionListState(
    val searchText: String = "",
    val champions: List<ChampionModel> = emptyList(),
    val filterChampions: List<ChampionModel> = emptyList(),
    val isLoading: Boolean = false
)