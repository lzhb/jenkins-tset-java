package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for HelloWorld class
 */
public class HelloWorldTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testMainMethod() {
        // 测试main方法的输出
        String[] args = {};
        HelloWorld.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("Hello, World!"), "Output should contain 'Hello, World!'");
        assertTrue(output.contains("This is a simple Java Hello World project."), 
                  "Output should contain project description");
    }
    
    @Test
    public void testMainMethodNotNull() {
        // 测试main方法不会抛出异常
        assertDoesNotThrow(() -> {
            HelloWorld.main(new String[]{});
        });
    }
}