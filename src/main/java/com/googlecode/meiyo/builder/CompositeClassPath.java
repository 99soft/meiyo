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
import java.util.Arrays;
import java.util.regex.Pattern;

import com.googlecode.meiyo.ClassPath;
import com.googlecode.meiyo.ClassPathHandler;
import com.googlecode.meiyo.ErrorHandler;

/**
 * 
 *
 * @version $Id$
 */
final class CompositeClassPath implements ClassPath {

    private static final Pattern JAR_FILE = Pattern.compile(".+\\.(jar|zip)", Pattern.CASE_INSENSITIVE);

    private String[] paths;

    private ClassLoader classLoader;

    private ErrorHandler errorHandler;

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void scan(ClassPathHandler... classPathHandlers) {
        for (String path: paths) {
            File file = new File(path);
            if (file.isDirectory()) {
                new DirectoryClassPath(file, this.classLoader, this.errorHandler).scan(classPathHandlers);
            } else {
                if (JAR_FILE.matcher(path).matches()) {
                    new JARClassPath(file, this.classLoader, this.errorHandler).scan(classPathHandlers);
                } else {
                    new FileClassPath(file, this.classLoader, this.errorHandler).scan(classPathHandlers);
                }
            }
            // else ignore it
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.paths);
    }

}
