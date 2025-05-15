package br.edu.utfpr.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val texto = binding.costOfService.text.toString()

        val cost = texto.toDoubleOrNull() ?: return

        val itemSelecionado = binding.tipOption.checkedRadioButtonId

        val tipPercentage = when (itemSelecionado) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked

        if( roundUp ) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString( R.string.tip_amount_s, formattedTip )

    }
}