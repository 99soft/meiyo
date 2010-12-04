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
package org.nnsoft.commons.meiyo.classpath.builder;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.nnsoft.commons.meiyo.classpath.ClassPath;
import org.nnsoft.commons.meiyo.classpath.ClassPathEntryHandler;
import org.nnsoft.commons.meiyo.classpath.ErrorHandler;
import org.nnsoft.commons.meiyo.classpath.LinkedHandlerBuilder;
import org.nnsoft.commons.meiyo.classpath.filter.Filter;

/**
 * 
 *
 * @version $Id$
 */
final class CompositeClassPath implements ClassPath {

    private static final Pattern JAR_FILE = Pattern.compile(".+\\.(jar|zip)", Pattern.CASE_INSENSITIVE);

    private final List<ClassPathHandler> handlers = new LinkedList<ClassPathHandler>();

    private final String[] paths;

    private final ClassLoader classLoader;

    private final ErrorHandler errorHandler;

    public CompositeClassPath(String[] paths, ClassLoader classLoader, ErrorHandler errorHandler) {
        this.paths = paths;
        this.classLoader = classLoader;
        this.errorHandler = errorHandler;
    }

    public LinkedHandlerBuilder ifMatches(final Filter filter) {
        return new LinkedHandlerBuilder() {

            public ClassPath handle(final ClassPathEntryHandler...entryHandler) {
                CompositeClassPath.this.handlers.add(new ClassPathHandler(filter, entryHandler));

                return CompositeClassPath.this;
            }

        };
    }

    public void scan() {
        for (String path: paths) {
            File file = new File(path);
            if (file.isDirectory()) {
                new DirectoryClassPath(file, this.classLoader, this.errorHandler).scan(this.handlers);
            } else {
                if (JAR_FILE.matcher(path).matches()) {
                    new JARClassPath(file, this.classLoader, this.errorHandler).scan(this.handlers);
                } else {
                    new FileClassPath(file, this.classLoader, this.errorHandler).scan(this.handlers);
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
