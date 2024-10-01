package com.example.isr.model
class Bruto(s: Double, periodicidad: String) : Sueldo(s, periodicidad) {

    fun asign(){
        sueldoB = sueldo
        sB= sueldo
    }

    fun defReg() {
        when {
            sueldoB in 0.01..746.04 -> asignar(0.0, 1.92, 0.01)
            sueldoB in 746.05..6332.05 -> asignar(14.32, 6.4, 746.05)
            sueldoB in 6332.06..11128.01 -> asignar(371.83, 10.88, 6332.06)
            sueldoB in 11128.02..12935.82 -> asignar(893.63, 16.00, 11128.02)
            sueldoB in 12935.83..15487.71 -> asignar(1182.88, 17.92, 12935.83)
            sueldoB in 15487.72..31236.49 -> asignar(1640.18, 21.36, 15487.72)
            sueldoB in 31236.50..49233.00 -> asignar(5004.12, 23.52, 31236.5)
            sueldoB in 49233.01..93993.90 -> asignar(9236.89, 30.00, 49233.01)
            sueldoB in 93993.91..125325.20 -> asignar(22665.17, 32.00, 93993.91)
            sueldoB in 125325.21..375975.61 -> asignar(32691.18, 34.00, 125325.21)
            sueldoB >= 375975.62 -> asignar(117912.32, 35.00, 375975.62)
        }
    }

    fun asignar(cuota: Double, t: Double, lim: Double) {
        cf = cuota
        tasa = t
        li = lim
    }

    fun subs() {
        if (sueldoB <= 9081.00) {
            subsidio = true
            sub = 390.0
            sueldoNS = sueldoN + sub
        } else return
    }

    fun calcisr(): Double {
        im = ((sueldoB - li) * (tasa / 100))
        isrc = im + cf
        return isrc
    }

    fun calcsn() {
        sueldoN = sueldoB - calcisr()
        subs()
    }

    fun per() {
        when (periodicidad) {
            "Diario" -> calcuper(30)
            "Semanal" -> calcuper(4)
            "Decenal" -> calcuper(3)
            "Quincenal" -> calcuper(2)
            else -> calcuper(1)
        }
    }

    fun calcuper(per: Int) {
        sueldoB /= per
        sB /= per
        li /= per
        cf /= per
        im /= per
        isrc /= per
        if (sub != 0.0) sub /= per
        sueldoN /= per
        sueldoNS /= per
    }

    fun calc() {
        asign()
        defReg()
        calcsn()
        per()
        rounding()
    }
}

