package org.noname.sd.reactive

import org.noname.sd.reactive.database.DatabaseFactory
import org.noname.sd.reactive.handler.CreateGoodController
import org.noname.sd.reactive.handler.RegisterController
import org.noname.sd.reactive.handler.ShowGoodsController
import org.noname.sd.reactive.domain.Good
import org.noname.sd.reactive.domain.Customer
import org.noname.sd.reactive.server.ServerFactory


fun main() {
    val database = DatabaseFactory.newDatabase()
    val goods = database.getCollection("goods", Good::class.java)
    val customers = database.getCollection("customers", Customer::class.java)

    val controllers = hashMapOf(
        "/create" to CreateGoodController(goods),
        "/show" to ShowGoodsController(customers, goods),
        "/register" to RegisterController(customers)
    )

    ServerFactory.newServer(controllers).awaitShutdown()
}

