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

import static org.nnsoft.commons.meiyo.classpath.filter.Filters.*;

import java.util.List;

import org.nnsoft.commons.meiyo.classpath.ClassPath;
import org.nnsoft.commons.meiyo.classpath.ClassPathEntry;
import org.nnsoft.commons.meiyo.classpath.ClassPathHandler;
import org.nnsoft.commons.meiyo.classpath.filter.AbstractMultipleArgumentFilter;
import org.nnsoft.commons.meiyo.classpath.filter.Filter;
import org.nnsoft.commons.meiyo.classpath.filter.Filters;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * 
 *
 * @version $Id$
 */
@Test
public final class FiltersTestCase {

    public void matchesAnnotatedWithType() {
        assert annotatedWithType(Test.class).matches(this.getClass());
        assert !annotatedWithType(Parameters.class).matches(this.getClass());
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void noRuntimeRetention() {
        annotatedWithType(NoRuntimeRetention.class);
    }

    public void matchesInPackage() {
        Filter inMeiyoPackage = inPackage("org.nnsoft.commons.meiyo.classpath");
        assert inMeiyoPackage.matches(ClassPath.class);
        assert !inMeiyoPackage.matches(List.class);
    }

    public void matchesInSubPackage() {
        Filter inMeiyoPackage = inSubpackage("org.nnsoft.commons.meiyo.classpath");
        assert inMeiyoPackage.matches(Filters.class);
        assert !inMeiyoPackage.matches(List.class);
    }

    public void matchesInterface() {
        Filter isInterface = isInterface();
        assert !isInterface.matches(DummyAnnotation.class);
        assert isInterface.matches(ClassPath.class);
    }

    public void matchesAbstract() {
        Filter isAbstract = isAbstract();
        assert isAbstract.matches(AbstractMultipleArgumentFilter.class);
        assert !isAbstract.matches(Filter.class);
    }

    public void matchesContainsMethodWithNoArguments() {
        Filter containsMethod = containsMethod("getClazz");
        assert containsMethod.matches(ClassPathEntry.class);
        assert !containsMethod.matches(Filter.class);
    }

    public void matchesContainsMethodWithArguments() {
        Filter containsMethod = containsMethod("doHandle", ClassPathEntry.class);
        assert containsMethod.matches(ClassPathHandler.class);
        assert !containsMethod.matches(Filter.class);
    }

}
