package com.example.isr.model
import java.math.BigDecimal
import java.math.RoundingMode
import com.example.isr.model.SueldoA

open class Sueldo (s:Double, periodicidad:String){
    var sueldo: Double = s
        get() {
            return field
        }

    var sueldoB: Double = 0.0
        get() {
            return field
        }

    var sB: Double = 0.0
        get() {
            return field
        }

    var periodicidad: String = periodicidad
        get() {
            return field
        }

    var li: Double = 0.0
        get() {
            return field
        }

    var cf: Double = 0.0
        get() {
            return field
        }

    var tasa: Double = 0.0
        get() {
            return field
        }

    var im: Double = 0.0
        get() {
            return field
        }

    var isrc: Double = 0.0
        get() {
            return field
        }

    var subsidio: Boolean = false
        get() {
            return field
        }

    var sub: Double = 0.0
        get() {
            return field
        }

    var sueldoN: Double = 0.0
        get() {
            return field
        }

    var sueldoNS: Double = 0.0
        get() {
            return field
        }

    fun rounding(){
        sueldoB = BigDecimal(sueldoB).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        sB= BigDecimal(sB).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        li = BigDecimal(li).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        cf = BigDecimal(cf).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        im = BigDecimal(im).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        isrc = BigDecimal(isrc).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        if(sub != 0.0) sub = BigDecimal(sub).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        sueldoN = BigDecimal(sueldoN).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        sueldoNS = BigDecimal(sueldoNS).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    fun toSueldoA(): SueldoA {
        return SueldoA(
            sueldo = this.sueldo,
            sueldoB = this.sueldoB,
            sB = this.sB,
            periodicidad = this.periodicidad,
            li = this.li,
            cf = this.cf,
            tasa = this.tasa,
            im = this.im,
            isrc = this.isrc,
            subsidio = this.subsidio,
            sub = this.sub,
            sueldoN = this.sueldoN,
            sueldoNS = this.sueldoNS
        )
    }
}