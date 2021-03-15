package org.noname.sd.reactive.handler

import com.mongodb.rx.client.MongoCollection
import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import org.noname.sd.reactive.domain.Customer
import org.noname.sd.reactive.utils.getParameter
import rx.Observable

class RegisterController(
    private val users: MongoCollection<Customer>
) : RequestHandler<ByteBuf, ByteBuf> {
    override fun handle(req: HttpServerRequest<ByteBuf>, resp: HttpServerResponse<ByteBuf>): Observable<Void> {
        val params = req.queryParameters
        val login = params.getParameter("login", "RegisterController: no 'login' key")
        val currency = params.getParameter("currency", "RegisterController: no 'currency' key")
        val user = Customer(login, currency)
        val result = users.insertOne(user).map { user.toString() }
        return resp.writeString(result)
    }
}