package com.xavis.opticscalc.formula

object ExcelModel {
    /**
     * 입력:  "A1" -> 1.23 같은 숫자 맵
     * 출력:  "D15" 같은 수식 셀 결과 맵
     */
    fun eval_Sheet(inputs: Map<String, Double>): Map<String, Double> {
        val out = mutableMapOf<String, Double>()

        // ----- 예시: 실제 수식으로 교체하세요 -----
        // e.g. 공식!D15 = A1 + A2
        val a1 = inputs["A1"] ?: 0.0
        val a2 = inputs["A2"] ?: 0.0
        out["D15"] = a1 + a2

        // e.g. 공식!E15 = if (D15 < 3) D15 * 2 else D15
        val d15 = out["D15"] ?: 0.0
        out["E15"] = if (d15 < 3.0) d15 * 2 else d15
        // --------------------------------------

        return out
    }
}
