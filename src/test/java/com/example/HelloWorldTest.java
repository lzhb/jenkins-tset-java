package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HelloWorld HTTP Server
 */
public class HelloWorldTest {
    
    @Test
    public void testHandlerClass() {
        // 测试Handler类存在且可以实例化
        assertDoesNotThrow(() -> {
            HelloWorld.HelloWorldHandler handler = new HelloWorld.HelloWorldHandler();
            assertNotNull(handler, "Handler should be instantiable");
        });
    }
    
    @Test
    public void testMainMethodExists() {
        // 测试main方法存在且不会立即抛出异常
        assertDoesNotThrow(() -> {
            // 只验证方法存在，不实际运行服务器
            HelloWorld.class.getMethod("main", String[].class);
        });
    }
    
    @Test
    public void testConstants() {
        // 测试常量值（通过反射）
        try {
            java.lang.reflect.Field portField = HelloWorld.class.getDeclaredField("PORT");
            portField.setAccessible(true);
            int port = (int) portField.get(null);
            assertEquals(9000, port, "Port should be 9000");
            
            java.lang.reflect.Field messageField = HelloWorld.class.getDeclaredField("RESPONSE_MESSAGE");
            messageField.setAccessible(true);
            String message = (String) messageField.get(null);
            assertEquals("Hello World", message, "Response message should be 'Hello World'");
            
        } catch (Exception e) {
            fail("Failed to access constants: " + e.getMessage());
        }
    }
}