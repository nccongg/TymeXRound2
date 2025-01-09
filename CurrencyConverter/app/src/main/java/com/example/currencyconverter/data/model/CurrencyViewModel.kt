package com.example.currencyconverter.data.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.api.CurrencyRatesResponse
import com.example.currencyconverter.data.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel : ViewModel() {
    val currencyRates = mutableStateOf<Map<String, Double>?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun fetchCurrencies(accessKey: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response: CurrencyRatesResponse = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getCurrencyRates(accessKey)
                }
                if (response.success) {
                    currencyRates.value = response.rates
                } else {
                    errorMessage.value = "API call failed: ${response.error?.info ?: "Unknown error"}"
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "An unknown error occurred"
            } finally {
                isLoading.value = false
            }
        }
    }
}
