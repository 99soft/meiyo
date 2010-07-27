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
package com.googlecode.meiyo;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 
 *
 * @version $Id$
 */
public final class ClassPathFactory {

    private static final String JAVA_CLASS_PATH = "java.class.path";

    private static final ClassLoader DEFULT_CLASSLOADER = Thread.currentThread().getContextClassLoader();

    private static final Pattern JAR_FILE = Pattern.compile(".+\\.[jJ][aA][rR]");

    /**
     * This class can't be instantiated.
     */
    private ClassPathFactory() {
        // do nothing
    }

    public static ClassPath createFromJVM() {
        return createFromJVM(DEFULT_CLASSLOADER);
    }

    public static ClassPath createFromJVM(ClassLoader classLoader) {
        return createFromPath(System.getProperty(JAVA_CLASS_PATH), classLoader);
    }

    public static ClassPath createFromPath(String classpath) {
        return createFromPath(classpath, DEFULT_CLASSLOADER);
    }

    public static ClassPath createFromPath(String classpath, ClassLoader classLoader) {
        return createFromPaths(classLoader, classpath.split(File.pathSeparator));
    }

    private static ClassPath createFromPaths(ClassLoader classLoader, String...paths) {
        CompositeClassPath compositeClassPath = new CompositeClassPath();

        for (String path: paths) {
            File file = new File(path);
            if (file.isFile()) {
                if (JAR_FILE.matcher(path).matches()) {
                    compositeClassPath.addClasspath(new JARClassPath(file, classLoader));
                } else {
                    compositeClassPath.addClasspath(new FileClassPath(file, classLoader));
                }
            } else if (file.isDirectory()) {
                compositeClassPath.addClasspath(new DirectoryClassPath(file, classLoader));
            }
            // else ignore it
        }

        return compositeClassPath;
    }

}
