package com.samcam.calculator

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samcam.calculator.databinding.FragmentButtonBinding
import kotlin.math.log

class ButtonFragment : Fragment() {

    private lateinit var binding: FragmentButtonBinding

    var toDisplay : String = ""

    var activityCallback : ButtonFragment.ButtonListener? = null

    interface ButtonListener {
        fun onButtonClick(send : String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as ButtonListener
        }
        catch (e: java.lang.ClassCastException) {
            throw java.lang.ClassCastException(context.toString() + "must implement ButtonListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtonBinding.inflate(inflater, container, false)

        binding.btn0.setOnClickListener {
            toDisplay = "0"
            buttonClicked(it)
        }
        binding.btn1.setOnClickListener {
            toDisplay = "1"
            buttonClicked(it)
        }
        binding.btn2.setOnClickListener {
            toDisplay = "2"
            buttonClicked(it)
        }
        binding.btn3.setOnClickListener {
            toDisplay = "3"
            buttonClicked(it)
        }
        binding.btn4.setOnClickListener {
            toDisplay = "4"
            buttonClicked(it)
        }
        binding.btn5.setOnClickListener {
            toDisplay = "5"
            buttonClicked(it)
        }
        binding.btn6.setOnClickListener {
            toDisplay = "6"
            buttonClicked(it)
        }
        binding.btn7.setOnClickListener {
            toDisplay = "7"
            buttonClicked(it)
        }
        binding.btn8.setOnClickListener {
            toDisplay = "8"
            buttonClicked(it)
        }
        binding.btn9.setOnClickListener {
            toDisplay = "9"
            buttonClicked(it)
        }

        binding.btnAdd.setOnClickListener {
            toDisplay = "+"
            buttonClicked(it)
        }
        binding.btnSub.setOnClickListener {
            toDisplay = "-"
            buttonClicked(it)
        }
        binding.btnMul.setOnClickListener {
            toDisplay = "*"
            buttonClicked(it)
        }
        binding.btnDiv.setOnClickListener {
            toDisplay = "/"
            buttonClicked(it)
        }
        binding.btnMod.setOnClickListener {
            toDisplay = "%"
            buttonClicked(it)
        }
        binding.btnSquare.setOnClickListener {
            toDisplay = "√"
            buttonClicked(it)
        }
        binding.btnC.setOnClickListener {
            toDisplay = "C"
            buttonClicked(it)
        }
        binding.btnCE.setOnClickListener {
            toDisplay = "CE"
            buttonClicked(it)
        }
        binding.btnEqual.setOnClickListener {
            toDisplay = "="
            buttonClicked(it)
        }
        binding.btnSign.setOnClickListener {
            toDisplay = "+/-"
            buttonClicked(it)
        }
        return binding.root

    }

    private fun buttonClicked (view: View) {
        activityCallback?.onButtonClick(toDisplay)
    }

}