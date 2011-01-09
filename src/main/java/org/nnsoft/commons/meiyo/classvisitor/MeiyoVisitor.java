package org.nnsoft.commons.meiyo.classvisitor;

import java.util.Arrays;
import java.util.Collection;

public final class MeiyoVisitor {

    private MeiyoVisitor() {
        // do nothing
    }

    public static ClassVisitor createVisitor(VisitorConfiguration...configurations) {
        if (configurations == null || configurations.length == 0) {
            throw new IllegalArgumentException("At least one VisitorConfiguration has to be specified");
        }
        return createVisitor(Arrays.asList(configurations));
    }

    public static ClassVisitor createVisitor(Collection<VisitorConfiguration> configurations) {
        if (configurations == null || configurations.isEmpty()) {
            throw new IllegalArgumentException("Parameter 'configurations' must not be null or empty");
        }

        AnnotationHandlerBinderImpl binderImpl = new AnnotationHandlerBinderImpl();
        for (VisitorConfiguration module : configurations) {
            module.configure(binderImpl);
        }

        return new ClassVisitorImpl(binderImpl);
    }

}
