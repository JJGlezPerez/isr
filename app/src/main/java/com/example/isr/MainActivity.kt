package com.example.isr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.isr.model.Bruto
import com.example.isr.model.Neto
import com.example.isr.model.Sueldo
import com.example.isr.model.SueldoA
import com.example.isr.ui.theme.ISRTheme


class MainActivity : ComponentActivity() {
    lateinit var fromRG: RadioGroup
    lateinit var toRG: RadioGroup
    lateinit var convertB: Button
    lateinit var periodo: Spinner
    lateinit var entry: EditText
    var per : String = ""
    var number : Double = 0.0
    lateinit var sueldo : Sueldo
    lateinit var sueldoBrutoTextView: TextView
    lateinit var limiteInferiorTextView: TextView
    lateinit var porcentajeTextView: TextView
    lateinit var impuestoMarginalTextView: TextView
    lateinit var cuotaFijaTextView: TextView
    lateinit var isrDeterminadoTextView: TextView
    lateinit var subsidioTextView: TextView
    lateinit var ingresoNetoTextView:TextView
    lateinit var ingresoNetoConSubsidioTextView:TextView

    var from: String = "bruto"
    var to:String = "neto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mainactivity)
        sueldoBrutoTextView = findViewById<TextView>(R.id.sb)
        limiteInferiorTextView = findViewById<TextView>(R.id.li)
        porcentajeTextView = findViewById<TextView>(R.id.por)
        impuestoMarginalTextView = findViewById<TextView>(R.id.im)
        cuotaFijaTextView = findViewById<TextView>(R.id.cf)
        isrDeterminadoTextView = findViewById<TextView>(R.id.isr)
        subsidioTextView = findViewById<TextView>(R.id.sub)
        ingresoNetoTextView = findViewById<TextView>(R.id.sn)
        ingresoNetoConSubsidioTextView = findViewById<TextView>(R.id.sns)
        inicializarComponents()
        setListeners()
    }

    fun inicializarComponents() {
        fromRG = findViewById(R.id.from)
        toRG = findViewById(R.id.to)
        convertB = findViewById(R.id.calc)
        entry = findViewById(R.id.value)
        periodo = findViewById(R.id.period)

        configurarSpinner(periodo)

        (fromRG.getChildAt(0) as RadioButton).isChecked = true
        (toRG.getChildAt(1) as RadioButton).isChecked = true
    }

    fun setListeners() {

        configurarListener(periodo)

        setConvertButtonListener(convertB)

        for (i in 0 until fromRG.childCount) {
            var radioButton = fromRG.getChildAt(i)
            var radioButtonId = resources.getResourceName(radioButton.id)
            var medida = radioButtonId.split('_')[1]
            setRadioFromClickListener(medida, radioButton as RadioButton)
        }

        for (i in 0 until toRG.childCount) {
            var radioButton = toRG.getChildAt(i)
            var radioButtonId = resources.getResourceName(radioButton.id)
            var medida = radioButtonId.split('_')[1]
            setRadioToClickListener(medida, radioButton as RadioButton)

        }

    }

    private fun configurarSpinner(spinner: Spinner) {
        val items = arrayOf("Diario", "Semanal", "Decenal", "Quincenal","Mensual")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

    }

    private fun configurarListener(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                per = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                return
            }
        }
    }


    fun setRadioFromClickListener(conv:String,radioButton: RadioButton){
        radioButton.setOnClickListener{
            this.from=conv
        }
    }

    fun setRadioToClickListener(conv:String,radioButton: RadioButton){
        radioButton.setOnClickListener{
            this.to=conv
        }
    }
    fun setConvertButtonListener(button:Button){
        button.setOnClickListener{
            if (entry.text.isNotEmpty()) {
                number = entry.text.toString().toDouble()
            }
            calcular()
        }
    }

    fun calcular(){
        if (number == 0.0 || per.isEmpty()) {
            Log.e("ResultActivity", "Valio vrga señor barriga")
            return
        }

        sueldo= when(from){
            "bruto"->{
                when(to){
                    "neto"->calcBruto()
                    else -> throw IllegalArgumentException("Conversión no soportada: $from a $to")

                }
            }
            "neto"->{
                when(to){
                    "bruto"->calcNeto()
                    else -> throw IllegalArgumentException("Conversión no soportada: $from a $to")
                }
            }
            else -> throw IllegalArgumentException("Tipo de sueldo no soportado: $from")
        }
        mostrar()
    }

    fun calcBruto():Sueldo{
        var bruto = Bruto (number, per)
        bruto.calc()
        return bruto
    }

    fun calcNeto():Sueldo{
        var neto = Neto(number, per)
        neto.calc()
        return neto
    }

    fun mostrar(){
        val sueldoBruto = sueldo.sueldoB
        val limiteInferior = sueldo.li
        val porcentajeExcedente = sueldo.tasa
        val impuestoMarginal = sueldo.im
        val cuotaFija = sueldo.cf
        val isrDeterminado = sueldo.isrc
        val subsidio = sueldo.sub
        val ingresoNeto = sueldo.sueldoN
        val ingresoNetoConSubsidio = sueldo.sueldoNS


        sueldoBrutoTextView.text = "$sueldoBruto"
        limiteInferiorTextView.text = "$limiteInferior"
        porcentajeTextView.text = "$porcentajeExcedente %"
        impuestoMarginalTextView.text = "$impuestoMarginal"
        cuotaFijaTextView.text = "$cuotaFija"
        isrDeterminadoTextView.text = "$isrDeterminado"
        subsidioTextView.text = "$subsidio"
        ingresoNetoTextView.text = "$ingresoNeto"
        ingresoNetoConSubsidioTextView.text = "$ingresoNetoConSubsidio"
    }


}
