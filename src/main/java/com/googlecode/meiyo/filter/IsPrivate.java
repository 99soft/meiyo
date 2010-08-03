package com.googlecode.meiyo.filter;

import java.lang.reflect.Modifier;

final class IsPrivate implements Filter {

    /**
     * {@inheritDoc}
     */
    public boolean matches(Class<?> clazz) {
        return Modifier.isInterface(clazz.getModifiers());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "isInterface()";
    }

}
