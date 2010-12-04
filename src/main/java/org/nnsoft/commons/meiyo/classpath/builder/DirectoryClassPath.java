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
import java.util.Collection;

import org.nnsoft.commons.meiyo.classpath.ErrorHandler;


/**
 * 
 * @version $Id$
 */
final class DirectoryClassPath extends FileClassPath {

    public DirectoryClassPath(File rootDirectory, ClassLoader classLoader, ErrorHandler errorHandler) {
        super(rootDirectory, classLoader, errorHandler);
    }

    @Override
    protected void traverse(final File file, Collection<ClassPathHandler> classPathHandlers) {
        if (file.isDirectory()) {

            for (File child : file.listFiles()) {
                this.traverse(child, classPathHandlers);
            }

            return;
        }

        super.traverse(file, classPathHandlers);
    }

}
