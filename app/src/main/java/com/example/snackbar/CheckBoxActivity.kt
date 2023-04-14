package com.example.snackbar

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.snackbar.databinding.ActivityCheckBoxBinding

class CheckBoxActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCheckBoxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chk1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Juice is Checked", Toast.LENGTH_SHORT).show()
        }

        binding.chk2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Coldrink is Checked", Toast.LENGTH_SHORT).show()
        }

        binding.chk3.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Icream is Checked", Toast.LENGTH_SHORT).show()
        }

        binding.chk4.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Water is Checked", Toast.LENGTH_SHORT).show()
        }
    }


}