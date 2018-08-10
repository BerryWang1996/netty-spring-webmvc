/*
 * Copyright 2018 berrywang1996
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.berrywang1996.netty.spring.web.core;

import com.github.berrywang1996.netty.spring.web.startup.NettyServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;

/**
 * @author berrywang1996
 * @version V1.0.0
 */
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyServerBootstrap nettyServerBootstrap;

    private SslContext sslCtx = null;

    public NettyServerChannelInitializer(NettyServerBootstrap nettyServerBootstrap) throws Exception {
        this.nettyServerBootstrap = nettyServerBootstrap;

        // Configure SSL
        if (nettyServerBootstrap.getStartupProperties().getSsl() != null
                && nettyServerBootstrap.getStartupProperties().getSsl().isEnable()) {
            File certificateFile = new File(nettyServerBootstrap.getStartupProperties().getSsl().getCertificate());
            File privateKeyFile = new File(nettyServerBootstrap.getStartupProperties().getSsl().getCertificate());
            sslCtx = SslContextBuilder.forServer(certificateFile, privateKeyFile).build();
        }
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new HttpServerCodec());
        if (nettyServerBootstrap.getStartupProperties().getGzip().isEnable()) {
            p.addLast(
                    "gzip",
                    new NettyServerHttpContentCompressor(
                            nettyServerBootstrap.getStartupProperties().getGzip().getCompressionLevel(),
                            nettyServerBootstrap.getStartupProperties().getGzip().getWindowBits(),
                            nettyServerBootstrap.getStartupProperties().getGzip().getMemLevel(),
                            nettyServerBootstrap.getStartupProperties().getGzip().getContentSizeThreshold(),
                            nettyServerBootstrap.getStartupProperties().getGzip().getTypes())
            );
        }
        p.addLast("aggregator", new HttpObjectAggregator(65536));
        p.addLast("chunked", new ChunkedWriteHandler());
        // TODO server handler
    }

}
