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
        this.wrapped = binder;

        try
        {
            this.configure();
        }
        finally
        {
            this.wrapped = null;
        }
    }

    public abstract void configure();

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Class> handleType()
    {
        return this.wrapped.handleType();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Constructor> handleConstructor()
    {
        return this.wrapped.handleConstructor();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Field> handleField()
    {
        return this.wrapped.handleField();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Method> handleMethod()
    {
        return this.wrapped.handleMethod();
    }

}
