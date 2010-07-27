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

/**
 * 
 * @version $Id$
 */
class FileClassPath extends AbstractClassPath {

    public FileClassPath(File root, ClassLoader classLoader) {
        super(root.getAbsolutePath(), classLoader);
        this.traverse(root);
    }

    protected void traverse(final File file) {
        this.addEntry(file.getAbsolutePath().substring(this.toString().length() + 1));
    }

}
