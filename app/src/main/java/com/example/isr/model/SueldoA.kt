package com.example.isr.model

import java.io.Serializable

data class SueldoA(var sueldo: Double,
                   var sueldoB: Double,
                   var sB: Double,
                   var periodicidad: String,
                   var li: Double,
                   var cf: Double,
                   var tasa: Double,
                   var im: Double,
                   var isrc: Double,
                   var subsidio: Boolean,
                   var sub: Double,
                   var sueldoN: Double,
                   var sueldoNS: Double
) : Serializable
