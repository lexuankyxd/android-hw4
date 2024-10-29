package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currencyConversionRates = arrayOf(
            // From VND, USD, EUR, JPY
            doubleArrayOf(1.0, 0.000039, 0.00003657, 0.005943), // VND
            doubleArrayOf(25365.0, 1.0, 0.92, 149.535),        // USD
            doubleArrayOf(27400.0, 1.087, 1.0, 162.505),      // EUR
            doubleArrayOf(168.249, 0.0067, 0.0061, 1.0)       // JPY
        )
        var c1 = 0
        var c2 = 0
        var input = 0.0
        var output = 0.0
        val textbox = findViewById<TextView>(R.id.output)

        val adapter = ArrayAdapter.createFromResource(this, R.array.choices, android.R.layout.simple_spinner_item).apply{
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        spinner1.adapter = adapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Get the selected item
                c1 = position
                output = input * currencyConversionRates[c1][c2]
                textbox.text = output.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case where no item is selected if necessary
            }
        }

        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        spinner2.adapter = adapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Get the selected item
                c2 = position
                output = input * currencyConversionRates[c1][c2]
                textbox.text = output.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case where no item is selected if necessary
            }
        }




        findViewById<EditText>(R.id.input).addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.d("TAG", "afterTextChanged: hi")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("TAG", "beforeTextChanged: hi")
            }
            override fun onTextChanged(s: CharSequence, start: Int, befor: Int, count: Int){
                if(s.length == 0) return;
                input =  s.toString().toDouble()
                output = input * currencyConversionRates[c1][c2]
                textbox.text = output.toString()
            }
        })
    }
}