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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.pasaperez.elixir.entities.Base;
import tech.pasaperez.elixir.exceptions.AlreadyExistException;
import tech.pasaperez.elixir.exceptions.NotFoundException;
import tech.pasaperez.elixir.exceptions.OperationNotSupportedException;

import java.io.Serializable;

/**
 * Generic base controller interface that defines common REST API endpoints for entities.
 * This interface provides a standard contract for controller layer implementations
 * that handle basic CRUD operations through HTTP endpoints with a consistent response structure.
 *
 * <p>This interface is designed to work with Spring Boot and provides RESTful endpoints
 * for entity management. All methods return ResponseEntity for proper HTTP response handling
 * and include appropriate exception declarations for error scenarios.</p>
 *
 * <p>The interface includes Swagger/OpenAPI annotations for automatic API documentation
 * generation when used with springdoc-openapi. Implementing classes should add specific
 * request mapping annotations and customize the Swagger documentation as needed.</p>
 *
 * @param <E> the entity type that extends Base
 * @param <ID> the type of the entity's identifier must be Serializable
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Base
 * @see org.springframework.http.ResponseEntity
 */
@SuppressWarnings("unused")
@Tag(name = "Base Controller", description = "Generic CRUD operations for entities")
public interface IBaseController<E extends Base, ID extends Serializable> {

    /**
     * Creates a new entity through HTTP POST request.
     * This endpoint accepts an entity in the request body, validates it doesn't already exist,
     * and persists it to the system. Returns the created entity with its assigned identifier.
     *
     * @param entity the entity to be created, provided in the request body, must not be null
     * @return ResponseEntity containing the created entity and HTTP status 201 (Created)
     * @throws AlreadyExistException if an entity with the same unique constraints already exists
     * @throws OperationNotSupportedException if the create operation is not supported for this entity type
     * @throws IllegalArgumentException if the entity parameter is null or invalid
     */
    @Operation(
            summary = "Create a new entity",
            description = "Creates a new entity in the system. The entity must not already exist based on unique constraints."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entity created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid entity data provided",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Entity already exists",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "501",
                    description = "Create operation not supported for this entity",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<?> create(@RequestBody E entity) throws AlreadyExistException, OperationNotSupportedException;

    /**
     * Retrieves a specific entity by its unique identifier through HTTP GET request.
     * This endpoint performs a lookup operation using the provided ID from the URL path.
     *
     * @param id the unique identifier of the entity to retrieve, extracted from URL path
     * @return ResponseEntity containing the found entity and HTTP status 200 (OK)
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null or invalid
     */
    @Operation(
            summary = "Get entity by ID",
            description = "Retrieves a specific entity using its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID format provided",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entity not found with the provided ID",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<?> getById(@PathVariable ID id) throws NotFoundException;

    /**
     * Retrieves all entities of the specified type through HTTP GET request.
     * This endpoint returns a complete list of all persisting entities in the system.
     *
     * @return ResponseEntity containing a list of all entities and HTTP status 200 (OK)
     */
    @Operation(
            summary = "Get all entities",
            description = "Retrieves a list of all entities of this type from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of entities retrieved successfully (may be empty)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error occurred while retrieving entities",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<?> getAll();

    /**
     * Updates an existing entity with new data through HTTP PUT request.
     * This endpoint finds the entity by ID and updates it with the provided entity data
     * from the request body, preserving the original identifier.
     *
     * @param id the unique identifier of the entity to update, extracted from URL path
     * @param entity the entity containing updated data, provided in the request body
     * @return ResponseEntity containing the updated entity and HTTP status 200 (OK)
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws OperationNotSupportedException if the update operation is not supported for this entity type
     * @throws IllegalArgumentException if either id or entity parameters are null or invalid
     */
    @Operation(
            summary = "Update an existing entity",
            description = "Updates an existing entity with new data. The entity must exist in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid entity data or ID format provided",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entity not found with the provided ID",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "501",
                    description = "Update operation not supported for this entity",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity) throws NotFoundException, OperationNotSupportedException;

    /**
     * Deletes an entity from the system by its unique identifier through HTTP DELETE request.
     * This endpoint performs a deletion operation and returns information about the deleted entity.
     *
     * @param id the unique identifier of the entity to delete, extracted from URL path
     * @return ResponseEntity containing the deleted entity information and HTTP status 200 (OK)
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws OperationNotSupportedException if the delete operation is not supported for this entity type
     * @throws IllegalArgumentException if the id parameter is null or invalid
     */
    @Operation(
            summary = "Delete an entity",
            description = "Deletes an entity from the system using its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity deleted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID format provided",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entity not found with the provided ID",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "501",
                    description = "Delete operation not supported for this entity",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<?> delete(@PathVariable ID id) throws NotFoundException, OperationNotSupportedException;
}