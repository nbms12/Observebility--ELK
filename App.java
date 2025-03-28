package com.example;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        logger.info("Starting the application...");

        // Create an HTTP server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            // Get the client's IP address and port
            String clientAddress = exchange.getRemoteAddress().getAddress().getHostAddress();
            int clientPort = exchange.getRemoteAddress().getPort();

            // Get the request URI
            String requestURI = exchange.getRequestURI().toString();

            // Get the Host header (e.g., "65.2.182.24:8080")
            String hostHeader = exchange.getRequestHeaders().getFirst("Host");

            // Construct the full URL
            String fullUrl = "http://" + hostHeader + requestURI;

            // Ignore requests for /favicon.ico
            if (requestURI.equals("/favicon.ico")) {
                exchange.sendResponseHeaders(404, -1); // Return 404 for favicon requests
                return; // Do not log or process further
            }

            // Log the request with client details and full URL
            String requestMethod = exchange.getRequestMethod();
            logger.info("Received {} request for URL: {} from {}:{}", requestMethod, fullUrl, clientAddress, clientPort);

            // Write "Hello, World!" to the response
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

            // Log the response
            logger.info("Sent response: {}", response);
        });

        // Start the server
        server.start();
        logger.info("Server started on port 8080");

        // Keep the application running
        logger.info("Application is running. Waiting for incoming requests...");
    }
}
