# Car Service Analysis App

This is an Android native app developed in Kotlin that uses the Gemini API to analyze car problems, recommend services, and generate detailed analysis reports in PDF format.

## Features

- Analyze car problems using the Gemini API
- Recommend appropriate car services based on analysis
- Generate and print enhanced analysis results into a PDF

## Approach and Tech Stack

- Jetpack Compose
- Type-safe Navigation with Compose Navigation 2.8.0-beta
- Modularization
- Hilt Dependency Injection
- Kotlin Coroutines Flow
- MVVM

## Setup

### Prerequisites

- Android Studio (latest version)
- An Android device or emulator running API level 24 or above

### Getting Started
1. Get a Gemini API
get the API key here: https://ai.google.dev/gemini-api/docs/api-key
2. Put the required API key and default login credential in the **local.properties** file
``` local.properties
apiKey=[YOUR API KEY]
testingEmail=johndoe@carserve.com <- default testing login email
password=nasigoreng <- default testing login password
```
