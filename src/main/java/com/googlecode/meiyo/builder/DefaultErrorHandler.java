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

import java.io.File;
import java.io.IOException;

import com.googlecode.meiyo.ErrorHandler;

/**
 * 
 *
 * @version $Id$
 */
final class DefaultErrorHandler implements ErrorHandler {

    /**
     * {@inheritDoc}
     */
    public void onClassNotFound(String className) {
        // do nothing, just ignore it
    }

    /**
     * {@inheritDoc}
     */
    public void onJARReadingError(File file, IOException e) {
        throw new RuntimeException("An error occurred while loading '"
                + file
                + "' jar entry", e);
    }

}
