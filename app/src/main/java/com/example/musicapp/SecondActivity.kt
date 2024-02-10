package com.example.musicapp

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    var totalTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val img = findViewById<ImageView>(R.id.songImage)
        val tittle = findViewById<TextView>(R.id.songTittle)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val btnPlay = findViewById<ImageView>(R.id.play)
        val btnPause = findViewById<ImageView>(R.id.pause)
        val btnStop = findViewById<ImageView>(R.id.stop)

        val image = intent.getStringExtra("cover")
        val songtittle = intent.getStringExtra("tittle")

        tittle.text = songtittle

//        val mediaPlayer = MediaPlayer.create(this)

        btnPlay.setOnClickListener {
        }

        btnPause.setOnClickListener {

        }

        btnStop.setOnClickListener {

        }

    }
}

