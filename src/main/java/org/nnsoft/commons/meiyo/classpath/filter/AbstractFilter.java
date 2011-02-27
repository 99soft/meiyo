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
package org.nnsoft.commons.meiyo.classpath.filter;

abstract class AbstractFilter implements Filter {

    /**
     * {@inheritDoc}
     */
    public Filter and(Filter other) {
        return new And(this, other);
    }

    /**
     * {@inheritDoc}
     */
    public Filter nand(Filter other) {
        return new Not(and(other));
    }

    /**
     * {@inheritDoc}
     */
    public Filter or(Filter other) {
        return new Or(this, other);
    }

    /**
     * {@inheritDoc}
     */
    public Filter nor(Filter other) {
        return new Not(or(other));
    }

    /**
     * {@inheritDoc}
     */
    public Filter xor(Filter other) {
        return new Xor(this, other);
    }

    public Filter xnor(Filter other) {
        return new Not(xor(other));
    }

}
