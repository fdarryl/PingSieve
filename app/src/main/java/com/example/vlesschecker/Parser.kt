package com.example.vlesschecker

class Parser {

    fun parseLines(text: String): List<String> {
        return text.lines().filter { it.isNotBlank() }
    }

    fun extractIp(line: String): String? {
        val ipRegex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b".toRegex()
        return ipRegex.find(line)?.value
    }
}
