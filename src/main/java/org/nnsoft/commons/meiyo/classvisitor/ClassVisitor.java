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

import static org.nnsoft.commons.meiyo.classvisitor.privilegedactions.PrivilegedActions.getDeclaredConstructors;
import static org.nnsoft.commons.meiyo.classvisitor.privilegedactions.PrivilegedActions.getDeclaredFields;
import static org.nnsoft.commons.meiyo.classvisitor.privilegedactions.PrivilegedActions.getDeclaredMethods;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
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

    public void visit(Class<?> type) {
        if (type == null || Object.class == type) {
            return;
        }

        // TYPE
        visitElements(type);

        if (!type.isInterface()) {
            // CONSTRUCTOR
            visitElements(getDeclaredConstructors(type));

            // FIELD
            visitElements(getDeclaredFields(type));
        }

        // METHOD
        visitElements(getDeclaredMethods(type));

        this.visit(type.getSuperclass());
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
