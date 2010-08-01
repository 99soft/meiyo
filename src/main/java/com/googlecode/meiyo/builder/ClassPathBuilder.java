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

import com.googlecode.meiyo.ClassPath;

/**
 * 
 * @version $Id$
 */
public final class ClassPathBuilder {

    private static final String JAVA_CLASS_PATH = "java.class.path";

    public static ClassPath createByDefaults() {
        return new ClassPathBuilder()
                    .createFromJVM()
                    .usingDefaultClassLoader()
                    .usingDefaultErrorHandler();
    }

    public ClassLoaderBuilder createFromJVM() {
        return this.createFromPath(System.getProperty(JAVA_CLASS_PATH));
    }

    public ClassLoaderBuilder createFromPath(String classpath) {
        if (classpath == null || classpath.length() == 0) {
            throw new IllegalArgumentException("Parameter 'classpath' must not be empty");
        }

        return this.createFromPath(classpath.split(File.pathSeparator));
    }

    public ClassLoaderBuilder createFromPath(String...classpath) {
        if (classpath == null || classpath.length == 0) {
            throw new IllegalArgumentException("Parameter 'classpath' must not be empty");
        }

        CompositeClassPath compositeClassPath = new CompositeClassPath();
        compositeClassPath.setPaths(classpath);
        return new ClassLoaderBuilder(compositeClassPath);
    }

}
