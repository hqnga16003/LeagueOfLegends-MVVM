package com.example.leagueoflegendsapp

import android.util.Log
import com.example.leagueoflegendsapp.champion.ChampionResponseModel
import com.example.leagueoflegendsapp.detail.model.ChampionDetailResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

    private const val BASE_URL = "/cdn/15.13.1/data/vi_VN/"
    const val IMAGE_SPLASH_URL = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/"
    const val IMAGE_LOADING_URL = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/"
    const val IMAGE_SQUARE_URL = "https://ddragon.leagueoflegends.com/cdn/15.13.1/img/champion/"
    const val IMAGE_PASSIVE_URL = "https://ddragon.leagueoflegends.com/cdn/15.13.1/img/passive/"
    const val IMAGE_ABILITY_URL = "https://ddragon.leagueoflegends.com/cdn/15.13.1/img/spell/"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 30000
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
        }

        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "ddragon.leagueoflegends.com"
            }

        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

    }

    suspend fun getAllChampions(): Result<ChampionResponseModel, DataError> {
        return try {
            val res = client.get("${BASE_URL}champion.json")
            when (res.status.value) {
                in 200..299 -> {
                    val data = res.body<ChampionResponseModel>()
                    Result.Success(data)
                }

                else -> {
                    Result.Error(DataError.Network.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    suspend fun getChampionByName(name: String): Result<ChampionDetailResponseModel, DataError> {
        return try {
            val res = client.get("${BASE_URL}champion/${name}.json")
            return when (res.status.value) {
                in 200..299 -> {
                    val data = res.body<ChampionDetailResponseModel>()
                    Result.Success(data)
                }

                else -> {
                    Result.Error(DataError.Network.UNKNOWN)
                }
            }
        } catch (e: Exception){
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}

sealed interface Error

typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>
}

sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT, TOO_MANY_REQUESTS, NO_INTERNET, PAYLOAD_TOO_LARGE, SERVER_ERROR, SERIALIZATION, UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL
    }
}
