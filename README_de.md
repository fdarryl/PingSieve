# PingSieve
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![APK](https://img.shields.io/badge/APK-Release-blue.svg)](https://github.com/fdarryl/VlessChecker/releases/latest)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Open%20Source-brightgreen.svg)](#license)
**PingSieve** ist ein Dienstprogramm, das automatisch funktionierende VLESS-Konfigurationen zur Umgehung von Einschränkungen suchen kann. Sein Hauptzweck besteht darin, Whitelists in Russland zu umgehen.
## ⚠️ Wichtige Warnung
**BITTE VERSCHWENDEN SIE DATEN NICHT GEDANKENLOS. DENKEN SIE DARAN, DASS SIE ÖFFENTLICHE LÖSUNGEN VERWENDEN.**
## Funktionen
- 🔍 **Automatische Konfigurationssuche** - Findet automatisch funktionierende VLESS-Konfigurationen
- ⚡ **Paralleles Pinging** - Multithreaded IP-Verifizierung für schnelle Ergebnisse
- ⚙️ **Konfigurierbare Einstellungen** - Passen Sie Threadanzahl, Timeout und URL an
- 🌐 **Unterstützung für 12 Sprachen** - Oberfläche in 12 Sprachen verfügbar
- 📊 **Statistiken in Echtzeit** - Live-Aktualisierung von guten/schlechten Konfigurationen
- 🔗 **Benutzerdefinierte URL-Unterstützung** - Laden Sie Konfigurationen aus jeder Quelle
- 📋 **Einfaches Kopieren** - Kopieren Sie Ergebnisse mit einem Klick
## Schnellstartanleitung
### Schritt 1: Konfigurationen analysieren
1. Verbinden Sie sich mit **WiFi oder 2G** (ungehindertes Internet)
2. Öffnen Sie PingSieve
3. Tippen Sie auf die Schaltfläche **Laden** um VLESS-Konfigurationen zu abrufen
4. Warten Sie auf das vollständige Laden der Liste
### Schritt 2: Konfigurationen überprüfen
1. Wechseln Sie zu **LTE** (wo Whitelists aktiviert sind)
2. Tippen Sie auf die Schaltfläche **Überprüfen** um die Verifizierung zu starten
3. Die App pingt jede IP-Adresse automatisch
4. Gültige Konfigurationen erscheinen im Bereich "Gültig"
5. Warten Sie auf den Abschluss des Verifizierungsprozesses
### Schritt 3: Verwendung im Launcher
1. Kopieren Sie die gültigen Konfigurationen aus dem Bereich "Gültig" (tippen Sie auf **In Zwischenablage importieren**)
2. Öffnen Sie einen beliebigen VLESS-kompatiblen Launcher (z.B. Nekoray, V2rayNG, Hiddify, etc.)
3. Importieren Sie die Konfigurationen in Ihren Launcher
4. Überprüfen Sie den **tatsächlichen Delay/Ping** für jede Konfiguration
5. Wenn der Ping angezeigt wird, funktioniert die Konfiguration für Sie
6. Verwenden Sie die funktionierende Konfiguration zum Verbinden
## Installation
1. Laden Sie die neueste APK aus [Releases](https://github.com/fdarryl/VlessChecker/releases/latest) herunter
2. Aktivieren Sie "Installation aus unbekannten Quellen" in den Einstellungen
3. Installieren Sie die APK
4. Gewähren Sie die erforderlichen Berechtigungen
## Einstellungen
- **Thread-Anzahl** - Anzahl der gleichzeitigen Ping-Threads (Standard: 4)
- **Timeout** - Ping-Timeout in Millisekunden (Standard: 1500ms)
- **Parsing-URL** - URL zum Laden von Konfigurationen
## Technische Details
- **Sprache**: Kotlin
- **Build-System**: Gradle
- **UI Framework**: Jetpack Compose
- **Min SDK**: 24
- **Target SDK**: 36
## Lizenz
Open Source - Verwenden Sie auf eigene Gefahr.
