package org.fuin.examples.jkmq.service;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.eclipse.yasson.FieldAccessStrategy;

@Provider
public class JsonbResolver implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> type) {
        final JsonbConfig config = new JsonbConfig()
                .withPropertyVisibilityStrategy(new FieldAccessStrategy());
        final Jsonb jsonb = JsonbBuilder.create(config);
        return jsonb;
    }

}
