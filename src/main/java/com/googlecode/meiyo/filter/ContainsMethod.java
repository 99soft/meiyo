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
package com.googlecode.meiyo.filter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;

/**
 * A filter that verifies the class found contains a method with the given signature.
 *
 * @version $Id$
 */
final class ContainsMethod implements Filter {

    /**
     * The method name has to be contained in the class found.
     */
    private final String name;

    /**
     * The method arguments type.
     */
    private final Class<?>[] argumentsType;

    /**
     * Creates a new {@code ContainsMethod} instance.
     *
     * @param name the method name has to be contained in the class found.
     * @param argumentsType the optional method arguments type.
     */
    public ContainsMethod(String name, Class<?>...argumentsType) {
        this.name = name;
        this.argumentsType = argumentsType;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        Class<?> current = clazz;
        while (current != null) {
            for (Method method : getDeclaredMethods(current)) {
                if (this.name.equals(method.getName())
                        && Arrays.equals(this.argumentsType, method.getParameterTypes())) {
                    return true;
                }
            }
            current = current.getSuperclass();
        }
        return false;
    }

    /**
     * Returns all the declared methods in the given class, using the privileged access.
     *
     * @param type the class from which extract the declared methods.
     * @return the declared methods array.
     */
    private static Method[] getDeclaredMethods(final Class<?> type) {
        return AccessController.doPrivileged(
                new PrivilegedAction<Method[]>() {
                    public Method[] run() {
                        final Method[] declaredMethods = type.getDeclaredMethods();
                        AccessibleObject.setAccessible(declaredMethods, true);
                        return declaredMethods;
                    }
                });
    }

}
