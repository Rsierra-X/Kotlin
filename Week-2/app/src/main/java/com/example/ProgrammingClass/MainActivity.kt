package com.example.ProgrammingClass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.ProgrammingClass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btAction = binding.button
        val radio = binding.radioGroup

        btAction.setOnClickListener {
            val radioButtonId = radio.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(radioButtonId)?.text.toString()

            val value1Text = binding.val1.text.toString()
            val value2Text = binding.val2.text.toString()
            val value3Text = binding.val3.text.toString()

            if (radioButton.isEmpty()) {
                Toast.makeText(this, "No se ha seleccionado ninguna opción", Toast.LENGTH_LONG).show()
            } else if (value1Text.isEmpty() || value2Text.isEmpty() || value3Text.isEmpty()) {
                Toast.makeText(this, "Se tienen que llenar todos los campos de valores numéricos", Toast.LENGTH_LONG).show()
            } else {
                val value1 = value1Text.toDouble()
                val value2 = value2Text.toDouble()
                val value3 = value3Text.toDouble()

                val result = when (radioButton) {
                    "suma" -> sumar(value1, value2, value3)
                    "promedio" -> calcularPromedio(value1, value2, value3)
                    "numero mayor" -> encontrarNumeroMayor(value1, value2, value3)
                    "numero menor" -> encontrarNumeroMenor(value1, value2, value3)
                    else -> null
                }

                if (result != null) {
                    Toast.makeText(this, "El resultado es: $result", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun sumar(num1: Double, num2: Double, num3: Double): Double {
        return num1 + num2 + num3
    }

    fun calcularPromedio(num1: Double, num2: Double, num3: Double): Double {
        return (num1 + num2 + num3) / 3.0
    }

    fun encontrarNumeroMayor(num1: Double, num2: Double, num3: Double): Double {
        return maxOf(num1, num2, num3)
    }

    fun encontrarNumeroMenor(num1: Double, num2: Double, num3: Double): Double {
        return minOf(num1, num2, num3)
    }
}