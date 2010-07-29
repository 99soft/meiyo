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
 * 
 *
 * @version $Id$
 */
final class InPackage implements Filter {

    private final String targetPackage;

    public InPackage(String targetPackage) {
        if (targetPackage == null) {
            throw new IllegalArgumentException("Parameter 'targetPackage' must be not null");
        }
        this.targetPackage = targetPackage;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        return this.targetPackage.equals(clazz.getPackage().getName());
    }

    @Override
    public String toString() {
        return "inPackage("
                + this.targetPackage
                + ")";
    }

}
