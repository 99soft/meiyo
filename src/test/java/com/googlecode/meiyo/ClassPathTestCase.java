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

import static com.googlecode.meiyo.filter.Filters.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.googlecode.meiyo.builder.ClassPathBuilder;

/**
 * 
 * @version $Id$
 */
public final class ClassPathTestCase {

    @Test
    public void justPrint() {
        final List<Class<?>> classes = new ArrayList<Class<?>>();

        ClassPathBuilder.createByDefaults().scan(
            new ClassPathHandler(and(
                    inSubpackage("com.googlecode.meiyo"),
                    isPublic(),
                    not(isAbstract()),
                    not(isAnnotation()),
                    not(classNameMatches(".*TestCase"))),
                new ClassHandler() {

                    public void doHandle(Class<?> clazz) {
                        classes.add(clazz);
                    }

                },
                new ClassHandler() {

                    public void doHandle(Class<?> clazz) {
                        System.out.println(">>>> " + clazz.getName());
                    }

                }
            ), new ClassPathHandler(any(), new ClassHandler() {

                public void doHandle(Class<?> clazz) {
                    System.out.println("[INFO] found " + clazz.getName());
                }

            })
        );

        assert 0 < classes.size();
    }

}
