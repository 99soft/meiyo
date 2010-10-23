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
package com.googlecode.meiyo.filter;

/**
 * Abstract implementation of a composite filter.
 *
 * @version $Id$
 */
abstract class AbstractMultipleArgumentFilter {

    /**
     * The filters array have to be applied.
     */
    private final Filter[] filters;

    /**
     * Creates a new composite filter.
     *
     * @param filters the filters array have to be applied.
     */
    public AbstractMultipleArgumentFilter(Filter...filters) {
        this.filters = filters;
    }

    /**
     * Return the filters array have to be applied.
     *
     * @return the filters array have to be applied.
     */
    protected final Filter[] getFilters() {
        return this.filters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName().toLowerCase());
        builder.append('(');
        for (int i = 0; i < this.filters.length; i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(this.filters[i]);
        }
        builder.append(')');
        return builder.toString();
    }

}
