package com.xavis.opticscalc.formula

import kotlin.math.*

object ExcelModel {
  fun eval_Sheet(inputs: Map<String, Double>): Map<String, Double> {
    val out = mutableMapOf<String, Double>()
    val D3 = inputs["D3"] ?: 5.0
    val D5 = inputs["D5"] ?: 600.0
    val D6 = inputs["D6"] ?: 20.0
    val D9 = inputs["D9"] ?: 280.3
    val E9 = inputs["E9"] ?: 240.0
    val D10 = inputs["D10"] ?: 50.0
    val E10 = inputs["E10"] ?: 50.0
    val D12 = inputs["D12"] ?: 10.0
    val D13 = inputs["D13"] ?: 3.0
    val D16 = inputs["D16"] ?: 35.0
    val D17 = inputs["D17"] ?: 280.0
    val E17 = inputs["E17"] ?: 280.0
    val D21 = inputs["D21"] ?: 0.1
    out["E3"] = D3
    out["D4"] = D5/D6
    out["E4"] = D4
    out["E5"] = D5
    out["E6"] = E5/E4
    out["D7"] = D9/D6
    out["E7"] = E9/E6
    out["D11"] = D9/(D10/1000)
    out["E11"] = E9/(E10/1000)
    out["E12"] = D12
    out["E13"] = D13
    out["D14"] = (D13/1000)*D6
    out["E14"] = (E13/1000)*E6
    out["D15"] = D14/(D10/1000)
    out["E15"] = E14/(E10/1000)
    out["E16"] = D16
    out["D18"] = ROUNDUP((D17*1.1/D7),0)
    out["E18"] = ROUNDUP((E17*1.1/E7),0)
    out["D19"] = D18*E18
    out["D20"] = D16/D12
    out["D23"] = D19*(D20+D21)
    out["D24"] = D23/60
    out["D25"] = D23/3600
    out["D26"] = D25/24
    return out
  }
  fun eval_공식2(inputs: Map<String, Double>): Map<String, Double> {
    val out = mutableMapOf<String, Double>()
    val I3 = inputs["I3"] ?: 100.0
    val I5 = inputs["I5"] ?: 490.0
    val I7 = inputs["I7"] ?: 20.0
    val I11 = inputs["I11"] ?: 49.5
    out["I6"] = I5/I3
    out["I9"] = I10/25.4
    out["I10"] = I7*(I5/I3)
    out["I12"] = I7/(I11/1000)
    return out
  }
}
