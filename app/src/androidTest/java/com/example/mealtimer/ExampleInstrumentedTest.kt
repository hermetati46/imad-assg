package com.example.mealtimer

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import org.junit.Before

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity2>
    @Before
    fun setup() {
// Launch the activity before each test
        activityScenario = ActivityScenario.launch(MainActivity2::class.java)
    }

        @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mealtimer", appContext.packageName)
    }

    fun testButtonClickUpdatesTextView() {
        activityScenario.onActivity { activity ->
// Set up references to UI elements
            val button: Button = activity.findViewById(R.id.buttonSuggest)
            val textView: TextView = activity.findViewById(R.id.textViewSuggestion)
            val editText: EditText = activity.findViewById(R.id.editTextTimeOfDay)
// Simulate user input
            editText.setText("John")
// Perform button click
            button.performClick()
// Check if the TextView's text was updated
            assertEquals("Hello, John! Button was clicked!", textView.text)
            assertEquals(Color.RED, textView.currentTextColor)
        }
    }
}