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

import java.lang.reflect.Modifier;

/**
 * 
 */
final class IsStrict
    extends AbstractFilter
{

    /**
     * {@inheritDoc}
     */
    public boolean matches( Class<?> clazz )
    {
        return Modifier.isStrict( clazz.getModifiers() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "isStrict()";
    }

}
