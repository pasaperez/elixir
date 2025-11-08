/*
 * Elixir Library
 * Copyright (C) 2025  Angel Santiago Perez
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <https://www.gnu.org/licenses/>.
 */

package tech.pasaperez.elixir.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pasaperez.elixir.exceptions.DefaultException;

/**
 * REST controller that provides system status monitoring endpoints for the Elixir application.
 * This controller offers basic health check functionality and error testing capabilities
 * to verify that the application is running correctly.
 *
 * <p>The controller exposes endpoints under the "/status" base path and provides
 * two main functionalities:
 * <ul>
 *   <li>System health check - Returns a simple status message</li>
 *   <li>Error handling test - Triggers an exception to test error handling</li>
 * </ul>
 * </p>
 *
 * <p>This controller is particularly useful for:
 * <ul>
 *   <li>Load balancer health checks</li>
 *   <li>Application monitoring systems</li>
 *   <li>Testing exception handling mechanisms</li>
 *   <li>Basic application availability verification</li>
 * </ul>
 * </p>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see RestController
 * @see RequestMapping
 * @see DefaultException
 */
@RestController
@RequestMapping("/status")
public class SystemStatusController {

    /**
     * Provides a basic health check endpoint that returns the current system status.
     * This endpoint can be used by monitoring tools, load balancers, or other systems
     * to verify that the application is running and responding to requests.
     *
     * <p>The method returns a simple success message with an HTTP 202 (ACCEPTED) status
     * code to indicate that the system is operational. This endpoint requires no
     * authentication and can be called frequently without impacting system performance.</p>
     *
     * <p><strong>Example usage:</strong></p>
     * <pre>
     * GET /status
     * Response: "OK" (HTTP 202)
     * </pre>
     *
     * @return a {@link ResponseEntity} containing a status message and HTTP 202 status code
     * @see ResponseEntity
     * @see HttpStatus#ACCEPTED
     */
    @GetMapping ("")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    /**
     * Test endpoint that deliberately throws an exception to verify error handling mechanisms.
     * This endpoint is designed for testing purposes to ensure that the application's
     * exception handling and error reporting systems are working correctly.
     *
     * <p>When called, this method will always throw a {@link DefaultException} with a
     * predefined message. This allows developers and testing systems to verify that:
     * <ul>
     *   <li>Exception handling is properly configured</li>
     *   <li>Error responses are formatted correctly</li>
     *   <li>Logging systems capture exceptions appropriately</li>
     *   <li>Global exception handlers are functioning</li>
     * </ul>
     * </p>
     *
     * <p><strong>Warning:</strong> This endpoint should be used only in development
     * and testing environments. Consider restricting access to production environments.</p>
     *
     * <p><strong>Example usage:</strong></p>
     * <pre>
     * GET /status/error
     * Throws: DefaultException("Error Default Funcionando.")
     * </pre>
     *
     * @throws DefaultException always thrown with a test message to verify error handling
     * @see DefaultException
     */
    @GetMapping ("/error")
    public void error() {
        throw new DefaultException("Default Error");
    }
}