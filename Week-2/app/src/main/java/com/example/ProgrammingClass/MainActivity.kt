package com.example.ProgrammingClass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import com.example.ProgrammingClass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btAction = binding.button
        val radio = binding.radioGroup
        val value1 = binding.val1.text.toString()
        val value2 = binding.val2.text.toString()
        val value3 = binding.val3.text.toString()

        var radioButton: String = ""

        radio.setOnCheckedChangeListener { _, checkedId ->
            val radioSelected: RadioButton = findViewById(checkedId)
            radioButton = radioSelected.text.toString()
        }

        btAction.setOnClickListener {
            if (radioButton == ""){
                Toast.makeText(this, "No se tiene ninguna opcion seleccionada", Toast.LENGTH_LONG).show()
            }else if (value1 == "" || value2 == "" || value3 == ""){
                Toast.makeText(this, "Se tienen que llenar todos los campos de valores", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this, "Se tiene un valor", Toast.LENGTH_LONG).show()
            }
        }
    }
}