package org.fuin.examples.jkmq.service;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
@WebFilter(filterName = "StaticCorsFilter", urlPatterns = { "/api.yaml", "/api/*" })
public class StaticCorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Not used
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        // TODO Investigate why there is a duplicate "Access-Control-Allow-Origin"
        // -----------------------------------------------------------------------
        // Failed to load http://jkmq-service:8080/jkmq-service/api/admin/hello:
        // The 'Access-Control-Allow-Origin' header contains multiple values
        // 'http://jkmq-swagger-ui:8080, *', but only one is allowed.
        // Origin 'http://jkmq-swagger-ui:8080' is therefore not allowed access.
        // Have the server send the header with a valid value, or, if an opaque
        // response serves your needs, set the request's mode to 'no-cors' to
        // fetch the resource with CORS disabled.
        // -----------------------------------------------------------------------
        // Checking for "authorization" is just a quick and dirty workaround...
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getHeader("authorization") == null) {

            final HttpServletResponse httpResponse = (HttpServletResponse) response;

            addIfNotExists(httpResponse, "Access-Control-Allow-Origin", "*");
            addIfNotExists(httpResponse, "Access-Control-Allow-Headers", "*");
            addIfNotExists(httpResponse, "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            addIfNotExists(httpResponse, "Access-Control-Allow-Credentials", "true");
            addIfNotExists(httpResponse, "Access-Control-Max-Age", "-1");

        }

        chain.doFilter(request, response);

    }

    private static void addIfNotExists(final HttpServletResponse httpResponse, final String key, final String value) {
        if (!httpResponse.containsHeader(key)) {
            httpResponse.addHeader(key, value);
        }
    }

    @Override
    public void destroy() {
        // Not used
    }
}
