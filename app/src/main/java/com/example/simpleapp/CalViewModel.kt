package com.example.simpleapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalViewModel : ViewModel() {

    var firstInput: String by mutableStateOf("")
    var secondInput: String by mutableStateOf("")
    private var add: MutableState<Float> = mutableStateOf(0.0f)
    private var sub: MutableState<Float> = mutableStateOf(0.0f)
    private var mul: MutableState<Float> = mutableStateOf(0.0f)
    private var div: MutableState<Float> = mutableStateOf(0.0f)
    val addfun: State<Float>
        get() = add

    val subfun: State<Float>
        get() = sub
    val mulfun: State<Float>
        get() = mul
    val divfun: State<Float>
        get() = div

    fun updateFirstNumber(firstNumber: String) {
        firstInput = firstNumber.replace(',', '.')
        calculate()
    }

    fun updateSecondNumber(secondNumber: String) {
        secondInput = secondNumber.replace(',', '.')
        calculate()
    }

    private fun calculate() {
        val firstNumber = firstInput.toFloatOrNull()
        val secondNumber = secondInput.toFloatOrNull()

        if (firstNumber != null && secondNumber != null && secondNumber != 0.0f) {
            add.value = firstNumber + secondNumber
            sub.value = firstNumber - secondNumber
            mul.value = firstNumber * secondNumber
            div.value = firstNumber / secondNumber
        } else {

            add.value = 0.0f
            sub.value = 0.0f
            mul.value = 0.0f
            div.value = Float.NaN
        }
    }
}
