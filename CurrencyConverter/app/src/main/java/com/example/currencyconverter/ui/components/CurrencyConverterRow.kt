package com.example.currencyconverter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.currencyconverter.ui.theme.AppTheme
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.R

@Composable
fun CurrencyConverterRow(
    title: String,
    initialAmount: String,
    currency: String,
    currencyRates: Map<String, Double>?,
    onAmountChanged: (String) -> Unit,
    onCurrencyChanged: (String) -> Unit,
    isEditable: Boolean = true,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val expanded = remember { mutableStateOf(false) }
    val availableCurrencies = currencyRates?.keys?.toList() ?: listOf()

    val errorMessage = remember { mutableStateOf<String?>(null) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(AppTheme.colors.surface, RoundedCornerShape(8.dp))
            .border(
                width = if (isEditable) 2.dp else 1.dp,
                color = if (isEditable) AppTheme.colors.primary else AppTheme.colors.onSurface.copy(
                    alpha = 0.5f
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = AppTheme.typography.bodySmall)

            // Input
            TextField(
                value = initialAmount,
                onValueChange = { newValue ->
                    onAmountChanged(newValue)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { focusManager.clearFocus() },
                textStyle = AppTheme.typography.headline,
                enabled = isEditable,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = AppTheme.colors.primary,
                    focusedTextColor = AppTheme.colors.primary,
                    unfocusedTextColor = AppTheme.colors.onSurface,
                    focusedContainerColor = AppTheme.colors.surface,
                    unfocusedContainerColor = AppTheme.colors.surface,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    disabledTextColor = AppTheme.colors.onSurface.copy(alpha = 1f),
                    disabledContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                ),

            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = currency,
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.onSurface,
            )
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null,
                modifier = Modifier
                    .clickable { expanded.value = true }
                    .padding(start = 4.dp)
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false},
                modifier = Modifier
                    .background(AppTheme.colors.surface)
                    .padding(horizontal = 8.dp)
                    .height(300.dp)
                    .width(220.dp)

            ) {

                val searchQuery = remember { mutableStateOf("") }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .border(
                            width =  1.dp,
                            color = AppTheme.colors.onSurface.copy(
                                alpha = 0.7f
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(24.dp)
                            .offset(x = 7.dp),
                        tint = AppTheme.colors.primary
                    )

                    // Search loại tiền
                    TextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        placeholder = { Text(text = "Search currency...") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),

                        colors = TextFieldDefaults.colors(
                            cursorColor = AppTheme.colors.primary,
                            focusedTextColor = AppTheme.colors.onSurface,
                            unfocusedTextColor = AppTheme.colors.onSurface.copy(alpha = 0.8f),
                            focusedContainerColor = AppTheme.colors.surface,
                            unfocusedContainerColor = AppTheme.colors.surface,
                            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                        )
                    )
                }

                // Lọc
                val filteredCurrencies = availableCurrencies.filter {
                    it.contains(searchQuery.value, ignoreCase = true)
                }

                filteredCurrencies .forEach { currency  ->
                    DropdownMenuItem(
                        text = { Text(currency) },
                        onClick = {
                            onCurrencyChanged(currency)
                            expanded.value = false
                        })
                }

                if (filteredCurrencies.isEmpty()) {
                    Text(
                        text = "No results found",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        color = AppTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}