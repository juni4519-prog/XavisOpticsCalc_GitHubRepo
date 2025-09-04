package com.xavis.opticscalc.formula

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.round

/**
 * ExcelModel: 시트 계산 엔진의 최소 버전
 * - 지금은 빌드 통과가 목적이라 결과를 비워서 반환합니다.
 * - 실제 수식 로직은 eval_Sheet() 내부 TODO 위치에 채워 넣으세요.
 */
object ExcelModel {

    // ── Excel 유틸 함수(엑셀 대체) ──
    fun ROUND(value: Double, digits: Int): Double {
        val f = 10.0.pow(digits)
        return round(value * f) / f
    }

    fun ROUNDUP(value: Double, digits: Int): Double {
        val f = 10.0.pow(digits)
        return ceil(value * f) / f
    }

    fun ROUNDDOWN(value: Double, digits: Int): Double {
        val f = 10.0.pow(digits)
        return floor(value * f) / f
    }

    fun MAX(a: Double, b: Double): Double = max(a, b)
    fun MIN(a: Double, b: Double): Double = min(a, b)
    fun ABS(x: Double): Double = abs(x)
    fun IF(cond: Boolean, t: Double, f: Double): Double = if (cond) t else f

    // ── 시트 평가 ──
    fun eval_Sheet(inputs: Map<String, Double>): Map<String, Double> {
        // 예) val E4 = inputs["E4"] ?: 0.0
        // TODO: 실제 수식 계산을 여기에 작성하고 결과 맵을 반환하세요.
        return emptyMap()
    }
}
