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
import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * FILL ME.
 *
 * @version $Id$
 */
public final class ClassVisitor {

    @SuppressWarnings("unchecked")
    public void visit(Class<?> type) {
        if (type == null || Object.class == type) {
            return;
        }

        // TYPE
        this.visitElements(type);

        if (type.isInterface()) {
            // CONSTRUCTOR
            this.visitElements(run(new GetDeclaredConstructorsPrivilegedAction(type)));

            // FIELD
            this.visitElements(run(new GetDeclaredFieldsPrivilegedAction(type)));
        }

        // METHOD
        this.visitElements(run(new GetDeclaredMethodsPrivilegedAction(type)));

        this.visit(type.getSuperclass());
    }

    private <E extends AnnotatedElement> void visitElements(E...elements) {
        for (E element : elements) {
            for (Annotation annotation : element.getAnnotations()) {
                
            }
        }
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

}
