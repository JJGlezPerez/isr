package com.example.isr

import android.os.Bundle
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
import com.example.isr.model.Sueldo
import com.example.isr.model.SueldoA
import com.example.isr.ui.theme.ISRTheme

class ResultActivity : ComponentActivity() {
    val s = intent.getSerializableExtra("sueldo") as SueldoA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultactivity)

        val sueldoBrutoTextView = findViewById<TextView>(R.id.sb)
        val limiteInferiorTextView = findViewById<TextView>(R.id.li)
        val porcentajeTextView = findViewById<TextView>(R.id.por)
        val impuestoMarginalTextView = findViewById<TextView>(R.id.im)
        val cuotaFijaTextView = findViewById<TextView>(R.id.cf)
        val isrDeterminadoTextView = findViewById<TextView>(R.id.isr)
        val subsidioTextView = findViewById<TextView>(R.id.sub)
        val ingresoNetoTextView = findViewById<TextView>(R.id.sn)
        val ingresoNetoConSubsidioTextView = findViewById<TextView>(R.id.sns)


        val sueldoBruto = s.sueldoB
        val limiteInferior = s.li
        val porcentajeExcedente = s.tasa
        val impuestoMarginal = s.im
        val cuotaFija = s.cf
        val isrDeterminado = s.isrc
        val subsidio = s.sub
        val ingresoNeto = s.sueldoN
        val ingresoNetoConSubsidio = s.sueldoNS


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