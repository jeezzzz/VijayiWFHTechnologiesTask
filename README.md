# Movies & TV Shows App

## Overview
This Android application displays a list of Movies and TV Shows fetched from the Watchmode API. Built using Jetpack Compose, the app follows the MVVM architecture and includes features like data fetching, error handling, and internet connectivity checks.

## Features
- Displays a list of Movies & TV Shows.
- Uses Jetpack Compose for UI components.
- Implements MVVM architecture.
- Uses Retrofit for API calls.
- Handles no internet connection gracefully with a Snackbar.
- Implements shimmer effect for loading placeholders.
- Includes navigation with a slight delay for smooth transitions.

## Tech Stack
- *Language*: Kotlin
- *UI Framework*: Jetpack Compose
- *Architecture*: MVVM
- *Networking*: Retrofit
- *Dependency Injection*: Koin
- *Coroutines & Flow*: For asynchronous programming

## Installation
1. Clone the repository:
   sh
   git clone https://github.com/your-repo.git
   
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the application on an emulator or a physical device.

## API Integration
This app fetches Movies and TV Shows using the Watchmode API. Ensure you have an API key and configure it in the Retrofit instance.

## Usage
1. Launch the app.
2. Browse the list of Movies & TV Shows.
3. Toggle between Movies and TV Shows using the switch.
4. Tap on any item to navigate to its details screen.
5. If there is no internet connection, a red Snackbar appears notifying the user.

## Unit Testing
The app includes unit tests for:
- API response handling.
- ViewModel logic.
- Internet connectivity checks.

Run tests using:
sh
./gradlew testDebugUnitTest


## License
This project is licensed under the MIT License.
