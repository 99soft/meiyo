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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Simple filters language implementation.
 */
public final class Filters
{

    /**
     * This class can't be instantiated.
     */
    private Filters()
    {
        // do nothing
    }

    public static Filter annotatedWith( Annotation annotation )
    {
        if ( annotation == null )
        {
            throw new IllegalArgumentException( "Parameter 'annotation' must not be null" );
        }

        checkForRuntimeRetention( annotation.annotationType() );

        return new AnnotatedWith( annotation );
    }

    public static Filter annotatedWithType( Class<? extends Annotation> annotationType )
    {
        if ( annotationType == null )
        {
            throw new IllegalArgumentException( "Parameter 'annotationType' must not be null" );
        }

        checkForRuntimeRetention( annotationType );

        return new AnnotatedWithType( annotationType );
    }

    private static void checkForRuntimeRetention( Class<? extends Annotation> annotationType )
    {
        Retention retention = annotationType.getAnnotation( Retention.class );
        if ( retention == null || RetentionPolicy.RUNTIME != retention.value() )
        {
            throw new IllegalArgumentException( "Annotation @" + annotationType.getName()
                + " is missing RUNTIME retention" );
        }
    }

    public static Filter any()
    {
        return new Any();
    }

    public static Filter classNameMatches( String regex )
    {
        if ( regex == null )
        {
            throw new IllegalArgumentException( "Parameter 'regex' must not be null" );
        }
        if ( regex.length() == 0 )
        {
            throw new IllegalArgumentException( "Empty 'regex' not allowed" );
        }

        return new ClassNameMatchesFilter( regex );
    }

    public static Filter containsMethod( String name, Class<?>... argumentsType )
    {
        if ( name == null )
        {
            throw new IllegalArgumentException( "Parameter 'name' must not be null" );
        }

        return new ContainsMethod( name, argumentsType );
    }

    public static Filter inPackage( String targetPackage )
    {
        if ( targetPackage == null )
        {
            throw new IllegalArgumentException( "Parameter 'targetPackage' must be not null" );
        }

        return new InPackage( targetPackage );
    }

    public static Filter inSubpackage( String targetPackage )
    {
        if ( targetPackage == null )
        {
            throw new IllegalArgumentException( "Parameter 'targetPackage' must be not null" );
        }

        return new InSubpackage( targetPackage );
    }

    public static Filter isAbstract()
    {
        return new IsAbstract();
    }

    public static Filter isAnnotation()
    {
        return new IsAnnotation();
    }

    public static Filter isAssignableTo( Class<?> superclassOrInterface )
    {
        if ( superclassOrInterface == null )
        {
            throw new IllegalArgumentException( "Parameter 'superclassOrInterface' must be not null" );
        }

        return new IsAssignableTo( superclassOrInterface );
    }

    public static Filter isFinal()
    {
        return new IsFinal();
    }

    public static Filter isInterface()
    {
        return new IsInterface();
    }

    public static Filter isPrivate()
    {
        return new IsPrivate();
    }

    public static Filter isPublic()
    {
        return new IsPublic();
    }

    public static Filter isStatic()
    {
        return new IsStatic();
    }

    public static Filter isStrict()
    {
        return new IsStrict();
    }

    public static Filter not( Filter delegate )
    {
        if ( delegate == null )
        {
            throw new IllegalArgumentException( "Parameter 'delegate' must be not null" );
        }

        return new Not( delegate );
    }

}
