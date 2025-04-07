package com.example.mealtimer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.util.Log // Import Log class
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    // Define a TAG constant for logging
    // Best practice: Use the class name
    private val TAG = "MainActivity2"

    // Declare UI element variables
    private lateinit var editTextTimeOfDay: EditText
    private lateinit var buttonSuggest: Button
    private lateinit var buttonReset: Button
    private lateinit var textViewSuggestion: TextView

    // Define the default suggestion text to use for reset
    private lateinit var defaultSuggestionText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Initialize UI elements using findViewById
        editTextTimeOfDay = findViewById(R.id.editTextTimeOfDay)
        buttonSuggest = findViewById(R.id.buttonSuggest)
        buttonReset = findViewById(R.id.buttonReset)
        textViewSuggestion = findViewById(R.id.textViewSuggestion)

        // Store the initial text of the suggestion TextView
        defaultSuggestionText = textViewSuggestion.text.toString()

        // Log that the Activity was created
        Log.d(TAG, "onCreate: Activity created and UI elements initialized.")

        // Set listener for the "Suggest Meal" button
        buttonSuggest.setOnClickListener {
            // Log button click
            Log.d(TAG, "Suggest Meal Button Clicked.")

            // Get user input, trim whitespace
            val timeOfDayInput = editTextTimeOfDay.text.toString().trim()
            Log.i(TAG, "User input received: '$timeOfDayInput'") // Use Log.i for informational messages

            // Check if input is empty
            if (timeOfDayInput.isEmpty()) {
                Log.w(TAG, "Empty input detected.") // Use Log.w for warnings
                textViewSuggestion.text = "Please enter a time of day."
                Toast.makeText(this, "Please enter a time of day.", Toast.LENGTH_SHORT).show()
            } else {
                // Get suggestion based on input
                val suggestion = getMealSuggestion(timeOfDayInput)
                // Display the suggestion
                textViewSuggestion.text = suggestion
                Log.d(TAG, "Suggestion displayed: '$suggestion'")
            }
        }

        // Set listener for the "Reset" button
        buttonReset.setOnClickListener {
            // Log button click
            Log.d(TAG, "Reset Button Clicked.")

            // Clear the EditText
            editTextTimeOfDay.text.clear()
            // Reset the TextView to its default message
            textViewSuggestion.text = defaultSuggestionText

            Log.i(TAG, "Input and suggestion fields reset.")
            Toast.makeText(this, "Input cleared.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Provides a meal suggestion based on the time of day string input.
     * Handles case-insensitivity and provides error message for invalid input.
     * Requirement 2: Meal Suggestion Logic
     * Requirement 3: Error Handling
     *
     * @param timeOfDay The string input from the user (e.g., "Morning").
     * @return A string containing the meal suggestion or an error message.
     */
    private fun getMealSuggestion(timeOfDay: String): String {
        Log.d(TAG, "getMealSuggestion called with input: '$timeOfDay'")

        // Use 'when' statement for cleaner conditional logic (Requirement 2)
        // Convert input to lowercase for case-insensitive comparison
        val suggestion = when (timeOfDay.lowercase()) {
            "morning" -> {
                Log.d(TAG, "Matched 'morning'. Suggesting Breakfast.")
                "Time for Breakfast! How about Eggs?" // Requirement 2.1
            }
            "mid-morning" -> {
                Log.d(TAG, "Matched 'mid-morning'. Suggesting Light snack.")
                "Mid-morning snack! A healthy Fruit?" // Requirement 2.2
            }
            "afternoon" -> {
                Log.d(TAG, "Matched 'afternoon'. Suggesting Lunch.")
                "Lunchtime! Maybe a Sandwich?" // Requirement 2.3
            }
            "mid-afternoon" -> {
                Log.d(TAG, "Matched 'mid-afternoon'. Suggesting Quick bites.")
                "Afternoon snack! Fancy some Cake?" // Requirement 2.4
            }
            "dinner" -> {
                Log.d(TAG, "Matched 'dinner'. Suggesting Main course.")
                "Dinner is served! Pasta sounds good." // Requirement 2.5
            }
            // Added "after dinner snack" as per requirement 2.6
            "after dinner snack" -> {
                Log.d(TAG, "Matched 'after dinner snack'. Suggesting Dessert.")
                "Dessert time! Ice Cream sounds delicious!" // Requirement 2.6
            }
            else -> {
                // Handle invalid input (Requirement 3)
                Log.w(TAG, "Invalid input: '$timeOfDay'. Providing error message.")
                "Please use: Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner, or After Dinner Snack."
            }
        }
        return suggestion
    }

    // Lifecycle logs (Optional but good for understanding)
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed.")
    }
}