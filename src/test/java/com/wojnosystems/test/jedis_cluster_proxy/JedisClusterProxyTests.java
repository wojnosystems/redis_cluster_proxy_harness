package com.wojnosystems.test.jedis_cluster_proxy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JedisClusterProxyTests {
    @Test
    void createTest() {
        JedisCluster cluster = new JedisCluster(
                new HostAndPort(REDIS_HOST, REDIS_PORT), AN_INT_TIME, AN_INT_TIME, AN_INT_TIME, poolConfig);
        String value = cluster.get("ping");
        assertNull( value );
    }

    @Test
    void setAndGetTest() {
        JedisCluster cluster = new JedisCluster(
                new HostAndPort(REDIS_HOST, REDIS_PORT), AN_INT_TIME, AN_INT_TIME, AN_INT_TIME, poolConfig);

        final String expect = "i am set";
        final String key = "setAndGet" + Instant.now().getEpochSecond();
        cluster.set(key, expect);
        final String actual = cluster.get(key);
        assertEquals( expect, actual );
    }

    @BeforeAll
    static void setUpSuite() {
        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(A_LONG_TIME);
    }

    private static JedisPoolConfig poolConfig;

    private static final long A_LONG_TIME = 1000000;
    private static final int AN_INT_TIME = 100000;
    private static final int REDIS_PORT = 8000;
    private static final String REDIS_HOST = "127.0.0.1";

}
