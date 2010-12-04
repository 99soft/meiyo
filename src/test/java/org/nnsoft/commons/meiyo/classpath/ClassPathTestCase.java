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

import static org.nnsoft.commons.meiyo.classpath.builder.ClassPathBuilder.createClassPathByDefaults;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.and;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.any;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.classNameMatches;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.inSubpackage;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.isAbstract;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.isAnnotation;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.isPublic;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.not;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 
 * @version $Id$
 */
public final class ClassPathTestCase {

    @Test
    public void justPrint() {
        final List<Class<?>> classes = new ArrayList<Class<?>>();

        createClassPathByDefaults()
            .ifMatches(and(
                    inSubpackage("org.nnsoft.commons.meiyo.classpath"),
                    isPublic(),
                    not(isAbstract()),
                    not(isAnnotation()),
                    not(classNameMatches(".*TestCase")))).handle(new ClassPathEntryHandler() {

                    public void doHandle(ClassPathEntry classPathEntry) {
                        classes.add(classPathEntry.getClazz());
                    }

                },
                new ClassPathEntryHandler() {

                    public void doHandle(ClassPathEntry classPathEntry) {
                        System.out.println(">>>> " + classPathEntry);
                    }

                })
            .ifMatches(any()).handle(new ClassPathEntryHandler() {

                public void doHandle(ClassPathEntry classPathEntry) {
                    System.out.println("[INFO] found " + classPathEntry);
                }

            })
            .scan();

        assert 0 < classes.size();
    }

}
