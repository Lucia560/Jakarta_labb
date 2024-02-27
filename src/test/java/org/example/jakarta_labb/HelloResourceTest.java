package org.example.jakarta_labb;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import org.example.jakarta_labb.resource.HelloResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(HelloResource.class);
    }

    @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new GrizzlyWebTestContainerFactory();
    }

    @Test
    @DisplayName("Should return 200 when reaching endpoint")
    void shouldReturn200WhenReachingEndpoint() {
        Response response = target("/hello-world").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("Hello, World!", response.readEntity(String.class));
    }
}
