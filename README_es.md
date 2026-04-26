# PingSieve
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![APK](https://img.shields.io/badge/APK-Release-blue.svg)](https://github.com/fdarryl/VlessChecker/releases/latest)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Open%20Source-brightgreen.svg)](#license)
**PingSieve** es una utilidad capaz de buscar automáticamente configuraciones VLESS funcionales para eludir restricciones. Su propósito principal es eludir listas blancas en Rusia.
## ⚠️ Advertencia Importante
**POR FAVOR NO DESPERDICIE TRÁFICO IMPENSADAMENTE. RECUERDE QUE ESTÁ UTILIZANDO SOLUCIONES PÚBLICAS.**
## Características
- 🔍 **Búsqueda Automática de Configuraciones** - Encuentra configuraciones VLESS funcionales automáticamente
- ⚡ **Ping Concurrente** - Verificación de IP multihilo para resultados rápidos
- ⚙️ **Configuración Personalizable** - Ajuste la cantidad de hilos, tiempo de espera y URL
- 🌐 **Soporte para 12 Idiomas** - Interfaz disponible en 12 idiomas
- 📊 **Estadísticas en Tiempo Real** - Actualización en vivo de configuraciones buenas/malas
- 🔗 **Compatibilidad con URL Personalizada** - Cargue configuraciones desde cualquier fuente
- 📋 **Copia Fácil** - Copie resultados con un solo clic
## Guía de Inicio Rápido
### Paso 1: Analizar Configuraciones
1. Conéctese a **WiFi o 2G** (Internet sin restricciones)
2. Abra PingSieve
3. Toque el botón **Cargar** para obtener configuraciones VLESS
4. Espere a que la lista se cargue completamente
### Paso 2: Verificar Configuraciones
1. Cambie a **LTE** (donde las listas blancas están habilitadas)
2. Toque el botón **Verificar** para iniciar la verificación
3. La aplicación hará ping a cada dirección IP automáticamente
4. Las configuraciones válidas aparecerán en la sección "Válido"
5. Espere a que se complete el proceso de verificación
### Paso 3: Usar en Launcher
1. Copie las configuraciones válidas de la sección "Válido" (toque **Importar al portapapeles**)
2. Abra cualquier lanzador compatible con VLESS (por ejemplo, Nekoray, V2rayNG, Hiddify, etc.)
3. Importe las configuraciones en su lanzador
4. Verifique el **retraso/ping real** para cada configuración
5. Si se muestra el ping, la configuración funciona para usted
6. Use la configuración funcional para conectarse
## Instalación
1. Descargue el APK más reciente desde [lanzamientos](https://github.com/fdarryl/VlessChecker/releases/latest)
2. Habilite "Instalar de fuentes desconocidas" en la configuración
3. Instale el APK
4. Otorgue los permisos necesarios
## Configuración
- **Número de Hilos** - Número de hilos de ping concurrentes (predeterminado: 4)
- **Tiempo de Espera** - Tiempo de espera de ping en milisegundos (predeterminado: 1500ms)
- **URL de Análisis** - URL para cargar configuraciones
## Detalles Técnicos
- **Idioma**: Kotlin
- **Sistema de Construcción**: Gradle
- **Marco UI**: Jetpack Compose
- **Min SDK**: 24
- **Target SDK**: 36
## Licencia
Código Abierto - Úselo bajo su propio riesgo.
