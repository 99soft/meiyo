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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 *
 * @version $Id$
 */
final class AnnotatedWith implements Filter {

    private final Annotation annotation;

    public AnnotatedWith(Annotation annotation) {
        if (annotation == null) {
            throw new IllegalArgumentException("Parameter 'annotation' must not be null");
        }

        Class<? extends Annotation> annotationType = annotation.getClass();
        Retention retention = annotationType.getAnnotation(Retention.class);
        if (retention == null || RetentionPolicy.RUNTIME != retention.value()) {
            throw new IllegalArgumentException("Annotation @"
                    + annotationType.getName()
                    + " is missing RUNTIME retention");
        }

        this.annotation = annotation;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        Annotation fromElement = clazz.getAnnotation(this.annotation.annotationType());
        return fromElement != null && this.annotation.equals(fromElement);
    }

    @Override
    public String toString() {
        return "annotatedWith("
                + this.annotation
                + ")";
    }

}
