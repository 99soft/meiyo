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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class AnnotationHandlerBinderImpl
    implements AnnotationHandlerBinder
{

    private final Map<Key, AnnotationHandler<AnnotatedElement, Annotation>> registry =
        new HashMap<Key, AnnotationHandler<AnnotatedElement, Annotation>>();

    public AnnotatedHandlerBuilder<Class> handleType()
    {
        return this.handleElement( Class.class );
    }

    public AnnotatedHandlerBuilder<Constructor> handleConstructor()
    {
        return this.handleElement( Constructor.class );
    }

    public AnnotatedHandlerBuilder<Field> handleField()
    {
        return this.handleElement( Field.class );
    }

    public AnnotatedHandlerBuilder<Method> handleMethod()
    {
        return this.handleElement( Method.class );
    }

    private <E extends AnnotatedElement> AnnotatedHandlerBuilder<E> handleElement( final Class<E> annotatedElementType )
    {
        return new AnnotatedHandlerBuilder<E>()
        {
            public <A extends Annotation> LinkedHandlingBuilder<E, A> annotatedWith( final Class<A> annotationType )
            {
                if ( annotationType == null )
                {
                    throw new IllegalArgumentException( "Parameter 'annotationType' must not be null" );
                }

                return new LinkedHandlingBuilder<E, A>()
                {
                    @SuppressWarnings( "unchecked" )
                    public void withHandler( AnnotationHandler<E, A> handler )
                    {
                        if ( handler == null )
                        {
                            throw new IllegalArgumentException( "Parameter 'handler' must not be null" );
                        }

                        AnnotationHandlerBinderImpl.this.registry.put( new Key( annotatedElementType, annotationType ),
                                                                       (AnnotationHandler<AnnotatedElement, Annotation>) handler );
                    }
                };
            }
        };
    }

    public AnnotationHandler<AnnotatedElement, Annotation> getHandler( Class<? extends AnnotatedElement> annotatedElementType,
                                                                       Class<? extends Annotation> annotationType )
    {
        return this.registry.get( new Key( annotatedElementType, annotationType ) );
    }

}
