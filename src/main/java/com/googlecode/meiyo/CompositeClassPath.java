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
import java.util.ArrayList;
import java.util.List;

import com.googlecode.meiyo.filter.Filter;

/**
 * 
 *
 * @version $Id$
 */
final class CompositeClassPath implements ClassPath {

    private final List<ClassPath> classPaths = new ArrayList<ClassPath>();

    public void addClasspath(ClassPath classPath) {
        this.classPaths.add(classPath);
    }

    /**
     * {@inheritDoc}
     */
    public void acceptVisitor(Filter matcher, ClassPathVisitor visitor) {
        for (ClassPath classPath : this.classPaths) {
            classPath.acceptVisitor(matcher, visitor);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.classPaths.size(); i++) {
            if (i > 0) {
                builder.append(File.pathSeparator);
            }
            builder.append(this.classPaths.get(i));
        }
        return builder.toString();
    }

}
