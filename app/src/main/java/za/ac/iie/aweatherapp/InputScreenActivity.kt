package za.ac.iie.aweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.apply
import kotlin.collections.forEachIndexed
import kotlin.collections.getOrElse
import kotlin.collections.getOrNull
import kotlin.text.toIntOrNull

class InputScreenActivity : AppCompatActivity() {

    private lateinit var llTemperatureInputs: LinearLayout
    private val editTextList = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_screen)

        llTemperatureInputs = findViewById(R.id.llTemperatureInputs)
        val btnSaveChanges: Button = findViewById(R.id.btnSaveChanges)
        val btnCancelInput: Button = findViewById(R.id.btnCancelInput)

        // Dynamically create EditText for each day
        WeatherData.days.forEachIndexed { index, day ->
            val dayLabel = TextView(this).apply {
                text = "$day:"
                textSize = 16f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = 8 }
            }
            llTemperatureInputs.addView(dayLabel)

            val tempInput = EditText(this).apply {
                id = index // Optional: set an ID if needed for other purposes
                inputType = InputType.TYPE_CLASS_NUMBER // For numeric input
                setText(WeatherData.maxTemps.getOrNull(index)?.toString() ?: "0")
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            editTextList.add(tempInput)
            llTemperatureInputs.addView(tempInput)
        }

        btnSaveChanges.setOnClickListener {
            saveTemperatureChanges()
            Toast.makeText(this, "Temperatures Updated!", Toast.LENGTH_SHORT).show()
            finish() // Close this screen and go back to MainScreen
        }

        btnCancelInput.setOnClickListener {
            finish() // Just close the screen without saving
        }
    }

    private fun saveTemperatureChanges() {
        editTextList.forEachIndexed { index, editText ->
            val newTempString = editText.text.toString()
            val newTemp = newTempString.toIntOrNull() ?: WeatherData.maxTemps.getOrElse(index) { 0 } // Keep old if invalid
            if (index < WeatherData.maxTemps.size) {
                WeatherData.maxTemps[index] = newTemp
            }
        }
    }
}