package ru.zdanovich.handhSchoolHomework.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.zdanovich.handhSchoolHomework.BuildConfig
import ru.zdanovich.handhSchoolHomework.data.network.interceptors.APIKeyInterceptor
import java.util.concurrent.TimeUnit

object WeatherNetworkModule {
    private const val CONNECT_TIMEOUT_IN_SECONDS = 10L
    private const val WRITE_TIMEOUT_IN_SECONDS = 30L
    private const val READ_TIMEOUT_IN_SECONDS = 30L

    private val httpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(APIKeyInterceptor())
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    val retrofit: Retrofit by lazy {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}