package org.nnsoft.commons.meiyo.classpath.filter;

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

/**
 * @version $Id$
 */
final class Xor
    extends AbstractFilter
{

    private final Filter a;

    private final Filter b;

    public Xor( Filter a, Filter b )
    {
        this.a = a;
        this.b = b;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches( Class<?> clazz )
    {
        return this.a.matches( clazz ) ^ this.b.matches( clazz );
    }

    @Override
    public String toString()
    {
        return String.format( "xor(%s, %s)", this.a, this.b );
    }

}