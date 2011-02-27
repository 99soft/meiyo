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

import java.util.regex.Pattern;

/**
 * A filter that verifies the class found name matches against a given pattern.
 */
final class ClassNameMatchesFilter extends AbstractFilter {

    /**
     * The class name pattern has to be matched.
     */
    private final Pattern pattern;

    /**
     * Creates a new class name matcher.
     *
     * @param regex the class name regexp pattern has to be matched.
     */
    public ClassNameMatchesFilter(String regex) {
        this(Pattern.compile(regex));
    }

    /**
     * Creates a new class name matcher.
     *
     * @param pattern the class name pattern has to be matched.
     */
    public ClassNameMatchesFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        return this.pattern.matcher(clazz.getSimpleName()).matches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("classNameMatches(%s)", this.pattern.toString());
    }

}
