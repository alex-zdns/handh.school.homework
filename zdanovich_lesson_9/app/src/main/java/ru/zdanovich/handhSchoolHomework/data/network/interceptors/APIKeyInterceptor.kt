package ru.zdanovich.handhSchoolHomework.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.zdanovich.handhSchoolHomework.BuildConfig

class APIKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}