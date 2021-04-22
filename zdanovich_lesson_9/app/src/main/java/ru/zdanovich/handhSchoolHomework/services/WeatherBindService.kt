package ru.zdanovich.handhSchoolHomework.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import retrofit2.create
import ru.zdanovich.handhSchoolHomework.data.network.WeatherNetworkModule
import ru.zdanovich.handhSchoolHomework.domain.repositories.CityWeatherRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.CityWeatherRepositoryImpl


class WeatherBindService : Service() {
    private val weatherRepository: CityWeatherRepository =
        CityWeatherRepositoryImpl(WeatherNetworkModule.retrofit.create())

    private val binder: IBinder = LocalBinder()
    var serviceCallbacks: WeatherBindServiceCallbacks? = null

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        Log.e(
            this::class.simpleName,
            "CoroutineExceptionHandler got $exception in $coroutineContext"
        )
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO + exceptionHandler)


    inner class LocalBinder : Binder() {
        fun getService(): WeatherBindService {
            return this@WeatherBindService
        }
    }

    override fun onCreate() {
        super.onCreate()
        scope.launch {

            while (this.isActive) {
                val result = weatherRepository.getCityWeatherRepository()
                sentData(result)
                delay(ONE_MINUTE)
            }
        }
    }

    private suspend fun sentData(cityWeatherResult: CityWeatherRepository.CityWeatherResult) =
        withContext(Dispatchers.Main) {
            serviceCallbacks?.updateWeatherInfo(cityWeatherResult)
        }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {
        const val ONE_MINUTE = 60_000L
    }

    interface WeatherBindServiceCallbacks {
        fun updateWeatherInfo(cityWeatherResult: CityWeatherRepository.CityWeatherResult)
    }
}