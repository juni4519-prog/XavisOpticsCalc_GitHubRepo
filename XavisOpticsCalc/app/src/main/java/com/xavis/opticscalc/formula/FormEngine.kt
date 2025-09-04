package com.xavis.opticscalc.formula

import android.content.Context
import org.json.JSONObject

data class InputField(val cell: String, val label: String, val defaultValue: Double)
data class FormulaField(val cell: String, val label: String)
data class SheetSchema(val name: String, val inputs: List<InputField>, val formulas: List<FormulaField>)

object FormSchemaLoader {
  fun load(context: Context): Map<String, SheetSchema> {
    val json = context.assets.open("optics_form_schema.json").bufferedReader().use { it.readText() }
    val root = JSONObject(json)
    val map = mutableMapOf<String, SheetSchema>()
    val names = root.keys()
    while (names.hasNext()) {
      val name = names.next()
      val obj = root.getJSONObject(name)
      val inputsJson = obj.getJSONArray("inputs")
      val formulasJson = obj.getJSONArray("formulas")
      val inputs = mutableListOf<InputField>()
      for (i in 0 until inputsJson.length()) {
        val it = inputsJson.getJSONObject(i)
        inputs.add(InputField(it.getString("cell"), it.optString("label", it.getString("cell")), it.optDouble("default", 0.0)))
      }
      val formulas = mutableListOf<FormulaField>()
      for (i in 0 until formulasJson.length()) {
        val ft = formulasJson.getJSONObject(i)
        formulas.add(FormulaField(ft.getString("cell"), ft.optString("label", ft.getString("cell"))))
      }
      map[name] = SheetSchema(name, inputs, formulas)
    }
    return map
  }
}

data class ConditionalRule(val sheet: String, val cells: List<String>, val op: String, val value: Double, val color: String, val label_hint: String? = null)

object ConditionalRules {
  fun load(context: android.content.Context): List<ConditionalRule> {
    val text = context.assets.open("conditional_rules.json").bufferedReader().use { it.readText() }
    val root = org.json.JSONObject(text)
    val arr = root.getJSONArray("rules")
    val list = mutableListOf<ConditionalRule>()
    for (i in 0 until arr.length()) {
      val o = arr.getJSONObject(i)
      val cells = o.getJSONArray("cells")
      val cl = mutableListOf<String>()
      for (j in 0 until cells.length()) cl.add(cells.getString(j))
      list.add(
        ConditionalRule(
          sheet = o.getString("sheet"),
          cells = cl,
          op = o.getString("op"),
          value = o.getDouble("value"),
          color = o.optString("color", "#FFEB3B"),
          label_hint = o.optString("label_hint", null)
        )
      )
    }
    return list
  }
}
