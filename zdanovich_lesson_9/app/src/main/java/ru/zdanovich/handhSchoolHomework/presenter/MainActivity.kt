package ru.zdanovich.handhSchoolHomework.presenter

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.broadcastReceivers.DownloadReceiver
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeather
import ru.zdanovich.handhSchoolHomework.domain.repositories.CityWeatherRepository
import ru.zdanovich.handhSchoolHomework.services.DownloadService
import ru.zdanovich.handhSchoolHomework.services.WeatherBindService


class MainActivity : AppCompatActivity(), WeatherBindService.WeatherBindServiceCallbacks,
    DownloadReceiver.DownloadBroadcastListener {
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

    private var receiver: DownloadReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupReceiver()

        binding.downloadFileButton.setOnClickListener {
            startDownloadService(binding.downloadFileUrlEditText.text.toString())
        }
    }

    private fun setupReceiver() {
        receiver = DownloadReceiver(this)
        val filter = IntentFilter()
        filter.addAction(DownloadService.ACTION_DOWNLOAD_PROGRESS)
        filter.addAction(DownloadService.ACTION_DOWNLOAD_FINISH)
        filter.addAction(DownloadService.ACTION_DOWNLOAD_ERROR)
        this.registerReceiver(receiver, filter)
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
        receiver?.listener = null
        unregisterReceiver(receiver)
        _binding = null
        super.onDestroy()
    }

    override fun updateWeatherInfo(cityWeatherResult: CityWeatherRepository.CityWeatherResult) =
        when (cityWeatherResult) {
            is CityWeatherRepository.CityWeatherResult.Error -> showError()
            is CityWeatherRepository.CityWeatherResult.Success -> showWeather(cityWeatherResult.cityWeather)
        }

    override fun updateProgressBar(progress: Int) {
        binding.progressBar.progress = progress
    }

    override fun downloadFinish(imageUri: Uri) {
        binding.openImageButton.apply {
            isEnabled = true
            setOnClickListener {
                val intent = Intent(this@MainActivity, ShowImageActivity::class.java).apply {
                    putExtra(ShowImageActivity.KEY_IMAGE_URI, imageUri)
                }

                startActivity(intent)
            }
        }
    }


    override fun downloadError() {
        Toast.makeText(this, getString(R.string.download_error_message), Toast.LENGTH_LONG).show()
    }
}