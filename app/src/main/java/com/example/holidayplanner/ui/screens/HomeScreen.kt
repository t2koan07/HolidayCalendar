package com.example.holidayplanner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.holidayplanner.R
import com.example.holidayplanner.data.model.supportedCountries
import com.example.holidayplanner.ui.components.CountryDropdown
import com.example.holidayplanner.ui.components.ErrorView
import com.example.holidayplanner.ui.components.HolidayListItem
import com.example.holidayplanner.ui.components.LoadingView
import com.example.holidayplanner.ui.state.HolidayUiState
import com.example.holidayplanner.viewmodel.HolidayViewModel
import java.util.Calendar

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    val vm: HolidayViewModel = viewModel()
    val state = vm.uiState

    val selectedCountry = remember { mutableStateOf(supportedCountries.first { it.code == "FI" }) }
    val year = remember { mutableStateOf("2025") }

    val sortedHolidays = (state as? HolidayUiState.Success)
        ?.holidays
        ?.sortedBy { it.date }
        .orEmpty()

    val todayIso = remember { getTodayIsoDate() }
    val nextHoliday = sortedHolidays.firstOrNull { it.date >= todayIso }

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = stringResource(R.string.home_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Official holidays, clean and fast.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "${selectedCountry.value.name} (${selectedCountry.value.code}), ${year.value}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        CountryDropdown(
                            selected = selectedCountry.value,
                            countries = supportedCountries,
                            onSelected = { selectedCountry.value = it },
                            label = stringResource(R.string.select_country)
                        )

                        OutlinedTextField(
                            value = year.value,
                            onValueChange = { input ->
                                year.value = input.filter { it.isDigit() }.take(4)
                            },
                            label = { Text(text = stringResource(R.string.select_year)) },
                            placeholder = { Text(text = stringResource(R.string.year_hint)) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        FilledTonalButton(
                            enabled = state !is HolidayUiState.Loading,
                            onClick = { vm.fetchHolidays(year.value, selectedCountry.value.code) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                        ) {
                            Text(text = stringResource(R.string.fetch_holidays))
                        }
                    }
                }
            }

            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.next_holiday),
                            style = MaterialTheme.typography.titleMedium
                        )

                        when (state) {
                            HolidayUiState.Idle -> Text(text = stringResource(R.string.no_holidays_yet))
                            HolidayUiState.Loading -> LoadingView()
                            is HolidayUiState.Error -> ErrorView(
                                message = stringResource(state.messageResId),
                                onRetry = { vm.fetchHolidays(year.value, selectedCountry.value.code) }
                            )
                            is HolidayUiState.Success -> {
                                Text(
                                    text = nextHoliday?.name ?: stringResource(R.string.no_upcoming_holidays),
                                    style = MaterialTheme.typography.titleMedium
                                )

                                if (nextHoliday != null) {
                                    Surface(
                                        shape = MaterialTheme.shapes.medium,
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = formatFinnishDate(nextHoliday.date),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        )
                                    }

                                    Text(
                                        text = "${stringResource(R.string.local_name_label)}: ${nextHoliday.localName}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (state is HolidayUiState.Success) {
                item {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.holidays_list_title),
                                style = MaterialTheme.typography.titleMedium
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                sortedHolidays.forEach { holiday ->
                                    HolidayListItem(holiday = holiday)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun getTodayIsoDate(): String {
    val cal = Calendar.getInstance()
    val y = cal.get(Calendar.YEAR)
    val m = cal.get(Calendar.MONTH) + 1
    val d = cal.get(Calendar.DAY_OF_MONTH)
    val mm = if (m < 10) "0$m" else "$m"
    val dd = if (d < 10) "0$d" else "$d"
    return "$y-$mm-$dd"
}

private fun formatFinnishDate(isoDate: String): String {
    val parts = isoDate.split("-")
    return if (parts.size == 3) "${parts[2]}.${parts[1]}.${parts[0]}" else isoDate
}
