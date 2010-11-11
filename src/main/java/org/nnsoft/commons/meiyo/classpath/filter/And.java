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
 * Implementation of the logic AND between filters, return true if all
 * the given filters return true.
 *
 * @version $Id$
 */
final class And extends AbstractMultipleArgumentFilter implements Filter {

    /**
     * Creates a new and filer.
     *
     * @param filters the filters array have to be applied.
     */
    public And(Filter...filters) {
        super(filters);
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        for (Filter filter : this.getFilters()) {
            if (!filter.matches(clazz)) {
                return false;
            }
        }
        return true;
    }

}
