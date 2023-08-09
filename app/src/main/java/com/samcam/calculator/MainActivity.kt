package com.samcam.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samcam.calculator.databinding.ActivityMainBinding
import com.samcam.calculator.databinding.FragmentDisplayBinding

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    override fun onButtonClick(input : String) {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.landDisplayFragment)
                as DisplayFragment
        displayFragment.buttonInput(input)
    }

}