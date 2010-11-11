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
package org.nnsoft.commons.meiyo.classvisitor.privilegedactions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * FILL ME.
 *
 * @version $Id$
 */
public final class PrivilegedActions {

    /**
     * This class can't be instantiated.
     */
    private PrivilegedActions() {
        // do nothing
    }

    public static Constructor<?>[] getDeclaredConstructors(Class<?> type) {
        return run(new GetDeclaredConstructorsPrivilegedAction(type));
    }

    public static Field[] getDeclaredFields(Class<?> type) {
        return run(new GetDeclaredFieldsPrivilegedAction(type));
    }

    public static Method[] getDeclaredMethods(Class<?> type) {
        return run(new GetDeclaredMethodsPrivilegedAction(type));
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
