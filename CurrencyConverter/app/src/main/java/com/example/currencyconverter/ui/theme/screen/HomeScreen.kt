package com.example.currencyconverter.ui.theme.screen

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.ui.theme.AppTheme
import com.example.currencyconverter.ui.theme.screen.components.CurrencyConverterRow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.data.model.CurrencyViewModel
import com.example.currencyconverter.ui.theme.screen.components.ForesightSection


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    currencyViewModel: CurrencyViewModel = viewModel()
) {

    // Tiền và loại tiền được gửi
    var sendAmount by remember { mutableStateOf("0") }
    var getAmount by remember { mutableStateOf("0") }
    var sendCurrency by remember { mutableStateOf("USD") }
    var getCurrency by remember { mutableStateOf("EUR") }

    // Tên người dùng
    var username by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(true) }

    // Gọi đến model để lâấy dữ liệu từ API
    LaunchedEffect(Unit) {
        currencyViewModel.fetchCurrencies("1a0115bf113e0b2e30d9387aad476288")
    }

    // Dữ liệu đã lấy được
    val currencyRates by currencyViewModel.currencyRates
    val isLoading by currencyViewModel.isLoading
    val errorMessage by currencyViewModel.errorMessage

    var errorInputMessage by remember { mutableStateOf<String?>(null) }

    // Lấy context từ LocalContext
    val context = LocalContext.current
    val activity = context as? Activity // Ép kiểu Context thành Activity

    // Hàm chuyển đổi tiền tệ
    fun exchangeCurrency() {
        val sendValue = sendAmount.toDoubleOrNull() ?: 0.0

        // Input lỗi
        if (sendValue == null || sendValue <= 0) {
            errorInputMessage = "Invalid amount. Please enter a positive number."
        } else {
            errorInputMessage = null

            // Lấy tỉ lệ giữ tiền gửi và nhận
            val sendToGetRate = currencyRates?.get(getCurrency)?.div(currencyRates?.get(sendCurrency) ?: 1.0) ?: 1.0
            val convertedValue = sendValue * sendToGetRate
            getAmount = "%.2f".format(convertedValue)
        }
    }

    // Lần đầu vào app, nhập tên
    if (isDialogVisible) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { /* Do nothing */ },
            title = { Text(text = "Enter your name") },
            text = {
                androidx.compose.material3.TextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = { Text(text = "Your name") },
                    singleLine = true,
                )
            },
            confirmButton = {
                androidx.compose.material3.Button(
                    onClick = {
                        if (username.isNotBlank()) {
                            isDialogVisible = false
                        }
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {}
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(60.dp)
                    .clickable {
                        if (errorInputMessage != null) {
                            activity?.finishAffinity()
                        } else {
                            exchangeCurrency()
                        }
                    }
                    .background(AppTheme.colors.primary, RoundedCornerShape(100.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (errorMessage != null) "OK" else "Exchange",
                    color = AppTheme.colors.white,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(AppTheme.colors.primary, RoundedCornerShape(100.dp))
                )
            }
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (isLoading) {
                Text(text = "Loading...")
            } else if (errorMessage != null) {
                Text(text = "Error: No internet connection. Please check your network and restart the app.")
            } else {
                Text(
                    text = "Hi $username",
                    style = AppTheme.typography.headline,
                    modifier = Modifier
                        .offset(x = 16.dp)
                    )
                Text(
                    text = "Have a nice day",
                    style = AppTheme.typography.bodySmall,
                    modifier = Modifier
                        .offset(x = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tiền gửi
                CurrencyConverterRow(
                    title = "You send",
                    initialAmount = sendAmount,
                    currency = sendCurrency,
                    isEditable = true,
                    currencyRates = currencyRates,
                    onAmountChanged = { newAmount -> sendAmount = newAmount },
                    onCurrencyChanged = { newCurrency -> sendCurrency = newCurrency }
                )

                // Input lỗi
                errorInputMessage?.let { error ->
                    Text(
                        text = error,
                        color = androidx.compose.ui.graphics.Color.Red,
                        style = AppTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .offset(x = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.exchange),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .height(50.dp)
                            .graphicsLayer(rotationZ = 90f),
                        tint = Color.Blue
                    )

                    Spacer(modifier = Modifier.padding(6.dp))
                    val sendToGetRate = currencyRates?.get(getCurrency)?.div(currencyRates?.get(sendCurrency) ?: 1.0) ?: 1.0
                    Text(
                        text = "1 $sendCurrency = $sendToGetRate $getCurrency",
                        style = AppTheme.typography.bodySmall)
                }

                // Tiền nhận
                CurrencyConverterRow(
                    title = "You get",
                    initialAmount = getAmount,
                    currency = getCurrency,
                    isEditable = false,
                    currencyRates = currencyRates,
                    onCurrencyChanged = { newCurrency -> getCurrency = newCurrency },
                    onAmountChanged = {}
                )

                Spacer(modifier = Modifier.height(6.dp))

                ForesightSection()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}