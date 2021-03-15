package org.noname.sd.reactive.handler

import com.mongodb.rx.client.MongoCollection
import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import org.noname.sd.reactive.domain.Good
import org.noname.sd.reactive.utils.getParameter
import rx.Observable

class CreateGoodController(
    private val items: MongoCollection<Good>
) : RequestHandler<ByteBuf, ByteBuf> {
    override fun handle(req: HttpServerRequest<ByteBuf>, resp: HttpServerResponse<ByteBuf>): Observable<Void> {
        val params = req.queryParameters
        val name = params.getParameter("name", "CreateGoodController: no 'name' key")
        val price = params.getParameter("price", "CreateGoodController: no 'currency' key").toDouble()
        val item = Good(name, price)
        val result = items.insertOne(item).map { item.toString() }
        return resp.writeString(result)
    }
}