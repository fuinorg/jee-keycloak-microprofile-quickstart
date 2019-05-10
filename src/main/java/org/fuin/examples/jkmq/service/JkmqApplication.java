package org.fuin.examples.jkmq.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class JkmqApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(PersonResource.class);
        classes.add(AdminResource.class);
        classes.add(EJBExceptionMapper.class);
        return classes;

    }

}
