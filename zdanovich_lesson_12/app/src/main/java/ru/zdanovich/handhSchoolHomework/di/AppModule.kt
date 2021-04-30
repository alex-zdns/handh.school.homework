package ru.zdanovich.handhSchoolHomework.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.zdanovich.handhSchoolHomework.BuildConfig
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.repositories.BridgesRepositoryImpl
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository

@Module
object AppModule {
    @Provides
    @Reusable
    @JvmStatic
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun provideRetrofitService(): Retrofit {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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
