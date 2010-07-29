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

import java.lang.annotation.Annotation;

/**
 * 
 * @version $Id$
 */
public final class Filters {

    /**
     * This class can't be instantiated directly
     */
    private Filters() {
        // do nothing
    }

    public static Filter and(Filter a, Filter b) {
        return new And(a, b);
    }

    public static Filter annotatedWith(Annotation annotation) {
        return new AnnotatedWith(annotation);
    }

    public static Filter annotatedWithType(Class<? extends Annotation> annotationType) {
        return new AnnotatedWithType(annotationType);
    }

    public static Filter any() {
        return new Any();
    }

    public static Filter inPackage(String targetPackage) {
        return new InPackage(targetPackage);
    }

    public static Filter inSubpackage(String targetPackage) {
        return new InSubpackage(targetPackage);
    }

    public static Filter isAssignableTo(Class<?> superclass) {
        return new IsAssignableTo(superclass);
    }

    public static Filter nand(Filter a, Filter b) {
        return new Not(new And(a, b));
    }

    public static Filter nor(Filter a, Filter b) {
        return new Not(new Or(a, b));
    }

    public static Filter not(Filter delegate) {
        return new Not(delegate);
    }

    public static Filter or(Filter a, Filter b) {
        return new Or(a, b);
    }

    public static Filter xor(Filter a, Filter b) {
        return new Xor(a, b);
    }

    public static Filter xnor(Filter a, Filter b) {
        return new Not(new Xor(a, b));
    }

}
