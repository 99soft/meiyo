package org.nnsoft.commons.meiyo.classpath;

import java.util.Collection;
import java.util.LinkedList;

import org.nnsoft.commons.meiyo.classpath.filter.Filter;

final class BinderImpl implements Binder {

    private final Collection<ClassPathHandler> handlers = new LinkedList<ClassPathHandler>();

    @Override
    public LinkedHandlerBuilder ifMatches(final Filter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("Filter must not be null");
        }

        return new LinkedHandlerBuilder() {

            @Override
            public void handleWith(final ClassPathEntryHandler...entryHandlers) {
                if (entryHandlers == null || entryHandlers.length == 0) {
                    throw new IllegalArgumentException("At least one ClassPathEntryHandler must be specified");
                }

                BinderImpl.this.handlers.add(new ClassPathHandler(filter, entryHandlers));
            }

        };
    }

    public Collection<ClassPathHandler> getHandlers() {
        return this.handlers;
    }

}
