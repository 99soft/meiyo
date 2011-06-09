/*
 *    Copyright 2010-2011 The Meiyo Team
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
package org.nnsoft.commons.meiyo.classvisitor;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * FILL ME.
 */
final class ClassVisitorImpl
    implements ClassVisitor
{

    private static final String JAVA_PACKAGE = "java";

    private final AnnotationHandlerBinderImpl binder;

    public ClassVisitorImpl( final AnnotationHandlerBinderImpl binder )
    {
        this.binder = binder;
    }

    public void visit( final Class<?> type )
    {
        if ( type == null || type.getPackage().getName().startsWith( JAVA_PACKAGE ) )
        {
            return;
        }

        // TYPE
        this.visitElements( type );

        if ( !type.isInterface() )
        {
            // CONSTRUCTOR
            this.visitElements( new PrivilegedAction<Constructor<?>[]>()
            {
                public Constructor<?>[] run()
                {
                    return type.getDeclaredConstructors();
                }
            } );

            // FIELD
            this.visitElements( new PrivilegedAction<Field[]>()
            {
                public Field[] run()
                {
                    return type.getDeclaredFields();
                }
            } );
        }

        // METHOD
        this.visitElements( new PrivilegedAction<Method[]>()
        {
            public Method[] run()
            {
                return type.getDeclaredMethods();
            }
        } );

        this.visit( type.getSuperclass() );
    }

    private <AE extends AnnotatedElement> void visitElements( PrivilegedAction<AE[]> action )
    {
        AE[] annotatedElements = null;
        if ( System.getSecurityManager() != null )
        {
            annotatedElements = AccessController.doPrivileged( action );
        }
        else
        {
            annotatedElements = action.run();
        }
        this.visitElements( annotatedElements );
    }

    private void visitElements( AnnotatedElement... annotatedElements )
    {
        for ( AnnotatedElement element : annotatedElements )
        {
            for ( Annotation annotation : element.getAnnotations() )
            {
                AnnotationHandler<AnnotatedElement, Annotation> handler =
                    this.binder.getHandler( element.getClass(), annotation.annotationType() );

                if ( handler != null )
                {
                    handler.handle( element, annotation );
                }
            }
        }
    }

}
