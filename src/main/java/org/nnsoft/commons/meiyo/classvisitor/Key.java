package org.nnsoft.commons.meiyo.classvisitor;

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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * FILL ME.
 */
final class Key
{

    private final Class<? extends AnnotatedElement> annotatedElementType;

    private final Class<? extends Annotation> annotationType;

    public Key( Class<? extends AnnotatedElement> annotatedElementType, Class<? extends Annotation> annotationType )
    {
        this.annotatedElementType = annotatedElementType;
        this.annotationType = annotationType;
    }

    protected Class<? extends AnnotatedElement> getAnnotatedElementType()
    {
        return this.annotatedElementType;
    }

    protected Class<? extends Annotation> getAnnotationType()
    {
        return this.annotationType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.annotatedElementType == null ) ? 0 : this.annotatedElementType.hashCode() );
        result = prime * result + ( ( this.annotationType == null ) ? 0 : this.annotationType.hashCode() );
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }

        Key other = (Key) obj;

        if ( this.annotatedElementType == null )
        {
            if ( other.getAnnotatedElementType() != null )
            {
                return false;
            }
        }
        else if ( !this.annotatedElementType.equals( other.getAnnotatedElementType() ) )
        {
            return false;
        }

        if ( this.annotationType == null )
        {
            if ( other.getAnnotationType() != null )
            {
                return false;
            }
        }
        else if ( !this.annotationType.equals( other.getAnnotationType() ) )
        {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format( "Key [annotatedElementType=%s, annotationType=%s]", this.annotatedElementType,
                              this.annotationType );
    }

}
