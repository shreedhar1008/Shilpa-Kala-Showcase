# 🎨 Shilpa-Kala Showcase

## 📌 Project Overview

Shilpa-Kala Showcase is an AI-powered Android application developed to promote and preserve rural arts, handicrafts, and traditional cultural heritage through digital technology.

Many rural artisans struggle to market their handmade products because they lack online visibility and digital platforms. This project provides a smart mobile application where artisans can showcase their artwork, connect with users, and digitally promote traditional culture.

The application is designed for:
- Rural artisans and craftsmen
- Art lovers and buyers
- Students and cultural enthusiasts
- Communities interested in preserving traditional arts

---

## 💡 Problem Statement

Traditional artisans often face major challenges such as:
- Lack of digital exposure
- Limited marketing opportunities
- Difficulty reaching modern customers
- Decline of traditional art forms

Due to these issues, many valuable cultural and artistic traditions are slowly disappearing.

Shilpa-Kala Showcase solves this problem by creating a modern Android platform that digitally empowers artisans and helps preserve rural culture using technology and AI-based recommendations.

---

## 🚀 Features

- 🖼️ Digital showcase for handicrafts and artworks
- 🤖 AI-powered product recommendations
- 🔍 Smart search and category filtering
- 📱 Simple and user-friendly Android interface
- 🎨 Artisan profile management
- 🛒 Product browsing system
- 🌐 Promotion of rural culture and heritage
- 📊 Product management features
- 💡 Smart user engagement features

---

## 📱 Modules

### 👤 User Module
- Browse Artworks
- Search Products
- View Artisan Profiles
- Explore Traditional Crafts

### 🎨 Artisan Module
- Upload Products
- Manage Product Information
- Showcase Handmade Art
- Promote Traditional Culture

### 🤖 AI Module
- Personalized Recommendations
- Smart Product Suggestions
- Improved User Experience

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Kotlin | Android Development |
| Android Studio | IDE |
| XML | User Interface Design |
| Material Design | Modern UI Components |
| Generative AI Concepts | Recommendation Features |

---

## ⚙️ Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing.

### 📋 Prerequisites

Before running the project, ensure you have the following installed:

- **Android Studio Jellyfish (2023.3.1)** or newer
- **JDK 17** (required for the Gradle Build Tool)
- **Android SDK Platform 34** (UpsideDownCake)

### 🔧 Installation & Setup

**1. Clone the repository:**

```bash
git clone https://github.com/shreedhar1008/Shilpa-Kala-Showcase.git
cd Shilpa-Kala-Showcase
```

**2. Open the project:**
- Launch Android Studio
- Select **Open** and navigate to the project folder
- Wait for the IDE to finish the **Gradle Sync**

**3. Dependency installation:**

The project uses Gradle to manage dependencies, which download automatically during sync. Key dependencies include:

- **Material Design** for modern UI components
- **Coil** for image loading
- **Navigation Component** for screen routing
- **Generative AI SDK** for recommendation features

### ▶️ Running the App

**1. Device setup:**
- **Physical Device:** Connect your phone via USB and enable **USB Debugging** in Developer Options
- **Emulator:** Open **Device Manager** in Android Studio and create a Virtual Device *(Recommended: Pixel 6, API 33+)*

**2. Build and launch:**
- Select `app` in the run configurations dropdown at the top
- Click the **Run** button (▶️) or press `Shift + F10`

### 🏗️ Build & Development Scripts

Use the terminal at the root of the project to perform common tasks:

| Command | Action |
|--------|--------|
| `./gradlew clean` | Cleans the build folders |
| `./gradlew assembleDebug` | Generates a debug APK in `app/build/outputs/apk/` |
| `./gradlew test` | Runs local unit tests |
| `./gradlew lint` | Runs a code quality check |

### ✅ Build Success Checklist

If you are updating the code, ensure the following pass before submitting:

- [ ] **Compile Check:** Run `./gradlew build` to ensure there are no syntax errors
- [ ] **Navigation Check:** Ensure `MainActivity.kt` correctly initializes the navigation graph
- [ ] **Theme Check:** Verify that the app theme is correctly applied in `themes.xml`
- [ ] **Dependency File:** Ensure any new libraries are added to `app/build.gradle.kts`
