package za.ac.iie.aweatherapp

object WeatherData {
    val days = mutableListOf(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    )

    // Initialize with default temperatures
    val maxTemps = mutableListOf(25, 29, 22, 24, 20, 18, 16)

    // Add a third array for weather conditions [cite: 3]
    val weatherConditions = mutableListOf(
        "Sunny", "Cloudy", "Rainy", "Sunny", "Cloudy", "Sunny", "Rainy"
    )

    // Ensure all lists are of the same size, pad if necessary for safety
    // (Though in this controlled example, they should match the number of days)
    init {
        val expectedSize = days.size
        while (maxTemps.size < expectedSize) maxTemps.add(0) // Default temp if missing
        while (weatherConditions.size < expectedSize) weatherConditions.add("Not Available") // Default condition
    }
}