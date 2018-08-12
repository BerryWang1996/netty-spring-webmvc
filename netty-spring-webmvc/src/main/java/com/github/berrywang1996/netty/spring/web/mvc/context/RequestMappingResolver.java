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

package com.github.berrywang1996.netty.spring.web.mvc.context;

import com.github.berrywang1996.netty.spring.web.context.MappingResolver;
import com.github.berrywang1996.netty.spring.web.mvc.consts.HttpRequestMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author berrywang1996
 * @since V1.0.0
 */
public class RequestMappingResolver extends MappingResolver<FullHttpRequest, HttpRequestMethod> {

    public RequestMappingResolver(Map<HttpRequestMethod, Method> methods,
                                  Object invokeRef) {
        super(methods, invokeRef);
    }

    @Override
    public void resolve(ChannelHandlerContext ctx, FullHttpRequest msg) {

        // TODO check request method
        Method method = getMethod(HttpRequestMethod.getInstance(msg.method().name()));
        if (method == null) {
            System.out.println("request method is not allowed");
        }

        // TODO request data bind

        // TODO validate data

        // TODO invoke method reference

        // TODO handle return value


    }

}
