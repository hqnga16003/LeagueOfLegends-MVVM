package com.example.leagueoflegendsapp.detail

import ChampionDetailModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapp.KtorClient
import com.example.leagueoflegendsapp.Result
import com.example.leagueoflegendsapp.detail.model.toChampionDetailList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor() :
    ViewModel() {
    val champion = mutableStateOf<ChampionDetailModel?>(null)
    fun get(name: String) {
        viewModelScope.launch {
            when (val res = KtorClient.getChampionByName(name)) {
                is Result.Success -> {
                    champion.value = res.data.champion.toChampionDetailList().firstOrNull()
                }

                is Result.Error -> {

                }

            }
        }
    }
}

