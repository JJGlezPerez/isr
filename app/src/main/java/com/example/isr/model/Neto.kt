package com.example.isr.model

class Neto (s: Double, periodicidad: String) : Sueldo(s, periodicidad) {
    fun subsid(){
        if (sueldo<=8800.08) {
            subsidio= true
            sub=390.0
            sueldoNS=sueldo
            sueldoN=sueldo-sub
        }else{
            sueldoN=sueldo
            sueldoNS= sueldo
        }
    }
    fun defReg(){
        when{
            sueldoN >= 375975.62-117912.32 -> asignar(117912.32, 35.00,375975.62 )
            sueldoN in 125325.21-32691.18..375975.61-117912.32 -> asignar(32691.18, 34.00, 125325.21)
            sueldoN in 93993.91-2266.5..125325.20-32691.18 -> asignar(22665.17, 32.00, 93993.91)
            sueldoN in 49233.01-9236.89..93993.90-22665.16 -> asignar(9236.89, 30.00, 49233.01)
            sueldoN in 31236.50-5004.12..49233.00-9236.90 -> asignar(5004.12, 23.52, 31236.5)
            sueldoN in 15487.72-1640.18..31236.49-5004.12 -> asignar(1640.18, 21.36, 15487.72)
            sueldoN in 12935.83-1182.88..15487.71-1640.18 -> asignar(1182.88, 17.92, 12935.83)
            sueldoN in 11128.02-983.63..12935.82-1182.88 -> asignar(893.63, 16.00, 11128.02)
            sueldoN in 6332.06-371.83..11128.01-893.63 -> asignar(371.83, 10.88, 6332.06)
            sueldoN in 746.05-14.32..6332.05-371.82 -> asignar(14.32, 6.4, 746.05)
            sueldoN in 0.01-0.0..746.04-14.32 -> asignar(0.0, 1.92,0.01)
        }
    }
    fun asignar(cuota:Double, t:Double, lim:Double){
        cf = cuota
        tasa = t
        li = lim
    }

    fun calculate(){
        sueldoB = (sueldoN - cf - ((tasa * li)/100))/(1-(tasa/100))
        sB= sueldoB
        calcisr()
    }

    fun calcisr(){
        isrc = sueldoB-sueldoN
        im= isrc-cf
    }

    fun per(){
        when(periodicidad){
            "Diario"-> calcper(30)
            "Semanal"-> calcper(4)
            "Decenal"-> calcper(3)
            "Quincenal"-> calcper(2)
            else-> calcper(1)
        }
    }
    fun calcper(per:Int){
        sueldoB /= per
        sB/=per
        li/=per
        cf/=per
        im/=per
        isrc/=per
        if(sub != 0.0) sub/=per
        sueldoN/=per
        sueldoNS/=per
    }

    fun calc(){
        subsid()
        defReg()
        calculate()
        per()
        rounding()
    }
}