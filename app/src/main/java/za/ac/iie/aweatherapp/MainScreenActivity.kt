package za.ac.iie.aweatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainScreenActivity : AppCompatActivity() {

    // Sample Hardcoded Data [cite: 5]
    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday") // [cite: 5]
    private val maxTemps = arrayOf(25, 29, 22, 24, 20, 18, 16) // [cite: 5]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val tvWeatherDetails: TextView = findViewById(R.id.tvWeatherDetails)
        val tvAverageTemp: TextView = findViewById(R.id.tvAverageTemp)
        val btnBack: Button = findViewById(R.id.btnBack)

        // Use a loop to display all days and temperatures [cite: 5, 6]
        val weatherDisplay = StringBuilder()
        for (i in days.indices) {
            weatherDisplay.append("${days[i]}: ${maxTemps[i]}°C\n")
        }
        tvWeatherDetails.text = weatherDisplay.toString()

        // Use a loop to calculate average max temperature [cite: 5, 6]
        var totalTemp = 0
        for (temp in maxTemps) {
            totalTemp += temp
        }
        val averageTemp = if (maxTemps.isNotEmpty()) totalTemp.toDouble() / maxTemps.size else 0.0
        // Format to one decimal place
        tvAverageTemp.text = String.format(Locale.getDefault(), "Average Max Temperature:\n %.1f°C", averageTemp)


        btnBack.setOnClickListener {
            // Intent to go back to MainActivity (Welcome Screen)
            finish()
        }
    }
}