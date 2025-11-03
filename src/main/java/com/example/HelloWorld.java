package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

/**
 * Simple Hello World HTTP Server
 * Listens on port 9000 and returns "Hello World" for root path
 */
public class HelloWorld {
    private static final int PORT = 9000;
    private static final String RESPONSE_MESSAGE = "Hello World";
    
    public static void main(String[] args) {
        try {
            // Create HTTP server
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            
            // Create context for root path
            server.createContext("/", new HelloWorldHandler());
            
            // Set executor (thread pool)
            server.setExecutor(Executors.newFixedThreadPool(10));
            
            // Start the server
            server.start();
            
            System.out.println("🚀 Hello World HTTP Server started!");
            System.out.println("📡 Listening on http://localhost:" + PORT);
            System.out.println("🌐 Try: curl http://localhost:" + PORT);
            System.out.println("⏹️  Press Ctrl+C to stop the server");
            
            // Add shutdown hook for graceful shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n🛑 Shutting down server...");
                server.stop(2);
                System.out.println("✅ Server stopped gracefully");
            }));
            
        } catch (IOException e) {
            System.err.println("❌ Failed to start server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * HTTP request handler for Hello World responses
     */
    static class HelloWorldHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            System.out.println("📥 " + method + " " + path + " from " + 
                             exchange.getRemoteAddress().getAddress().getHostAddress());
            
            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            exchange.getResponseHeaders().set("Server", "HelloWorld-Java-Server/1.0");
            
            // Prepare response
            byte[] response = RESPONSE_MESSAGE.getBytes(StandardCharsets.UTF_8);
            
            // Send response
            exchange.sendResponseHeaders(200, response.length);
            
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        }
    }
}