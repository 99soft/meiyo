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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

import org.nnsoft.commons.meiyo.classvisitor.handlers.AnnotatedHandlerBuilder;
import org.nnsoft.commons.meiyo.classvisitor.handlers.AnnotationHandler;

/**
 * FILL ME.
 *
 * @version $Id$
 */
public final class ClassVisitor {

    private final Map<Key, AnnotationHandler<AnnotatedElement, Annotation>> registry =
        new HashMap<Key, AnnotationHandler<AnnotatedElement, Annotation>>();

    public <E extends AnnotatedElement> AnnotatedHandlerBuilder<E> handleElement(Class<E> elementType) {
        return null;
    }

    public void visit(final Class<?> type) {
        if (type == null || Object.class == type) {
            return;
        }

        // TYPE
        visitElements(type);

        if (!type.isInterface()) {
            // CONSTRUCTOR
            visitElements(run(new PrivilegedAction<Constructor<?>[]>() {
                public Constructor<?>[] run() {
                    return type.getDeclaredConstructors();
                }
            }));

            // FIELD
            visitElements(run(new PrivilegedAction<Field[]>() {
                public Field[] run() {
                    return type.getDeclaredFields();
                }
            }));
        }

        // METHOD
        visitElements(run(new PrivilegedAction<Method[]>() {
            public Method[] run() {
                return type.getDeclaredMethods();
            }
        }));

        this.visit(type.getSuperclass());
    }

    /**
     * Perform action with AccessController.doPrivileged() if possible.
     *
     * @param action - the action to run
     * @return result of running the action
     */
    private static <T> T run(PrivilegedAction<T> action) {
        if (System.getSecurityManager() != null) {
            return AccessController.doPrivileged(action);
        }
        return action.run();
    }

    private void visitElements(AnnotatedElement...elements) {
        for (AnnotatedElement element : elements) {
            for (Annotation annotation : element.getAnnotations()) {
                AnnotationHandler<AnnotatedElement, Annotation> handler =
                    this.registry.get(new Key(element.getClass(), annotation.annotationType()));

                if (handler != null) {
                    handler.handle(element, annotation);
                }
            }
        }
    }

}
