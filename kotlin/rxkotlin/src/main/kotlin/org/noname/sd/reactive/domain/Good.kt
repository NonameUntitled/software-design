package org.noname.sd.reactive.domain

class Good(
    val name: String,
    val price: Double
) {
    override fun toString() = toString("Usd")

    fun toString(currencyType: String) = "Name: ${name}. Price: ${price}. Currency type: ${currencyType}."
}