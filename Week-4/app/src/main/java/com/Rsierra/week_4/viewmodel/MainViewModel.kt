package com.Rsierra.week_4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _listaPalabras = MutableLiveData<ArrayList<String>>(arrayListOf())
    val listaPalabras : LiveData<ArrayList<String>> = _listaPalabras

    fun getWords() {
        val listaDePalabras: List<String> = listOf(
            "esencia",
            "neblina",
            "pañuelo",
            "recital",
            "regreso",
            "barullo",
            "caballo",
            "cerrado",
            "grisaceo",
            "mascara",
        )

        _listaPalabras.value?.clear()
        _listaPalabras.value?.addAll(listaDePalabras.toTypedArray())
    }
}