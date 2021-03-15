package org.noname.sd.reactive.server

import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServer
import io.reactivex.netty.protocol.http.server.RequestHandler

object ServerFactory {
    fun newServer(controllers: Map<String, RequestHandler<ByteBuf, ByteBuf>>): HttpServer<ByteBuf, ByteBuf> =
        HttpServer
            .newServer(9091)
            .start { req, resp -> controllers[req.uri.split("?")[0]]!!.handle(req, resp) }
}