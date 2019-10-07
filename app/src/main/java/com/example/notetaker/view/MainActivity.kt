package com.example.notetaker.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaker.R
import com.example.notetaker.adapter.ColorAdapter
import com.example.notetaker.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.color_item_view_layout.*

const val COLOR_KEY = "notetaker.color"
const val DEFAULT_COLOR = "WHITE"

class MainActivity : AppCompatActivity(), ColorAdapter.colorAdapterDelegator {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    override fun colorPicked(colorResource: String) {
        spEditor.putString(COLOR_KEY, colorResource)
        spEditor.apply()
        Intent(this, NoteListActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = this.getSharedPreferences(
            applicationContext.packageName,
            Context.MODE_PRIVATE
        )
        spEditor = sharedPreferences.edit()

        val colorRetrieved = sharedPreferences.getString(COLOR_KEY, DEFAULT_COLOR) ?: DEFAULT_COLOR

        setUpRV()
    }

    fun setUpRV() {
        color_recyclerview.adapter = ColorAdapter (
            mutableListOf(
                Constants.COLOR_BLUE,
                Constants.COLOR_YELLOW,
                Constants.COLOR_WHITE
            ), this
        )

        color_recyclerview.layoutManager = LinearLayoutManager(this)
        color_recyclerview.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    fun setUpColor(colorPicked: String?) {
        when (colorPicked) {
            Constants.COLOR_BLUE -> color_textview.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.appBlue
                )
            )
            Constants.COLOR_YELLOW -> color_textview.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.appYellow
                )
            )
            Constants.COLOR_WHITE -> color_textview.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.appWhite
                )
            )
        }
    }
}

