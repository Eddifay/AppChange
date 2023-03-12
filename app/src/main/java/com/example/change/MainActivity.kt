package com.example.change


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/



    private lateinit var amountEditText: EditText
    private lateinit var madButton: Button
    private lateinit var eurButton: Button
    private lateinit var usdButton: Button
    private lateinit var eurTextView: TextView
    private lateinit var usdTextView: TextView

    private val exchangeRatesMap = mapOf(
        "MAD" to mapOf("EUR" to 0.091, "USD" to 0.11),
        "EUR" to mapOf("MAD" to 10.97, "USD" to 1.209),
        "USD" to mapOf("MAD" to 9.09, "EUR" to 0.826)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.editTextAmount)
        madButton = findViewById(R.id.buttonMAD)
        eurButton = findViewById(R.id.buttonEUR)
        usdButton = findViewById(R.id.buttonUSD)
        eurTextView = findViewById(R.id.textViewEUR)
        usdTextView = findViewById(R.id.textViewUSD)

        fun convertCurrency(fromCurrency: String, toCurrency: String) {
            val amountString = amountEditText.text.toString()
            if (amountString.isEmpty()) {
                return
            }
            val amount = amountString.toDouble()
            val exchangeRate = exchangeRatesMap[fromCurrency]?.get(toCurrency) ?: return
            val result = amount * exchangeRate
            when (toCurrency) {
                "EUR" -> {
                    eurTextView.text = String.format("In EUR %.2f", result)
                    usdTextView.text = String.format("In USD %.2f", result / exchangeRatesMap[fromCurrency]?.get("USD")!!)
                }
                "USD" -> {
                    usdTextView.text = String.format("In USD %.2f", result)
                    eurTextView.text = String.format("In EUR %.2f", result / exchangeRatesMap[fromCurrency]?.get("EUR")!!)
                }
                else -> return
            }
        }

        madButton.setOnClickListener {
            convertCurrency("MAD", "EUR")
        }

        eurButton.setOnClickListener {
            convertCurrency("EUR", "USD")
        }

        usdButton.setOnClickListener {
            convertCurrency("USD", "MAD")
        }
    }
}