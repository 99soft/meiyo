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
 * Filter that verifies the found class is exactly in the given package.
 *
 * @version $Id$
 */
final class InPackage implements Filter {

    /**
     * The package where classes are looked for.
     */
    private final String targetPackage;

    /**
     * Creates a new package based filter.
     *
     * @param targetPackage the package where classes are looked for.
     */
    public InPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        return this.targetPackage.equals(clazz.getPackage().getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "inPackage("
                + this.targetPackage
                + ")";
    }

}
