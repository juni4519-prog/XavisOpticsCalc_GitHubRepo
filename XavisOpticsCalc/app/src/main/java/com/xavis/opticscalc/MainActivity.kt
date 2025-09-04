package com.xavis.opticscalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.xavis.opticscalc.ui.theme.XavisTheme
import com.xavis.opticscalc.formula.FormSchemaLoader
import com.xavis.opticscalc.formula.ConditionalRules
import com.xavis.opticscalc.formula.ExcelModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XavisTheme {
                Surface(Modifier.fillMaxSize()) { OpticsApp() }
            }
        }
    }
}

@Composable
fun OpticsApp() {
    val ctx = LocalContext.current
    val focus = LocalFocusManager.current
    val schema = remember { FormSchemaLoader.load(ctx) }
    val rules = remember { ConditionalRules.load(ctx) } // 아직은 미사용이어도 OK
    val sheet = schema["공식"]

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("시트: 공식 (회색=입력)", style = MaterialTheme.typography.titleMedium)

        if (sheet != null) {
            var inputStates by remember {
                mutableStateOf(
                    sheet.inputs.associate { it.cell to it.defaultValue.toString() }.toMutableMap()
                )
            }

            sheet.inputs.forEach { field ->
                OutlinedTextField(
                    value = inputStates[field.cell] ?: "",
                    onValueChange = { v -> inputStates[field.cell] = v.filter { it.isDigit() || it == '.' } },
                    label = { Text("${field.label} [${field.cell}]") },
                    singleLine = true,
                    // ✅ 반드시 괄호 안의 named parameter로! (바깥에 쓰면 에러)
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(onClick = { focus.clearFocus() }, modifier = Modifier.align(Alignment.End)) {
                Text("계산")
            }

            Divider()

            val numericInputs = inputStates.mapValues { it.value.toDoubleOrNull() ?: 0.0 }
            val results = ExcelModel.eval_Sheet(numericInputs)

            Text("결과(수식 셀):", style = MaterialTheme.typography.titleMedium)
            sheet.formulas.forEach { f ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${f.label} [${f.cell}]")
                    Text(
                        String.format("%.6f", results[f.cell] ?: Double.NaN),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        } else {
            Text("스키마를 불러오지 못했습니다.")
        }
    }
}
