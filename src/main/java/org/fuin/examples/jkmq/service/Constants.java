package org.fuin.examples.jkmq.service;

/**
 * Constants for the package.
 */
public final class Constants {

    /** Short name for the application. */
    public static final String APP_SHORT_NAME = "jkmq";
    
    /** Admininistrator role. */
    public static final String ROLE_ADMIN = "admin";

    /** User role. */
    public static final String ROLE_USER = "user";

    private Constants() {
        super();
        throw new UnsupportedOperationException("Creating an instance is not allowed");
    }

}
