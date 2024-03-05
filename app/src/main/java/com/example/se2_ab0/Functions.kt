package com.example.se2_ab0

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


fun findCommonDivisors(numberString: String): String {
    val numberList = numberString.map { it.toString().toIntOrNull() ?: 0 }

    val indexPairs = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until numberList.size) {
        for (j in i + 1 until numberList.size) {
            if (hasCommonDivisor(numberList[i], numberList[j])) {
                indexPairs.add(Pair(i, j))
            }
        }
    }

    return if (indexPairs.isEmpty()) "Keine gemeinsamen Teiler gefunden"
    else "Indizes mit gemeinsamen Teilern: $indexPairs"
}

fun hasCommonDivisor(a: Int, b: Int): Boolean {
    for (i in 2..minOf(a, b)) {
        if (a % i == 0 && b % i == 0) return true
    }
    return false
}

fun sendToServer(mnr: String, onResult: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val socket: Socket = Socket("se2-submission.aau.at", 20080)
        val writer: PrintWriter = PrintWriter(socket.getOutputStream(), true)
        val reader: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))

        try {
            writer.println(mnr)
            val response = reader.readLine()
            withContext(Dispatchers.Main) {
                onResult(response)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult("Fehler: ${e.message}")
            }
        } finally {
            try {
                writer.close()
                reader.close()
                socket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}