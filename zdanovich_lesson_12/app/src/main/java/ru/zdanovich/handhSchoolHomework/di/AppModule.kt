package ru.zdanovich.handhSchoolHomework.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.zdanovich.handhSchoolHomework.BuildConfig
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.repositories.BridgesRepositoryImpl
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository
import java.util.concurrent.TimeUnit

@Module
object AppModule {
    @Provides
    @Reusable
    @JvmStatic
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun provideRetrofitService(client: OkHttpClient): Retrofit {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideBridgeApi(retrofit: Retrofit): BridgeApi {
        return retrofit.create(BridgeApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideBridgeRepository(api: BridgeApi): BridgesRepository {
        return BridgesRepositoryImpl(api)
    }
}
