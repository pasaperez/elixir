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

package tech.pasaperez.elixir.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.dtos.ErrorDTO;
import tech.pasaperez.elixir.entities.Status;
import tech.pasaperez.elixir.exceptions.*;

import java.util.Collections;

/**
 * Global exception handler for REST API endpoints in the Elixir application.
 * This class provides centralized exception handling for all REST controllers,
 * converting various exceptions into appropriate HTTP responses with proper
 * status codes and error messages.
 *
 * <p>This handler uses Spring's @RestControllerAdvice annotation to intercept
 * exceptions thrown by any REST controller in the application and transform
 * them into standardized API responses.</p>
 *
 * <p>The handler supports different types of custom exceptions and maps them
 * to appropriate HTTP status codes:</p>
 * <ul>
 * <li>DefaultException → HTTP 409 CONFLICT</li>
 * <li>NotFoundException → HTTP 404 NOT_FOUND</li>
 * <li>AlreadyExistException → HTTP 403 FORBIDDEN</li>
 * <li>OperationNotSupportedException → HTTP 405 METHOD_NOT_ALLOWED</li>
 * </ul>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see RestControllerAdvice
 * @see ExceptionHandler
 * @see APIResponse
 * @see ResponseException
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    /**
     * Handles DefaultException by returning a simple string response with conflict status.
     * This method provides a basic error handling mechanism for default runtime exceptions.
     *
     * <p>Unlike other exception handlers in this class, this method returns a plain
     * string response rather than a structured APIResponse object.</p>
     *
     * @param ex the RuntimeException that was thrown (specifically DefaultException)
     * @return ResponseEntity containing an error message string with HTTP 409 CONFLICT status
     * @see DefaultException
     */
    @ExceptionHandler(value= { DefaultException.class })
    protected ResponseEntity<String> handleDefaultConflict(RuntimeException ex) {
        return new ResponseEntity<>("Error handler: \n" + ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT);
    }

    /**
     * Handles NotFoundException by returning a structured API response with NOT_FOUND status.
     * This method is triggered when a requested resource cannot be found in the system.
     *
     * <p>The response includes a standardized APIResponse object with error status
     * and detailed error information wrapped in an ErrorDTO.</p>
     *
     * @param ex the ResponseException that was thrown (specifically NotFoundException)
     * @return ResponseEntity containing APIResponse with error details and HTTP 404 NOT_FOUND status
     * @see NotFoundException
     * @see APIResponse
     * @see ErrorDTO
     */
    @ExceptionHandler(value= { NotFoundException.class })
    protected ResponseEntity<APIResponse<?>> handleNotFoundConflict(ResponseException ex) {
        return new ResponseEntity<>(APIResponse.builder()
                .status(Status.ERROR)
                .error(Collections.singletonList(new ErrorDTO("Detail", ex.getMessage())))
                .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AlreadyExistException by returning a structured API response with FORBIDDEN status.
     * This method is triggered when attempting to create a resource that already exists
     * in the system.
     *
     * <p>The response indicates that the requested operation is forbidden because
     * the resource already exists, using HTTP 403 FORBIDDEN status.</p>
     *
     * @param ex the ResponseException that was thrown (specifically AlreadyExistException)
     * @return ResponseEntity containing APIResponse with error details and HTTP 403 FORBIDDEN status
     * @see AlreadyExistException
     * @see APIResponse
     * @see ErrorDTO
     */
    @ExceptionHandler(value= { AlreadyExistException.class })
    protected ResponseEntity<APIResponse<?>> handleAlreadyExistConflict(ResponseException ex) {
        return new ResponseEntity<>(APIResponse.builder()
                .status(Status.ERROR)
                .error(Collections.singletonList(new ErrorDTO("Detail", ex.getMessage())))
                .build(),
                new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles OperationNotSupportedException by returning a structured API response
     * with METHOD_NOT_ALLOWED status.
     * This method is triggered when an unsupported operation is attempted on a resource.
     *
     * <p>The response indicates that the requested HTTP method or operation
     * is not supported for the given resource, using HTTP 405 METHOD_NOT_ALLOWED status.</p>
     *
     * @param ex the ResponseException that was thrown (specifically OperationNotSupportedException)
     * @return ResponseEntity containing APIResponse with error details and HTTP 405 METHOD_NOT_ALLOWED status
     * @see OperationNotSupportedException
     * @see APIResponse
     * @see ErrorDTO
     */
    @ExceptionHandler(value= { OperationNotSupportedException.class })
    protected ResponseEntity<APIResponse<?>> handleOperationNotSupportedConflict(ResponseException ex) {
        return new ResponseEntity<>(APIResponse.builder()
                .status(Status.ERROR)
                .error(Collections.singletonList(new ErrorDTO("Detail", ex.getMessage())))
                .build(),
                new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}