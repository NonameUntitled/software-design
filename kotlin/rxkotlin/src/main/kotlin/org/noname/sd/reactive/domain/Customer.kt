package org.noname.sd.reactive.domain

class Customer(private val login: String, val currencyType: String) {
    override fun toString() = "Login: ${login}. Currency type: ${currencyType}."
}