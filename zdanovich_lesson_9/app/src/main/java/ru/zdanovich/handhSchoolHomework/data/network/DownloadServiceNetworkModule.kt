package ru.zdanovich.handhSchoolHomework.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object DownloadServiceNetworkModule {
    private const val CONNECT_TIMEOUT_IN_SECONDS = 10L
    private const val WRITE_TIMEOUT_IN_SECONDS = 30L
    private const val READ_TIMEOUT_IN_SECONDS = 30L

    val httpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }
}