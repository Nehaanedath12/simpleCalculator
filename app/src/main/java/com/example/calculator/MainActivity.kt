package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val number1=findViewById<View>(R.id.number1) as EditText
        val number2=findViewById<View>(R.id.number2) as EditText
        val add=findViewById<View>(R.id.add) as TextView
        val subtract=findViewById<View>(R.id.subtract) as TextView
        val divide=findViewById<View>(R.id.divide)as TextView
        val multiple=findViewById<View>(R.id.multiple)as TextView
        val ans=findViewById<View>(R.id.answer) as TextView
        val image=findViewById<View>(R.id.calc_I) as TextView
        val clear=findViewById<View>(R.id.clear) as TextView

        clear.setOnClickListener{
            number1.setText("")
            number2.setText("")
            ans.text = ""
        }

            add.setOnClickListener {

                if((number1.text.toString()!="") &&(number2.text.toString()!="")) {
                    ans.text = (number1.text.toString().trim().toDouble() + number2.text.toString().trim().toDouble() ).toString()
                    image.visibility=View.VISIBLE
                    image.text="+"


                }
            }
            subtract.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    ans.text =(number1.text.toString().trim().toDouble() - number2.text.toString().trim().toDouble()).toString()
                    image.visibility=View.VISIBLE
                    image.text="-"

                }
            }
            divide.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    ans.text =(number1.text.toString().trim().toDouble() / number2.text.toString().trim().toDouble()).toString()
                    image.visibility=View.VISIBLE
                    image.text="/"

                }
            }
            multiple.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    ans.text = (number1.text.toString().trim().toDouble() * number2.text.toString().trim().toDouble()).toString()
                    image.visibility=View.VISIBLE
                    image.text="*"

                }            }


    }
}

