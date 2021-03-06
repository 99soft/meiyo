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

/**
 * @version $Id$
 */
final class Not
    extends AbstractFilter
{

    final Filter delegate;

    public Not( Filter delegate )
    {
        if ( delegate == null )
        {
            throw new IllegalArgumentException( "Parameter 'delegate' must not be null" );
        }
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches( Class<?> clazz )
    {
        return !this.delegate.matches( clazz );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format( "not(%s)", this.delegate );
    }

}
