package com.example.currencyconverter.ui.theme.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.currencyconverter.ui.theme.AppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.currencyconverter.R


@Composable
fun ForesightSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.lightbulb),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 10.dp)
                .padding(end = 10.dp)
                .height(50.dp),
            tint = Color.Blue
        )

        Column {
            Text(
                text = "Foresight",
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.onSurface
            )

            Text(
                text = "Information may not be accurate",
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            val foresightInfo = listOf(
                "Rates usually drop between 1AM to 6PM on Monday",
                "Rates usually are up during holidays",
                "Rates usually are up during holidays"
            )

            foresightInfo.forEach { info ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.dot),
                        contentDescription = null,
                        modifier = Modifier
                            .height(15.dp),
                        tint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = info,
                        style = AppTheme.typography.bodySmall,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}
