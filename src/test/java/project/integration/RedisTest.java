package project.integration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisTest {

    @Test
    void shouldStoreAndReadValueFromRedis() {
        try (GenericContainer<?> redis = new GenericContainer<>("redis:7.2")
                .withExposedPorts(6379)) {

            redis.start();

            String address = redis.getHost();
            Integer port = redis.getFirstMappedPort();

            try (Jedis jedis = new Jedis(address, port)) {
                jedis.set("test-key", "hello");
                String value = jedis.get("test-key");

                assertEquals("hello", value);
            }
        }
    }
}
