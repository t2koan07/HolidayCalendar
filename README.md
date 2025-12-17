# HolidayCalendar

HolidayCalendar is a native Android application that allows users to browse public holidays by country and year. The app fetches official holiday data from a public API and presents it in a clean, mobile friendly UI with filtering, theming, and clear state handling.

Built with Kotlin and Jetpack Compose, following MVVM architecture.

![Weather App Gif](https://github.com/user-attachments/assets/0f2d4515-3931-4ade-91b9-50164fcd49cc)

## Contents

* [Introduction](#holidaycalendar)
* [Features](#features)
* [Architecture & Structure](#architecture--structure)
* [Technology Stack](#technology-stack)
* [Installation & Setup](#installation--setup)
* [API](#api)
* [License](#license)

## Features

- **Public Holiday Search** — Fetch public holidays by country and year
- **Next Holiday Highlight** — Automatically shows the next upcoming holiday
- **Filtering Options** — View only upcoming or global holidays
- **Error Handling** — User friendly messages for network or API errors
- **Loading States** — Spinner displayed while data is loading
- **Dark Mode Support** — Toggleable theme, persisted across launches
- **Navigation** — Separate Home, Info, and Settings screens
- **Localized Strings** — All UI text stored in string resources
  
## Architecture & Structure

The app follows **MVVM architecture** and is divided into clear layers:

```text
HolidayCalendar/
├── data/
│   ├── api/            # Retrofit API definitions
│   ├── model/          # Data models
│   ├── repository/     # Data access layer
│   └── datastore/      # Preferences (country, year, theme)
├── ui/
│   ├── screens/        # Composable screens
│   ├── components/     # Reusable UI components
│   ├── navigation/     # Navigation graph and routes
│   └── theme/          # Custom colors and theming
├── viewmodel/          # ViewModel and UI state
└── MainActivity.kt     # App entry point
```

State management, validation, and API calls are handled in the ViewModel. UI reacts to state changes using Compose.

## Technology Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Architecture:** MVVM
* **Networking:** Retrofit + Coroutines
* **Persistence:** DataStore Preferences
* **Navigation:** Compose Navigation

## Installation & Setup

1. **Clone the repository**

```bash
git clone https://github.com/t2koan07/HolidayCalendar.git
```

2. **Open in Android Studio**

* Open the project
* Let Gradle sync
* Run on an emulator or physical device

No additional configuration is required.

## API

* **Public Holidays API:** Nager.Date

    * [https://date.nager.at](https://date.nager.at)

The API is free to use and does not require authentication or API keys.

## License
