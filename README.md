# PingSieve
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![APK](https://img.shields.io/badge/APK-Release-blue.svg)](https://github.com/fdarryl/VlessChecker/releases/latest)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Open%20Source-brightgreen.svg)](#license)
**PingSieve** is a utility capable of automatically searching for working VLESS configurations to bypass restrictions. Its main purpose is to bypass whitelists in Russia.
## ⚠️ Important Warning
**PLEASE DO NOT WASTE TRAFFIC THOUGHTLESSLY. REMEMBER THAT YOU ARE USING PUBLIC SOLUTIONS.**
## Features
- 🔍 **Automatic Configuration Search** - Finds working VLESS configurations automatically
- ⚡ **Concurrent Pinging** - Multi-threaded IP verification for fast results
- ⚙️ **Configurable Settings** - Adjust thread count, timeout, and parsing URL
- 🌐 **Multi-Language Support** - Available in 12 languages
- 📊 **Real-time Statistics** - Live updates showing good/bad configurations
- 🔗 **Custom URL Support** - Load configurations from any source
- 📋 **Easy Clipboard Export** - Copy valid configurations with one click
## Quick Start Guide
### Step 1: Parse Configurations
1. Connect to **WiFi or 2G** (unrestricted internet)
2. Open PingSieve
3. Tap **Load** button to fetch VLESS configurations
4. Wait for the list to load completely
### Step 2: Check Configurations
1. Switch to **LTE** (where whitelists are enabled)
2. Tap the **Check** button to start verification
3. The app will ping each IP address automatically
4. Valid configurations will appear in the "Valid" section
5. Wait for the process to complete
### Step 3: Use in Launcher
1. Copy the valid configurations from the "Valid" section (tap **Copy to Clipboard**)
2. Open any VLESS-compatible launcher (e.g., Nekoray, V2rayNG, Hiddify, etc.)
3. Import the configurations into your launcher
4. Check the **real delay/ping** for each configuration
5. If the ping is displayed, the configuration works for you
6. Use the working configuration to connect
## Installation
1. Download the latest APK from [releases](https://github.com/fdarryl/VlessChecker/releases/latest)
2. Enable "Unknown Sources" in your device settings
3. Install the APK
4. Grant necessary permissions
## Settings
- **Thread Count** - Number of concurrent ping threads (default: 4)
- **Timeout** - Ping timeout in milliseconds (default: 1500ms)
- **Parsing URL** - URL to fetch configurations from
## Supported Languages
- English
- Русский (Russian)
- Deutsch (German)
- Español (Spanish)
- Français (French)
- Português (Portuguese)
- Türkçe (Turkish)
- العربية (Arabic)
- فارسی (Persian)
- עברית (Hebrew)
- हिंदी (Hindi)
- 中文 (Chinese Simplified)
## Requirements
- Android 7.0 (API 24) or higher
- Internet connection
- INTERNET permission
## Technical Details
- **Language**: Kotlin
- **Build System**: Gradle
- **UI Framework**: Jetpack Compose
- **Min SDK**: 24
- **Target SDK**: 36
## Disclaimer
This application is provided for educational and authorized use only. Users are responsible for their own usage and compliance with applicable laws and regulations. The developers are not responsible for misuse of this application.
## License
This project is open source. Use at your own discretion.
---
**Available README files in other languages:**
- [README_ru.md](README_ru.md) - Русский
- [README_de.md](README_de.md) - Deutsch
- [README_es.md](README_es.md) - Español
- [README_fr.md](README_fr.md) - Français
- [README_pt.md](README_pt.md) - Português
- [README_tr.md](README_tr.md) - Türkçe
- [README_ar.md](README_ar.md) - العربية
- [README_fa.md](README_fa.md) - فارسی
- [README_iw.md](README_iw.md) - עברית
- [README_hi.md](README_hi.md) - हिंदी
- [README_zh.md](README_zh.md) - 简体中文
