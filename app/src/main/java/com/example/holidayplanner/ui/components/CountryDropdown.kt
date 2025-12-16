package com.example.holidayplanner.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.holidayplanner.data.model.Country

@Composable
fun CountryDropdown(
    selected: Country,
    countries: List<Country>,
    onSelected: (Country) -> Unit,
    label: String
) {
    val expanded = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = "${selected.name} (${selected.code})",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(text = "${country.name} (${country.code})") },
                    onClick = {
                        onSelected(country)
                        expanded.value = false
                    }
                )
            }
        }
    }
}
