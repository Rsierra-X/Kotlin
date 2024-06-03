package com.Rsierra.week_4.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.Rsierra.week_4.databinding.ActivityMainBinding
import com.Rsierra.week_4.viewmodel.MainViewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //always create the binding, to control ActivityMain components
    lateinit var binding: ActivityMainBinding;
    lateinit var viewModel: MainViewModel
    lateinit var wordList: List<String>
    var randomWord: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.listaPalabras.observe(this) {
            wordList = it;
            generateRandomWord();
        }
        viewModel.getWords();
        setContentView(binding.root)
    }

    private fun generateRandomWord(){
        val randomPositionWord = Random.nextInt(from = 3, until = 6).let { wordList[it] }

        binding.button.text = randomPositionWord
    }
}