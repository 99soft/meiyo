package org.nnsoft.commons.meiyo.classpath;

import java.util.Collection;

public interface ModulesBuilder {

    ClassLoaderBuilder withConfiguration(Module...modules);

    ClassLoaderBuilder withConfiguration(Collection<Module> modules);

}
