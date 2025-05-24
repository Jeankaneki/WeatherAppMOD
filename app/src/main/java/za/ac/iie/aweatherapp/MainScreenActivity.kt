package za.ac.iie.aweatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainScreenActivity : AppCompatActivity() {

    // Sample Hardcoded Data
    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val maxTemps = arrayOf(25, 29, 22, 24, 20, 18, 16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val btnEditTemperatures: Button = findViewById(R.id.btnEditTemperatures)
        val btnBackToWelcome: Button = findViewById(R.id.btnBackToWelcome)

        btnEditTemperatures.setOnClickListener {
            val intent = Intent(this, InputScreenActivity::class.java)
            startActivity(intent)
        }

        btnBackToWelcome.setOnClickListener {
            finish() // Close current activity and go back to WelcomeScreen (MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        displayWeatherData() // Refresh data when returning to this screen
    }

    private fun displayWeatherData() {
        // Use a loop to display all days, temperatures, and conditions
        val tvWeatherDetails: TextView = findViewById(R.id.tvWeatherDetails)
        val tvAverageTemp: TextView = findViewById(R.id.tvAverageTemp)

        val weatherDisplay = StringBuilder() // Assuming weatherDisplay is a StringBuilder
        for (i in WeatherData.days.indices) {
            val day = WeatherData.days.getOrElse(i) { "N/A" }
            val temp = WeatherData.maxTemps.getOrElse(i) { 0 }

            // Calculate condition here within the loop
            val condition: String
            if (temp < 10) {
                condition = "Rainy"
            } else if (temp >= 10 && temp <= 20) {
                condition = "Might be Windy"
            } else if (temp > 20 && temp < 30) {
                condition = "Sunny"
            } else {
                condition = "badly Sunny and hotter"
            }

            weatherDisplay.append("$day: $temp°C, $condition\n")
        }
        tvWeatherDetails.text = weatherDisplay.toString()

        // Use a loop to calculate average max temperature
        var totalTemp = 0
        if (WeatherData.maxTemps.isNotEmpty()) {
            for (temp in WeatherData.maxTemps) {
                totalTemp += temp
            }
            val averageTemp = totalTemp.toDouble() / WeatherData.maxTemps.size

            val tvAverageTemp: TextView = findViewById(R.id.tvAverageTemp)
            tvAverageTemp.text = String.format(Locale.getDefault(), "Average Max Temperature: \n %.1f°C", averageTemp)
        } else {
            tvAverageTemp.text = "Average Max Temperature: \n N/A"
        }
    }
}