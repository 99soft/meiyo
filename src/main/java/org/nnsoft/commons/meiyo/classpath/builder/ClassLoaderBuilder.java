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
package com.googlecode.meiyo.classpath.builder;

/**
 * 
 * @version $Id$
 */
public final class ClassLoaderBuilder {

    private static final ClassLoader DEFULT_CLASSLOADER = Thread.currentThread().getContextClassLoader();

    private final CompositeClassPath compositeClassPath;

    protected ClassLoaderBuilder(CompositeClassPath compositeClassPath) {
        this.compositeClassPath = compositeClassPath;
    }

    public ErrorHandlerBuilder usingDefaultClassLoader() {
        return this.usingClassLoader(DEFULT_CLASSLOADER);
    }

    public ErrorHandlerBuilder usingClassLoader(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("Parameter 'classLoader' must not be null");
        }

        this.compositeClassPath.setClassLoader(classLoader);

        ErrorHandlerBuilder errorHandlerBuilder = new ErrorHandlerBuilder(this.compositeClassPath);
        return errorHandlerBuilder;
    }

}