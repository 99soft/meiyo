package org.nnsoft.commons.meiyo.classpath.filter;

/*
 *    Copyright 2010-2011 The 99 Software Foundation
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

import java.lang.annotation.Annotation;

/**
 * A filter that verifies the given annotation type is present on the class found.
 */
final class AnnotatedWithType
    extends AbstractFilter
{

    /**
     * The annotation type has to be present on the class found.
     */
    private final Class<? extends Annotation> annotationType;

    /**
     * Creates a new annotation type matcher filter.
     * 
     * @param annotationType the annotation type has to be present on the class found.
     */
    public AnnotatedWithType( Class<? extends Annotation> annotationType )
    {
        this.annotationType = annotationType;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches( Class<?> clazz )
    {
        return clazz.isAnnotationPresent( this.annotationType );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format( "annotatedWith(%s)", this.annotationType.getName() );
    }

}
