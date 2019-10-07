package com.example.notetaker.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notetaker.R

class NoteListActivity : AppCompatActivity() {

    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(applicationContext.packageName, Context.MODE_PRIVATE)
    }
    lateinit var textColor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        // get the color that was stored in the shared preferences file
        textColor = sharedPreferences.getString(COLOR_KEY, DEFAULT_COLOR) ?: DEFAULT_COLOR

        Toast.makeText(this, "Color stored was $textColor", Toast.LENGTH_SHORT).show()
        // ...

    }
}
