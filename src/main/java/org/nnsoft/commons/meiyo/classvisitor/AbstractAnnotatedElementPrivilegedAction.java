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
package org.nnsoft.commons.meiyo.classvisitor;

import java.security.PrivilegedAction;

/**
 * FILL ME.
 *
 * @version $Id$
 */
abstract class AbstractMeiyoPrivilegedAction<T> implements PrivilegedAction<T> {

    private final Class<?> type;

    public AbstractMeiyoPrivilegedAction(Class<?> type) {
        this.type = type;
    }

    protected final Class<?> getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    public abstract T run();

}
