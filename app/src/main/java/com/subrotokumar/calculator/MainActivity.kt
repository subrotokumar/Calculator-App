package com.subrotokumar.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.InputDevice
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var lastDecimal:Boolean=false
    var lastDigit:Boolean=true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val tvInput=findViewById<TextView>(R.id.tvInput)
        if(tvInput.text.toString().equals("0"))
            tvInput.text=""
        tvInput.append((view as Button).text)
        lastDigit=true
    }
    fun onClear(view:View){
        val tvInput=findViewById<TextView>(R.id.tvInput)
        tvInput.text="0"
        lastDecimal=false
        lastDigit=true
    }
    fun onDecimal(view: View)
    {
        val tvInput=findViewById<TextView>(R.id.tvInput)
        if(!lastDecimal && lastDigit) {
            tvInput.append(".")
            lastDecimal=true
        }
    }
    fun onOperation(view: View)
    {
        val tvInput=findViewById<TextView>(R.id.tvInput)
        val operator:String=tvInput.text.toString()
        if((operator.length>1) && ( operator.endsWith("-") || operator.endsWith("+") || operator.endsWith("÷") || operator.endsWith("×") ) ) {
            tvInput.text = operator.substring(0, operator.length - 1)
            tvInput.append((view as Button).text)
            lastDigit = false
            lastDecimal = false
        }
        if(lastDigit && !isOperatorAdded(operator))
        {
            tvInput.append((view as Button).text)
                lastDigit=false
                lastDecimal=false
        }
    }
    private fun isOperatorAdded(value:String):Boolean
    {
        if(value.endsWith("-")||value.contains("+")||value.contains("÷")||value.contains("×")||value.indexOf("-")!=value.lastIndexOf("-"))
        {
            return true
        }
        else
            return false
    }
    private fun zeroEliminate(num : String) : String
    {
        if(num.endsWith(".0"))
            return num.substring(0,num.length-2)
        return num
    }
    fun onEqual(view: View)
    {
        val tvInput=findViewById<TextView>(R.id.tvInput)
        var value=tvInput.text.toString()
        var prefix=""
        if(value.startsWith("-")) {
            prefix = "-"
            value = value.substring(1)
        }
        if(lastDigit)
        {
            try {

                if (value.contains("÷")) {
                    var s = value.split("÷")

                    var one = s[0]
                    var two = s[1]

                    //if (!prefix.isEmpty())
                    //    one = prefix + one
                    tvInput.text = zeroEliminate((one.toDouble()/two.toDouble()).toString())
                } else if (value.contains("×")) {
                    var s = value.split("×")

                    var one = s[0]
                    var two = s[1]

                    if (!prefix.isEmpty())
                        one = prefix + one
                    tvInput.text = zeroEliminate((one.toDouble()*two.toDouble()).toString())
                } else if (value.contains("+")) {
                    var s = value.split("+")

                    var one = s[0]
                    var two = s[1]

                    if (!prefix.isEmpty())
                        one = prefix + one
                    tvInput.text = zeroEliminate((one.toDouble()+two.toDouble()).toString())
                }
                if (value.contains("-")) {
                    var s = value.split("-")

                    var one = s[0]
                    var two = s[1]

                    if (!prefix.isEmpty())
                        one = prefix + one
                    tvInput.text = zeroEliminate((one.toDouble()-two.toDouble()).toString())
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onErase(view: View) {
        val tvInput=findViewById<TextView>(R.id.tvInput)
        val text=tvInput.text;
        if(text.length==1)
            tvInput.text="0"
        else
            tvInput.text=text.substring(0,text.length-1)
    }
}