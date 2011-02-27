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

import static org.nnsoft.commons.meiyo.classpath.filter.Filters.annotatedWithType;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.containsMethod;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.inPackage;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.inSubpackage;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.isAbstract;
import static org.nnsoft.commons.meiyo.classpath.filter.Filters.isInterface;

import java.util.List;

import org.nnsoft.commons.meiyo.classpath.Matcher;
import org.nnsoft.commons.meiyo.classpath.ClassLoaderBuilder;
import org.nnsoft.commons.meiyo.classpath.MeiyoScanner;
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
        assert inMeiyoPackage.matches(MeiyoScanner.class);
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
        assert isInterface.matches(Matcher.class);
    }

    public void matchesAbstract() {
        Filter isAbstract = isAbstract();
        assert isAbstract.matches(AbstractFilter.class);
        assert !isAbstract.matches(Filter.class);
    }

    public void matchesContainsMethodWithNoArguments() {
        Filter containsMethod = containsMethod("usingDefaultClassLoader");
        assert containsMethod.matches(ClassLoaderBuilder.class);
        assert !containsMethod.matches(Filter.class);
    }

    public void matchesContainsMethodWithArguments() {
        Filter containsMethod = containsMethod("matches", Class.class);
        assert containsMethod.matches(Filter.class);
        assert !containsMethod.matches(MeiyoScanner.class);
    }

}
