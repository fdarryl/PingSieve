# PingSieve
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![APK](https://img.shields.io/badge/APK-Release-blue.svg)](https://github.com/fdarryl/VlessChecker/releases/latest)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Open%20Source-brightgreen.svg)](#license)
**PingSieve** é um utilitário capaz de procurar automaticamente configurações VLESS funcionais para contornar restrições. Seu principal objetivo é contornar listas brancas na Rússia.
## ⚠️ Aviso Importante
**POR FAVOR, NÃO DESPERDICE TRÁFEGO IRREFLETIDAMENTE. LEMBRE-SE QUE VOCÊ ESTÁ USANDO SOLUÇÕES PÚBLICAS.**
## Funcionalidades
- 🔍 **Busca Automática de Configurações** - Encontra configurações VLESS automaticamente
- ⚡ **Ping Concorrente** - Verificação multithread de IP para resultados rápidos
- ⚙️ **Configurações Personalizáveis** - Ajuste número de threads, timeout e URL
- 🌐 **Suporte a 12 Idiomas**
- 📊 **Estatísticas em Tempo Real**
- 🔗 **Suporte a URL Personalizada**
- 📋 **Cópia Fácil**
## Guia Rápido
### Passo 1: Fazer parsing das configurações
1. Conecte-se ao **WiFi ou 2G** (internet sem restrições)
2. Abra o PingSieve
3. Toque em **Carregar** para buscar as configurações VLESS
4. Aguarde a lista carregar completamente
### Passo 2: Verificar configurações
1. Mude para **LTE** (onde as listas brancas estão ativas)
2. Toque em **Verificar** para iniciar
3. O app fará ping em cada IP automaticamente
4. As configurações válidas aparecerão em "Válido"
5. Aguarde o término do processo
### Passo 3: Usar em um launcher
1. Copie as configurações válidas (toque em **Importar para área de transferência**)
2. Importe para um launcher compatível com VLESS (Nekoray, V2rayNG, Hiddify etc.)
3. Verifique o **delay/ping real**; se aparecer, a configuração funciona
## Instalação
1. Baixe o APK mais recente em [releases](https://github.com/fdarryl/VlessChecker/releases/latest)
2. Habilite instalações de fontes desconhecidas
3. Instale o APK
## Técnicas
- **Linguagem**: Kotlin
- **UI**: Jetpack Compose
## Licença
Open Source - Use por sua conta e risco.
