package org.nnsoft.commons.meiyo.classvisitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class AbstractVisitorConfiguration implements VisitorConfiguration {

    private AnnotationHandlerBinder wrapped;

    /**
     * {@inheritDoc}
     */
    public final void configure(final AnnotationHandlerBinder binder) {
        this.wrapped = binder;

        try {
            this.configure();
        } finally {
            this.wrapped = null;
        }
    }

    public abstract void configure();

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Class> handleType() {
        return this.wrapped.handleType();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Constructor> handleConstructor() {
        return this.wrapped.handleConstructor();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Field> handleField() {
        return this.wrapped.handleField();
    }

    /**
     * {@inheritDoc}
     */
    public AnnotatedHandlerBuilder<Method> handleMethod() {
        return this.wrapped.handleMethod();
    }

}
