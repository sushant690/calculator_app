package com.example.calco_olator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var input:TextView?=null
    var lastNumeric:Boolean=false
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         input=findViewById(R.id.textView2)
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=input?.text.toString()
            var prefix=""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                val splitVal=tvValue.split("-")
                var one=splitVal[0]
                var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        prefix= prefix +one
                    }
                input?.text=removeDot((one.toDouble()-two.toDouble()).toString())}
                else if(tvValue.contains("+")){
                    val splitVal=tvValue.split("+")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        prefix= prefix +one
                    }
                    input?.text=removeDot((one.toDouble()+two.toDouble()).toString())}
                else if(tvValue.contains("*")){
                    val splitVal=tvValue.split("*")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        prefix= prefix +one
                    }
                    input?.text=removeDot((one.toDouble()*two.toDouble()).toString())}
                else if(tvValue.contains("/")){
                    val splitVal=tvValue.split("/")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty()){
                        prefix= prefix +one
                    }
                    input?.text=removeDot((one.toDouble()/two.toDouble()).toString())}
            }

                catch (e:java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    fun onDigit(view: View){
        input?.append((view as Button).text)
        lastNumeric=true
        lastDot=false

    }
    fun onCLear(view: View){
        input?.text=""
    }
    fun onDecimal(view: View){
        if(lastNumeric && !lastDot)
        {
            input?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        input?.text?.let{
            if(lastNumeric&&!isOperatorAdded(it.toString())){
                input?.append((view as Button).text)
                lastDot=false
                lastNumeric=false
            }
        }

    }
    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||value.contains("*")||value.contains("+")||value.contains("-")
        }
    }
    private fun removeDot(result:String):String{
        var value=result
        if(value.contains(".0"))
            value=result.substring(0,result.length-2)
        return value
    }
}