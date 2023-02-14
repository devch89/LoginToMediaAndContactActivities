package com.example.mediaandcontactslogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mediaandcontactslogin.databinding.ActivityMainBinding
import com.example.mediaandcontactslogin.ui.MediaPlayer
import com.example.mediaandcontactslogin.ui.UserContactsActivity

class ChoiceActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    private fun toContacts(){
         val userContacts = Intent(this,UserContactsActivity::class.java)
        startActivity(userContacts)
    }

    private fun toMedia(){
        val playMedia = Intent(this, MediaPlayer::class.java)
        startActivity(playMedia)
    }

   private fun initViews(){
        binding.apply {
            btnToContacts.setOnClickListener {
                toContacts()
            }
            btnToMedia.setOnClickListener {
                toMedia()
            }
        }
    }
}