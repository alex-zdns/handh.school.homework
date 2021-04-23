package ru.zdanovich.handhSchoolHomework.presenter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeather
import ru.zdanovich.handhSchoolHomework.domain.repositories.CityWeatherRepository
import ru.zdanovich.handhSchoolHomework.services.DownloadService
import ru.zdanovich.handhSchoolHomework.services.WeatherBindService

class MainActivity : AppCompatActivity(), WeatherBindService.WeatherBindServiceCallbacks {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var weatherBindService: WeatherBindService? = null
    private var isBound = false

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            isBound = true
            weatherBindService = (binder as? WeatherBindService.LocalBinder)?.getService()
            weatherBindService?.serviceCallbacks = this@MainActivity
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            weatherBindService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.downloadFileButton.setOnClickListener {
            startDownloadService(binding.downloadFileUrlEditText.text.toString())
        }
    }

    private fun startDownloadService(url: String) {
        val startServiceIntent = Intent(this, DownloadService::class.java)
        startServiceIntent.putExtra(DownloadService.URL_KEY, url)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(startServiceIntent)
        } else {
            startService(startServiceIntent)
        }
    }


    override fun onStart() {
        super.onStart()
        val intent = Intent(this, WeatherBindService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun showWeather(weather: CityWeather) {
        binding.cityWeather.text = getString(
            R.string.city_weather_info,
            weather.cityName,
            weather.temp,
            weather.feelsLikeTemp
        )
    }

    private fun showError() {
        Toast.makeText(this, "Произошла ошибка при получение данных", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

        if (isBound) {
            weatherBindService?.serviceCallbacks = null
            unbindService(serviceConnection)
            isBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun updateWeatherInfo(cityWeatherResult: CityWeatherRepository.CityWeatherResult) =
        when (cityWeatherResult) {
            is CityWeatherRepository.CityWeatherResult.Error -> showError()
            is CityWeatherRepository.CityWeatherResult.Success -> showWeather(cityWeatherResult.cityWeather)
        }
}