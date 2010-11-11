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
package org.nnsoft.commons.meiyo.classpath;

/**
 * A classpath entry is a class found while performing the scan operations.
 *
 * @version $Id$
 */
public final class ClassPathEntry {

    /**
     * The class found during the scan operations.
     */
    private final Class<?> clazz;

    /**
     * The classpath reference the the class was found.
     */
    private final ClassPath classPath;

    /**
     * Creates a new classpath entry.
     *
     * @param clazz the class found during the scan operations.
     * @param classPath the classpath reference the the class was found.
     */
    public ClassPathEntry(Class<?> clazz, ClassPath classPath) {
        if (clazz == null) {
            throw new IllegalArgumentException("Parameter 'clazz' must not be null");
        }
        if (classPath == null) {
            throw new IllegalArgumentException("Parameter 'classPath' must not be null");
        }
        this.clazz = clazz;
        this.classPath = classPath;
    }

    /**
     * Return the class found during the scan operations.
     *
     * @return the class found during the scan operations.
     */
    public Class<?> getClazz() {
        return this.clazz;
    }

    /**
     * Return the classpath reference the the class was found.
     *
     * @return the classpath reference the the class was found.
     */
    public ClassPath getClassPath() {
        return this.classPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.clazz.getName()
            + " ("
            + this.classPath
            + ")";
    }

}
