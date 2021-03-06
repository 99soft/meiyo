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

import java.util.Arrays;
import java.util.Collection;

public final class MeiyoVisitor
{

    private MeiyoVisitor()
    {
        // do nothing
    }

    public static ClassVisitor createVisitor( VisitorConfiguration... configurations )
    {
        if ( configurations == null || configurations.length == 0 )
        {
            throw new IllegalArgumentException( "At least one VisitorConfiguration has to be specified" );
        }
        return createVisitor( Arrays.asList( configurations ) );
    }

    public static ClassVisitor createVisitor( Collection<VisitorConfiguration> configurations )
    {
        if ( configurations == null || configurations.isEmpty() )
        {
            throw new IllegalArgumentException( "Parameter 'configurations' must not be null or empty" );
        }

        AnnotationHandlerBinderImpl binderImpl = new AnnotationHandlerBinderImpl();
        for ( VisitorConfiguration module : configurations )
        {
            module.configure( binderImpl );
        }

        return new ClassVisitorImpl( binderImpl );
    }

}
