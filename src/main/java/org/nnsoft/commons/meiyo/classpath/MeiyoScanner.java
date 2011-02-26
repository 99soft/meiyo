/*
 *    Copyright 2010-2011 The Meiyo Team
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
package org.nnsoft.commons.meiyo.classpath;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

/**
 * FILL ME
 */
public final class MeiyoScanner {

    private static final String JAVA_CLASS_PATH = "java.class.path";

    private static final Pattern JAR_FILE = Pattern.compile(".+\\.(jar|zip)", Pattern.CASE_INSENSITIVE);

    private static final String CLASS_EXTENSION = ".class";

    private MeiyoScanner() {
        // do nothing
    }

    public static HandlerConfigurationsBuilder createClassPathFromJVM() {
        return createClassPathFromPath(System.getProperty(JAVA_CLASS_PATH));
    }

    public static HandlerConfigurationsBuilder createClassPathFromPath(final String classpath) {
        if (classpath == null || classpath.length() == 0) {
            throw new IllegalArgumentException("Parameter 'classpath' must not be empty");
        }

        return createClassPathFromPath(classpath.split(File.pathSeparator));
    }

    public static HandlerConfigurationsBuilder createClassPathFromPath(final String...paths) {
        if (paths == null || paths.length == 0) {
            throw new IllegalArgumentException("At least one path has to be specified");
        }

        return new HandlerConfigurationsBuilder() {

            public ClassLoaderBuilder withConfiguration(final HandlerConfiguration...configurations) {
                if (configurations == null || configurations.length == 0) {
                    throw new IllegalArgumentException("At least one HandlerConfiguration has to be specified");
                }
                return withConfiguration(Arrays.asList(configurations));
            }

            public ClassLoaderBuilder withConfiguration(final Collection<HandlerConfiguration> configurations) {
                if (configurations == null || configurations.isEmpty()) {
                    throw new IllegalArgumentException("Parameter 'configurations' must not be null or empty");
                }

                MatcherImpl matcher = new MatcherImpl();
                for (HandlerConfiguration configuration : configurations) {
                    configuration.configure(matcher);
                }

                final Collection<ClassPathHandler> handlers = matcher.getHandlers();

                return new ClassLoaderBuilder() {

                    public ErrorHandlerBuilder usingDefaultClassLoader() {
                        return this.usingClassLoader(Thread.currentThread().getContextClassLoader());
                    }

                    public ErrorHandlerBuilder usingClassLoader(final ClassLoader classLoader) {
                        if (classLoader == null) {
                            throw new IllegalArgumentException("Parameter 'classLoader' must not be null");
                        }

                        return new ErrorHandlerBuilder() {

                            public void scan() {
                                scan(new ErrorHandler() {

                                    public void onJARReadingError(File file, IOException e) {
                                        throw new RuntimeException("An error occurred while loading '"
                                                + file
                                                + "' jar entry", e);
                                    }

                                    public void onClassNotFound(String className) {
                                        // do nothing, just ignore it
                                    }

                                });
                            }

                            public void scan(final ErrorHandler errorHandler) {
                                if (errorHandler == null) {
                                    throw new IllegalArgumentException("Parameter 'errorHandler' must not be null");
                                }

                                for (String path: paths) {
                                    File file = new File(path);
                                    if (JAR_FILE.matcher(path).matches()) {
                                        try {
                                            JarFile jarFile = new JarFile(path);
                                            Enumeration<JarEntry> enumeration = jarFile.entries();
                                            while (enumeration.hasMoreElements()) {
                                                JarEntry entry = enumeration.nextElement();
                                                if (!entry.isDirectory()) {
                                                    handleEntry(entry.getName(), path, handlers, classLoader, errorHandler);
                                                }
                                            }
                                        } catch (IOException e) {
                                            errorHandler.onJARReadingError(file, e);
                                        }
                                    } else {
                                        traverse(file, path, handlers, classLoader, errorHandler);
                                    }
                                    // else ignore it
                                }
                            }
                        };
                    }

                };
            }
        };
    }

    private static final void traverse(final File file,
            final String path,
            final Collection<ClassPathHandler> handlers,
            final ClassLoader classLoader,
            final ErrorHandler errorHandler) {
        if (file.isDirectory()) {

            for (File child : file.listFiles()) {
                traverse(child, path, handlers, classLoader, errorHandler);
            }

            return;
        }

        handleEntry(file.getAbsolutePath().substring(path.length() + 1), path, handlers, classLoader, errorHandler);
    }

    private static final void handleEntry(String entry,
            final String path,
            final Collection<ClassPathHandler> handlers,
            final ClassLoader classLoader,
            final ErrorHandler errorHandler) {
        if (!entry.endsWith(CLASS_EXTENSION)) {
            return;
        }

        entry = entry.substring(0, entry.lastIndexOf('.')).replace('/', '.');
        try {
            Class<?> clazz = classLoader.loadClass(entry);

            for (ClassPathHandler classPathHandler : handlers) {
                classPathHandler.doHandle(path, clazz);
            }
        } catch (Throwable t) {
            errorHandler.onClassNotFound(entry);
        }
    }

}
