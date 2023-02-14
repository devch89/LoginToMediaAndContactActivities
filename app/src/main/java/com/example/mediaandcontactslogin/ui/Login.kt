package com.example.mediaandcontactslogin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaandcontactslogin.ChoiceActivity
import com.example.mediaandcontactslogin.databinding.LoginBinding

class Login: AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    private fun userLogin(){
        if(binding.etEnterUsername.text.isEmpty() && binding.etEnterPassword.text.isEmpty()){
            Toast.makeText(this,"Please fill out the form", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val main = Intent(this, ChoiceActivity::class.java)
            startActivity(main)
        }
    }

    private fun initViews(){
        binding.apply {
            btnLoginAccount.setOnClickListener{
                userLogin()
        }
        }
    }
}