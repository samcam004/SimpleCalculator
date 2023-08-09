package com.samcam.calculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samcam.calculator.databinding.FragmentDisplayBinding
import kotlin.math.round
import kotlin.math.sqrt

class DisplayFragment : Fragment() {

    private lateinit var binding: FragmentDisplayBinding

    var firstNum : String = ""
    var secondNum : String = ""
    var holdFirst : String = ""
    var holdSecond : String = ""
    var operand : String = ""
    var sign : Boolean = false
    var holdDisplay : String = ""
    var holdHint : String = ""
    var holdOp : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDisplayBinding.inflate(inflater, container, false)

        holdDisplay = savedInstanceState?.getString("display").toString()
        holdHint = savedInstanceState?.getString("hint").toString()
        holdFirst = savedInstanceState?.getString("firstNum").toString()
        holdSecond = savedInstanceState?.getString("secondNum").toString()
        holdOp = savedInstanceState?.getString("op").toString()

        if (holdDisplay != null) {
            Log.i("From Display", "$holdDisplay ")
            binding.tvDisplay.text = holdDisplay

            if (binding.tvDisplay.text == "null")
                binding.tvDisplay.text = ""
        }
        if (holdHint.toString() != null) {
            binding.tvDisplay.hint = holdHint
            Log.i("From Hint", "$holdHint")

            if (binding.tvDisplay.hint == "null")
                binding.tvDisplay.hint = ""
            }
        if (holdFirst != "") {
            firstNum = holdFirst
            Log.i("From holdFirst", "$firstNum")
        }
        if (holdSecond != "") {
            secondNum = holdSecond
            Log.i("From holdSecond", "$secondNum")
        }
        if (holdOp != "") {
            operand = holdOp
            Log.i("From holdOp", "$operand")

        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("display", binding.tvDisplay.text.toString())
        outState.putString("hint", binding.tvDisplay.hint.toString())
        outState.putString("firstNum", firstNum)
        outState.putString("secondNum", secondNum)
        outState.putString("op", operand)

    }

    //Converts inputs from ButtonFragment and puts them in the DisplayFragment
    fun buttonInput (input : String) {

        when (input) {
            "=" -> {
                if (firstNum.isNotBlank() && binding.tvDisplay.text.isNotBlank()) {
                    setSecondNum(1)
                    Log.i("Equal", "firstNum = $firstNum & secondNum = $secondNum & op = $operand")
                } else if (operand == "√")
                    compute(firstNum,secondNum,operand)
            }
            "C" -> {
                binding.tvDisplay.text = ""
                binding.tvDisplay.hint = ""
                firstNum = ""
                secondNum = ""
            }
            "CE" -> {
                binding.tvDisplay.text = binding.tvDisplay.text.toString().dropLast(1)
            }
            "+" -> {
                operand = "+"
                if (firstNum.isNotBlank() && secondNum.isBlank()) {
                    Log.i("2", "setup")
                    setSecondNum(2)
                    Log.i("2", "op = $operand & secondNum = $secondNum ")
                }
                else {
                    setFirstNum()
                    Log.i("3", "op = $operand & firstNum = $firstNum ")
                }
            }
            "-" -> {
                operand = "-"
                if (firstNum.isNotBlank() && secondNum.isBlank()) {
                    Log.i("2", "setup")
                    setSecondNum(2)
                    Log.i("2", "op = $operand & secondNum = $secondNum ")
                }
                else {
                    setFirstNum()
                    Log.i("3", "op = $operand & firstNum = $firstNum ")
                }
            }
            "*" -> {
                operand = "*"
                if (firstNum.isNotBlank() && secondNum.isBlank()) {
                    Log.i("2", "setup")
                    setSecondNum(2)
                    Log.i("2", "op = $operand & secondNum = $secondNum ")
                }
                else {
                    setFirstNum()
                    Log.i("3", "op = $operand & firstNum = $firstNum ")
                }
            }
            "/" -> {
                operand = "/"
                if (firstNum.isNotBlank() && secondNum.isBlank()) {
                    Log.i("2", "setup")
                    setSecondNum(2)
                    Log.i("2", "op = $operand & secondNum = $secondNum ")
                }
                else {
                    setFirstNum()
                    Log.i("3", "op = $operand & firstNum = $firstNum ")
                }
            }
            "%" -> {
                operand = "%"
                if (firstNum.isNotBlank() && secondNum.isBlank()) {
                    Log.i("2", "setup")
                    setSecondNum(2)
                    Log.i("2", "op = $operand & secondNum = $secondNum ")
                }
                else {
                    setFirstNum()
                    Log.i("3", "op = $operand & firstNum = $firstNum ")
                }
            }
            "√" -> {
                operand = "√"
                setFirstNum()
                Log.i("Squareroot", "first: $firstNum, op: $operand")
            }
            "+/-" -> {
                if (binding.tvDisplay.text.isNotBlank() && !sign) {
                    binding.tvDisplay.text = "-" + binding.tvDisplay.text
                    sign = true
                } else if (binding.tvDisplay.text.isNotBlank() && sign) {
                    binding.tvDisplay.text = binding.tvDisplay.text.drop(1)
                    sign = false
                }
            }
            else -> {
                if (binding.tvDisplay.text.isBlank()) {
                    binding.tvDisplay.text = input
                    //Log.i("Display", "buttonInput: $input")
                } else {
                    binding.tvDisplay.text = binding.tvDisplay.text.toString() + input
                    //Log.i("Display", "buttonInput: $input")
                }
            }
        }

    }

    //Calculates numbers from the textview in the DisplayFragment
    private fun compute (first : String, second : String, op : String) {
        Log.i("pre-compute", "first: $first, second: $second, op: $op")
        var computation : Double = 0.0

        when (op) {
            "+" -> computation = first.toDouble() + second.toDouble()
            "-" -> computation = first.toDouble() - second.toDouble()
            "*" -> computation = first.toDouble() * second.toDouble()
            "/" -> computation = first.toDouble() / second.toDouble()
            "%" -> computation = first.toDouble() % second.toDouble()
            "√" -> computation = sqrt(first.toDouble())
        }

        firstNum = computation.toString()

        secondNum = ""
        sign = false
        Log.i("post-compute", "computation: $computation, second: $secondNum ")

        if (firstNum.toString().last() == '0') {
            binding.tvDisplay.hint = firstNum.toString().dropLast(2)
        } else
            binding.tvDisplay.hint = firstNum.toString()

    }

    //Takes current number from display and sets it as the first number for a math problem
    private fun setFirstNum () {
        firstNum = if (binding.tvDisplay.text.isBlank()) {
            binding.tvDisplay.hint.toString()
        } else
            binding.tvDisplay.text.toString()

        binding.tvDisplay.text = ""
        binding.tvDisplay.hint = firstNum.toString()
    }

    //Takes current number from the display and sets it as the second number for the math problem
    private fun setSecondNum (mode : Int) {
        when (mode) {
            1 -> {
                secondNum = binding.tvDisplay.text.toString()
                binding.tvDisplay.text = ""
                compute(firstNum, secondNum, operand)
                }
            2 -> {
                secondNum = binding.tvDisplay.text.toString()
                binding.tvDisplay.text = ""
                //compute(firstNum,secondNum,operand)
                }
        }
    }
}
