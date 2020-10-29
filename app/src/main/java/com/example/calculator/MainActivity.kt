package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val number1=findViewById<View>(R.id.number1) as EditText
        val number2=findViewById<View>(R.id.number2) as EditText
        val add=findViewById<View>(R.id.add) as CardView
        val subtract=findViewById<View>(R.id.subtract) as CardView
        val divide=findViewById<View>(R.id.divide)as CardView
        val multiple=findViewById<View>(R.id.multiple)as CardView
        val ans=findViewById<View>(R.id.answer) as TextView
        val image=findViewById<View>(R.id.calc_I) as TextView
        val clear=findViewById<View>(R.id.clear) as TextView
        val mapText=findViewById<View>(R.id.map) as TextView

        mapText.setOnClickListener{
            intent= Intent(applicationContext,CurrentLocationActivity::class.java)
            startActivity(intent)
        }

        clear.setOnClickListener{
            number1.setText("")
            number2.setText("")
            ans.text = ""
            image.visibility=View.GONE
        }

            add.setOnClickListener {

                if((number1.text.toString()!="") &&(number2.text.toString()!="")) {
                    val result= (number1.text.toString().trim().toDouble() + number2.text.toString().trim().toDouble() )
                    ans.text= "%.3f".format(result)
                    image.visibility=View.VISIBLE
                    image.text="+"


                }
            }
            subtract.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    val result =(number1.text.toString().trim().toDouble() - number2.text.toString().trim().toDouble())
                    ans.text= "%.3f".format(result)
                    image.visibility=View.VISIBLE
                    image.text="-"

                }
            }
            divide.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    val result =(number1.text.toString().trim().toDouble() / number2.text.toString().trim().toDouble())
                    ans.text= "%.4f".format(result)
                    image.visibility=View.VISIBLE
                    image.text="/"

                }
            }
            multiple.setOnClickListener {
                if((number1.text.toString()!="")&&(number2.text.toString()!="")) {
                    val result= (number1.text.toString().trim().toDouble() * number2.text.toString().trim().toDouble())
                    ans.text= "%.3f".format(result)
                    image.visibility=View.VISIBLE
                    image.text="*"

                }
            }


    }
}

