# InstaFix - Service Request App

A proof of concept Android application for requesting and managing service appointments.

## Features

- User Authentication (Email, Google, Facebook)
- Location Selection with Google Maps
- Service Request Types
- AI Chat Assistant
- Technician Matching System

## Prerequisites

- Android Studio Arctic Fox or newer
- JDK 17 or newer
- Google Maps API Key
- Firebase Project
- Facebook Developer Account

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Create a Firebase project and add the `google-services.json` file to the `app` directory
4. Get a Google Maps API key from the Google Cloud Console and add it to `AndroidManifest.xml`
5. Create a Facebook Developer account and add your Facebook App ID to `strings.xml`
6. Sync the project with Gradle files
7. Run the app on an emulator or physical device

## Required API Keys

1. Google Maps API Key
2. Firebase Configuration
3. Facebook App ID

## Dependencies

- Jetpack Compose
- Firebase Authentication
- Google Maps SDK
- Facebook Login SDK
- Material3 Components

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/instafix/app/
│   │   │   ├── ui/
│   │   │   │   ├── screens/
│   │   │   │   │   ├── LoginScreen.kt
│   │   │   │   │   ├── MapScreen.kt
│   │   │   │   │   ├── ChatScreen.kt
│   │   │   │   │   └── TechniciansScreen.kt
│   │   │   │   └── theme/
│   │   │   │       ├── Color.kt
│   │   │   │       ├── Theme.kt
│   │   │   │       └── Type.kt
│   │   │   ├── MainActivity.kt
│   │   │   └── InstaFixApp.kt
│   │   └── AndroidManifest.xml
│   └── test/
└── build.gradle
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 