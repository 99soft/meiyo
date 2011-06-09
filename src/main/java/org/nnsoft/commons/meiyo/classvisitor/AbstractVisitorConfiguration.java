package org.nnsoft.commons.meiyo.classvisitor;

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class AbstractVisitorConfiguration
    implements VisitorConfiguration
{

    private AnnotationHandlerBinder wrapped;

    /**
     * {@inheritDoc}
     */
    public final void configure( final AnnotationHandlerBinder binder )
    {
        if ( wrapped != null )
        {
            throw new IllegalStateException( "Re-entry is not allowed." );
        }

        wrapped = binder;

        try
        {
            configure();
        }
        finally
        {
            wrapped = null;
        }
    }

    protected abstract void configure();

    /**
     * {@inheritDoc}
     */
    protected AnnotatedHandlerBuilder<Class> handleType()
    {
        return wrapped.handleType();
    }

    /**
     * {@inheritDoc}
     */
    protected AnnotatedHandlerBuilder<Constructor> handleConstructor()
    {
        return wrapped.handleConstructor();
    }

    /**
     * {@inheritDoc}
     */
    protected AnnotatedHandlerBuilder<Field> handleField()
    {
        return wrapped.handleField();
    }

    /**
     * {@inheritDoc}
     */
    protected AnnotatedHandlerBuilder<Method> handleMethod()
    {
        return wrapped.handleMethod();
    }

}
