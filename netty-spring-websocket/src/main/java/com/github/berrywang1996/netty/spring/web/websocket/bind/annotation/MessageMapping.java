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

package com.github.berrywang1996.netty.spring.web.websocket.bind.annotation;

import com.github.berrywang1996.netty.spring.web.websocket.consts.MessageType;

import java.lang.annotation.*;

/**
 * @author berrywang1996
 * @since V1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageMapping {

    /**
     * url
     */
    String[] value() default "";

    /**
     * message producer
     */
    MessageType messageType() default MessageType.TEXT_MESSAGE;

    /**
     * port. If port is null, the application will map the method
     */
    int[] port() default {};

}
