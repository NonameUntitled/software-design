package org.noname.sd.reactive.database

import com.mongodb.rx.client.MongoClients
import com.mongodb.rx.client.MongoDatabase

object DatabaseFactory {
    fun newDatabase(): MongoDatabase = MongoClients
        .create("mongodb://localhost:27017")
        .getDatabase("magazine")
}