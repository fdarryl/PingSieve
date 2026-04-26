# PingSieve
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![APK](https://img.shields.io/badge/APK-Release-blue.svg)](https://github.com/fdarryl/VlessChecker/releases/latest)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Open%20Source-brightgreen.svg)](#license)
**PingSieve** est un utilitaire capable de rechercher automatiquement des configurations VLESS fonctionnelles pour contourner les restrictions. Son objectif principal est de contourner les listes blanches en Russie.
## ⚠️ Avertissement Important
**VEUILLEZ NE PAS GASPILLER LE TRAFIC SANS RÉFLÉCHIR. N'OUBLIEZ PAS QUE VOUS UTILISEZ DES SOLUTIONS PUBLIQUES.**
## Caractéristiques
- 🔍 **Recherche Automatique de Configurations** - Trouve automatiquement des configurations VLESS fonctionnelles
- ⚡ **Ping Concurrent** - Vérification d'IP multi-thread pour des résultats rapides
- ⚙️ **Paramètres Personnalisables** - Ajustez le nombre de threads, le délai d'attente et l'URL
- 🌐 **Support de 12 Langues** - Interface disponible en 12 langues
- 📊 **Statistiques en Temps Réel** - Mise à jour en direct des bonnes/mauvaises configurations
- 🔗 **Support d'URL Personnalisée** - Chargez les configurations de n'importe quelle source
- 📋 **Copie Facile** - Copiez les résultats en un seul clic
## Guide de Démarrage Rapide
### Étape 1: Analyser les Configurations
1. Connectez-vous à **WiFi ou 2G** (Internet sans restrictions)
2. Ouvrez PingSieve
3. Appuyez sur le bouton **Charger** pour obtenir les configurations VLESS
4. Attendez que la liste soit complètement chargée
### Étape 2: Vérifier les Configurations
1. Basculez vers **LTE** (où les listes blanches sont activées)
2. Appuyez sur le bouton **Vérifier** pour démarrer la vérification
3. L'application fera ping à chaque adresse IP automatiquement
4. Les configurations valides apparaîtront dans la section "Valide"
5. Attendez que le processus de vérification se termine
### Étape 3: Utiliser dans un Lanceur
1. Copiez les configurations valides de la section "Valide" (appuyez sur **Importer dans le presse-papiers**)
2. Ouvrez n'importe quel lanceur compatible VLESS (par exemple, Nekoray, V2rayNG, Hiddify, etc.)
3. Importez les configurations dans votre lanceur
4. Vérifiez le **délai/ping réel** pour chaque configuration
5. Si le ping s'affiche, la configuration fonctionne pour vous
6. Utilisez la configuration fonctionnelle pour vous connecter
## Installation
1. Téléchargez le dernier APK à partir de [versions](https://github.com/fdarryl/VlessChecker/releases/latest)
2. Activez "Installation à partir de sources inconnues" dans les paramètres
3. Installez l'APK
4. Accordez les autorisations nécessaires
## Paramètres
- **Nombre de Threads** - Nombre de threads de ping simultanés (par défaut: 4)
- **Délai d'Attente** - Délai d'attente du ping en millisecondes (par défaut: 1500ms)
- **URL d'Analyse** - URL pour charger les configurations
## Détails Techniques
- **Langage**: Kotlin
- **Système de Compilation**: Gradle
- **Framework UI**: Jetpack Compose
- **Min SDK**: 24
- **Target SDK**: 36
## Licence
Code Ouvert - Utilisez à vos propres risques.
