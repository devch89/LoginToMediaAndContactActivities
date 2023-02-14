package com.example.mediaandcontactslogin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaandcontactslogin.R
import com.example.mediaandcontactslogin.databinding.MediaLayoutBinding
import com.example.mediaandcontactslogin.services.MusicService

class MediaPlayer: AppCompatActivity() {

    private lateinit var binding: MediaLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MediaLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    private fun playMedia(){
        binding.ivMusicPlayerController.setImageResource(R.drawable.baseline_pause_circle_outline_24)
        val musicMedia = Intent(this, MusicService::class.java)
        startService(musicMedia)
        binding.ivMusicPlayerController.setOnClickListener {
            stopMedia()
        }
    }

    private fun stopMedia(){
        binding.ivMusicPlayerController.setImageResource(R.drawable.baseline_play_circle_outline_24)
        val musicMedia = Intent(this, MusicService::class.java)
        stopService(musicMedia)
        binding.ivMusicPlayerController.setOnClickListener {
            playMedia()
        }
    }

    private fun initViews(){
        binding.apply {
            ivMusicPlayerController.setOnClickListener {
                playMedia()
            }
        }
    }
}