/*
 *    Copyright 2010 The Meiyo Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.googlecode.meiyo.builder;

import com.googlecode.meiyo.ClassPath;
import com.googlecode.meiyo.ErrorHandler;

/**
 * 
 * @version $Id$
 */
public final class ErrorHandlerBuilder {

    private static final ErrorHandler DEFAULT_ERROR_HANDLER = new DefaultErrorHandler();

    private final CompositeClassPath compositeClassPath;

    protected ErrorHandlerBuilder(CompositeClassPath compositeClassPath) {
        this.compositeClassPath = compositeClassPath;
    }

    public ClassPath usingDefaultErrorHandler() {
        return this.usingErrorHandler(DEFAULT_ERROR_HANDLER);
    }

    public ClassPath usingErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new IllegalArgumentException("Parameter 'errorHandler' must not be null");
        }

        this.compositeClassPath.setErrorHandler(errorHandler);
        return compositeClassPath;
    }

}
