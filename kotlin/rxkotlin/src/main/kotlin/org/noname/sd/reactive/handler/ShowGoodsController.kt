package org.noname.sd.reactive.handler

import com.mongodb.client.model.Filters
import com.mongodb.rx.client.MongoCollection
import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import org.noname.sd.reactive.domain.Good
import org.noname.sd.reactive.domain.Customer
import org.noname.sd.reactive.utils.convert
import org.noname.sd.reactive.utils.getParameter
import rx.Observable

class ShowGoodsController(
    private val users: MongoCollection<Customer>,
    private val items: MongoCollection<Good>
) : RequestHandler<ByteBuf, ByteBuf> {
    override fun handle(req: HttpServerRequest<ByteBuf>, resp: HttpServerResponse<ByteBuf>): Observable<Void> {
        val params = req.queryParameters
        val login = params.getParameter("login", "ShowGoodsController error: no 'login' key")
        val result = users
            .find(Filters.eq("login", login))
            .toObservable()
            .map { it.currencyType }
            .flatMap { currency ->
                items
                    .find()
                    .toObservable()
                    .map { i ->
                        Good(i.name, convert(currency!!, i.price))
                            .toString(currency)
                    }
            }
        return resp.writeString(result)
    }
}