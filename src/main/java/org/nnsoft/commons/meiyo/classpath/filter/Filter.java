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
package org.nnsoft.commons.meiyo.classpath.filter;

/**
 * A filter is a class, found in the classpath, discriminator.
 */
public interface Filter
{

    /**
     * Returns {@code true} if the given class satisfies the requirements, {@code false} otherwise.
     * 
     * @return {@code true} if the given class satisfies the requirements, {@code false} otherwise.
     */
    boolean matches( Class<?> clazz );

    Filter and( Filter other );

    Filter nand( Filter other );

    Filter or( Filter other );

    Filter nor( Filter other );

    Filter xor( Filter other );

    Filter xnor( Filter other );

}
