package com.example .databinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title="Lotus App"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var viewModel=ViewModel(binding)
        viewModel.setupListeners()
        binding.loginButton.setOnClickListener {
            if (viewModel.isValidate()) {
                Toast.makeText(this, "validated", Toast.LENGTH_SHORT).show()
            }
        }
    }

}