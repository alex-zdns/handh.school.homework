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
import ru.zdanovich.handhSchoolHomework.data.apiService.BridgeApiServiceImpl
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.domain.apiService.BridgeApiService
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepositoryImpl
import java.util.concurrent.TimeUnit

@Module
object AppModule {
    private const val CONNECT_TIMEOUT_IN_SECONDS = 10L
    private const val WRITE_TIMEOUT_IN_SECONDS = 30L
    private const val READ_TIMEOUT_IN_SECONDS = 30L

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
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
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
    fun provideBridgeApiService(api: BridgeApi): BridgeApiService {
        return BridgeApiServiceImpl(api)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideBridgeRepository(apiService: BridgeApiService): BridgesRepository {
        return BridgesRepositoryImpl(apiService)
    }
}
