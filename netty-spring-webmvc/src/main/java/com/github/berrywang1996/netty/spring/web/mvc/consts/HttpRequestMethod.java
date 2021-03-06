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

package com.github.berrywang1996.netty.spring.web.mvc.consts;

/**
 * @author berrywang1996
 * @since V1.0.0
 */
public enum HttpRequestMethod {

    GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE, ALL;

    public static HttpRequestMethod getInstance(String name) {
        switch (name.toUpperCase()) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            case "PATCH":
                return PATCH;
            case "HEAD":
                return HEAD;
            case "OPTIONS":
                return OPTIONS;
            case "TRACE":
                return TRACE;
            case "ALL":
                return ALL;
            default:
                return null;
        }
    }

}
