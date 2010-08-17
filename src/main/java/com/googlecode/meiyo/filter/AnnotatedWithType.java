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
 *
 * @version $Id$
 */
final class AnnotatedWithType implements Filter {

    private final Class<? extends Annotation> annotationType;

    public AnnotatedWithType(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        return clazz.isAnnotationPresent(this.annotationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "annotatedWith("
            + this.annotationType.getName()
            + ")";
    }

}
