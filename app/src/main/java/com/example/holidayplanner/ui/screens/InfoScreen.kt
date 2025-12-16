package com.example.holidayplanner.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.holidayplanner.R
import androidx.core.net.toUri

@Composable
fun InfoScreen(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val apiUrl = stringResource(R.string.api_url)

    androidx.compose.foundation.layout.Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.about_title),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = stringResource(R.string.about_text))
                Text(text = stringResource(R.string.author_line), style = MaterialTheme.typography.bodySmall)
            }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.api_title),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = stringResource(R.string.api_text))
                Text(text = apiUrl, style = MaterialTheme.typography.bodySmall)

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, apiUrl.toUri())
                        context.startActivity(intent)
                    }
                ) {
                    Text(text = stringResource(R.string.open_api_site))
                }
            }
        }
    }
}
