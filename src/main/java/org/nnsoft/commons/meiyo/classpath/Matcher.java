package org.nnsoft.commons.meiyo.classpath;

import org.nnsoft.commons.meiyo.classpath.filter.Filter;

public interface Binder {

    LinkedHandlerBuilder ifMatches(Filter filter);

}
