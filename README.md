# Dr. Appointment (Android App)

**Made by - Vikash Kumar (NIT Jamshedpur)**

## 📋 Overview

Dr. Appointment is a native Android app built with Kotlin + Jetpack Compose that helps users discover doctors by specialty, browse top doctors, and view detailed profiles.

## 🎯 Purpose

Make it quick and easy to find the right doctor, review details, and contact or navigate to them.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Navigation Compose](https://img.shields.io/badge/Navigation%20Compose-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Coil](https://img.shields.io/badge/Coil-0000FF?style=for-the-badge&logo=coil&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)

---

## ✨ Core Functionalities

1. Browse doctors by specialty categories
2. View **top doctors** with ratings and experience
3. Open doctor profile details (bio, stats, reviews)
4. Quick actions: call, SMS, directions, website, share
5. Firebase realtime data loading
6. Smooth navigation between screens
7. Material 3 UI with Compose
8. Offline-friendly UI state with cached lists

---

## 🛠️ Tech Stack (Points)

- Kotlin
- Jetpack Compose + Material 3
- MVVM (ViewModel + LiveData)
- Firebase Realtime Database
- Navigation Compose
- Coil (image loading)
- Gradle + Android Studio

---

## 🏗️ Technical Perspective

- MVVM architecture with `MainViewModel` exposing `LiveData` for UI state
- Single-shot Firebase reads (`addListenerForSingleValueEvent`) with in-memory caching flags
- Compose-first UI, split by feature modules (intro, home, detail, top doctors)
- Navigation graph centralized in `AppNavGraph` with route helpers
- Resource-driven UI (drawables, fonts, colors, strings)

---

## 🌟 Key Features

### 👨‍⚕️ Doctor Discovery

- Category-based browsing
- Top doctor list with ratings
- Detailed profile view

### 📞 Actions & Sharing

- Call, message, and directions
- Website and share links

### 🧭 UI & Navigation

- Compose-only UI
- Clean navigation graph

### 💾 Data & Sync

- Firebase Realtime Database
- Efficient one-time fetches with caching

---

## 📱 App Screenshots

![Dr. Appointment App Screenshots](assets/screenshots/collage.jpg)

---

## 📄 License

NIT Jamshedpur
