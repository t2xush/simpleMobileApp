package com.example.simpleapp.viewmodel


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapp.ExcusesApi
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.simpleapp.Excuse



class ExcuseViewModel :ViewModel(){
    var excuses= mutableStateListOf<Excuse>()
        private set

    init {
        getExcuseList()
    }

    private fun getExcuseList(){
        viewModelScope.launch {
            var excuseApi: ExcusesApi?=null
            try {
                excuseApi= ExcusesApi!!.getInstance()
                excuses.clear()
                excuses.addAll(excuseApi.getExcuses())
            }catch (e:Exception){
                Log.d("EXCUSEVIEWMODEL",e.message.toString())
            }
        }
    }

}
