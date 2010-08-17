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
import java.util.ArrayList;
import java.util.List;

import com.googlecode.meiyo.ClassPath;
import com.googlecode.meiyo.ClassPathEntry;
import com.googlecode.meiyo.ClassPathHandler;
import com.googlecode.meiyo.ErrorHandler;

/**
 * 
 *
 * @version $Id$
 */
abstract class AbstractClassPath implements ClassPath {

    /**
     * Regular expression that matches a Java identifier.
     */
    private static final String CLASS_EXTENSION = ".class";

    private final List<ClassPathEntry> entries = new ArrayList<ClassPathEntry>();

    private final String path;

    private final ClassLoader classLoader;

    private final ErrorHandler errorHandler;

    public AbstractClassPath(String path, ClassLoader classLoader, ErrorHandler errorHandler) {
        this.path = path;
        this.classLoader = classLoader;
        this.errorHandler = errorHandler;
    }

    /**
     * {@inheritDoc}
     */
    public void scan(ClassPathHandler... classPathHandlers) {
        for (ClassPathEntry entry : this.entries) {
            for (ClassPathHandler classPathHandler : classPathHandlers) {
                classPathHandler.doHandle(entry);
            }
        }
    }

    protected final void addEntry(String entry) {
        entry = entry.substring(0, entry.lastIndexOf('.')).replace('/', '.');
        try {
            Class<?> clazz = this.classLoader.loadClass(entry);
            this.entries.add(new ClassPathEntry(clazz, this));
        } catch (Throwable t) {
            this.errorHandler.onClassNotFound(entry);
        }
    }

    protected final boolean isJavaClass(File resource) {
        return this.isJavaClass(resource.getName());
    }

    protected final boolean isJavaClass(String resourceName) {
        return resourceName.endsWith(CLASS_EXTENSION);
    }

    @Override
    public final String toString() {
        return this.path;
    }

}
