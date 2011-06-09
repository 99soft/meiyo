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
package org.nnsoft.commons.meiyo.classpath;

import java.util.Collection;
import java.util.LinkedList;

import org.nnsoft.commons.meiyo.classpath.filter.Filter;

/**
 * FILL ME
 */
final class MatcherImpl
    implements Matcher
{

    private final Collection<ClassPathHandler> handlers = new LinkedList<ClassPathHandler>();

    public LinkedHandlerBuilder ifMatches( final Filter filter )
    {
        if ( filter == null )
        {
            throw new IllegalArgumentException( "Filter must not be null" );
        }

        return new LinkedHandlerBuilder()
        {

            public void handleWith( final ClassPathEntryHandler... entryHandlers )
            {
                if ( entryHandlers == null || entryHandlers.length == 0 )
                {
                    throw new IllegalArgumentException( "At least one ClassPathEntryHandler must be specified" );
                }

                MatcherImpl.this.handlers.add( new ClassPathHandler( filter, entryHandlers ) );
            }

        };
    }

    public Collection<ClassPathHandler> getHandlers()
    {
        return this.handlers;
    }

}
