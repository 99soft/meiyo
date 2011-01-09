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
package org.nnsoft.commons.meiyo.classvisitor;

import static org.nnsoft.commons.meiyo.classvisitor.ClassVisitor.newClassVisitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * FILL ME.
 */
public final class ClassVisitorTestCase {

    private boolean foundClassAnnotation = false;

    private boolean foundConstructorAnnotation = false;

    private boolean foundMethodAnnotation = false;

    @BeforeClass
    public void setUp() {
        newClassVisitor()
            .handleType().annotatedWith(ClassAnnotation.class).withHandler(new AnnotationHandler<Class, ClassAnnotation>() {
                public void handle(Class annnotatedElement, ClassAnnotation annotation) {
                    foundClassAnnotation = true;
                }
            })
            .handleConstructor().annotatedWith(ConstructorAnnotation.class).withHandler(new AnnotationHandler<Constructor, ConstructorAnnotation>() {
                public void handle(Constructor annnotatedElement, ConstructorAnnotation annotation) {
                    foundConstructorAnnotation = true;
                }
            })
            .handleMethod().annotatedWith(MethodAnnotation.class).withHandler(new AnnotationHandler<Method, MethodAnnotation>() {
                public void handle(Method annnotatedElement, MethodAnnotation annotation) {
                    foundMethodAnnotation = true;
                }
            })
            .visit(AnnotatedBean.class);
    }

    @Test
    public void verifyClassAnnotationsFound() {
        assert this.foundClassAnnotation;
    }

    @Test
    public void verifyConstructorAnnotationsFound() {
        assert this.foundConstructorAnnotation;
    }

    @Test
    public void verifyMethodAnnotationsFound() {
        assert this.foundMethodAnnotation;
    }

}
