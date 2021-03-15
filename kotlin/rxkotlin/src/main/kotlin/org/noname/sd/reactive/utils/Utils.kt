package org.noname.sd.reactive.utils

private val coefficients = hashMapOf(
    "Rub" to 100.0,
    "Usd" to 200.0,
    "Eur" to 300.0
)

fun convert(currency: String, value: Double): Double {
    return coefficients[currency]!! * value
}

fun Map<String, List<String>>.getParameter(key: String, message: String): String {
    val results = this[key]
    if (results == null || results.isEmpty()) {
        throw RuntimeException(message)
    }
    return results[0]
}