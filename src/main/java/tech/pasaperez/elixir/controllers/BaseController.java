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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.entities.Base;
import tech.pasaperez.elixir.exceptions.AlreadyExistException;
import tech.pasaperez.elixir.exceptions.NotFoundException;
import tech.pasaperez.elixir.exceptions.OperationNotSupportedException;
import tech.pasaperez.elixir.services.BaseService;

import java.util.List;

/**
 * Abstract base controller implementation that provides common REST API endpoints for entities.
 * This class implements the IBaseController interface and provides default implementations
 * for standard CRUD operations through HTTP endpoints. It serves as a foundation for specific
 * controller implementations by handling common functionality and REST mappings.
 *
 * <p>This controller uses Spring Boot annotations for REST endpoint mapping and includes
 * automatic validation through {@code @Validated} annotations. All operations return
 * properly typed ResponseEntity objects with appropriate HTTP status codes and APIResponse wrappers.</p>
 *
 * <p>The controller is designed to work with Long identifiers and delegates all business logic
 * to the injected service layer. It provides consistent error handling and response formatting
 * across all CRUD operations.</p>
 *
 * @param <E> the entity type that extends Base
 * @param <S> the service type that extends BaseService for the entity
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see IBaseController
 * @see BaseService
 * @see APIResponse
 * @see Base
 */
public abstract class BaseController <E extends Base, S extends BaseService<E, Long>> implements IBaseController<E, Long> {

    /**
     * The service instance used for business logic operations.
     * This field provides access to the service layer for entity management and business rules.
     */
    protected final S servicio;

    /**
     * Constructs a new BaseController with the specified service.
     * This constructor initializes the controller with a service instance that will be used
     * for all business logic operations throughout the controller lifecycle.
     *
     * @param servicio the service instance for business logic operations must not be null
     * @throws IllegalArgumentException if servicio is null
     */
    public BaseController(S servicio) {
        this.servicio = servicio;
    }

    /**
     * Creates a new entity through HTTP POST request to the root endpoint.
     * This endpoint accepts a validated entity in the request body, delegates creation
     * to the service layer, and returns the created entity with HTTP 201 status.
     *
     * @param entity the validated entity to be created, provided in the request body, must not be null
     * @return ResponseEntity containing APIResponse with the created entity and HTTP status 201 (Created)
     * @throws AlreadyExistException if an entity with the same unique constraints already exists
     * @throws OperationNotSupportedException if the create operation is not supported for this entity type
     * @throws IllegalArgumentException if the entity parameter is null or fails validation
     */
    @PostMapping("/")
    @Override
    public ResponseEntity<APIResponse<E>> create(@Validated @RequestBody E entity) throws AlreadyExistException, OperationNotSupportedException {
        return new ResponseEntity<>(servicio.create(entity), HttpStatus.CREATED);
    }

    /**
     * Retrieves a specific entity by its unique identifier through HTTP GET request.
     * This endpoint extracts the ID from the URL path, delegates the lookup to the service layer,
     * and returns the found entity with HTTP 200 status.
     *
     * @param id the unique identifier of the entity to retrieve, extracted from URL path
     * @return ResponseEntity containing APIResponse with the found entity and HTTP status 200 (OK)
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null or invalid
     */
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<APIResponse<E>> getById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(servicio.findById(id), HttpStatus.OK);
    }

    /**
     * Retrieves all entities of the specified type through HTTP GET request to the root endpoint.
     * This endpoint delegates to the service layer to fetch all entities and returns them
     * in a list wrapped in an APIResponse with HTTP 200 status.
     *
     * @return ResponseEntity containing APIResponse with a list of all entities and HTTP status 200 (OK)
     */
    @GetMapping("/")
    @Override
    public ResponseEntity<APIResponse<List<E>>> getAll() {
        return new ResponseEntity<>(servicio.findAll(), HttpStatus.OK);
    }

    /**
     * Updates an existing entity with new data through HTTP PUT request.
     * This endpoint extracts the ID from the URL path, accepts a validated entity in the request body,
     * delegates the update operation to the service layer, and returns the updated entity with HTTP 200 status.
     *
     * @param id the unique identifier of the entity to update, extracted from URL path
     * @param entity the validated entity containing updated data, provided in the request body
     * @return ResponseEntity containing APIResponse with the updated entity and HTTP status 200 (OK)
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws OperationNotSupportedException if the update operation is not supported for this entity type
     * @throws IllegalArgumentException if either id or entity parameters are null or invalid
     */
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<APIResponse<E>> update(@PathVariable Long id, @Validated @RequestBody E entity) throws NotFoundException, OperationNotSupportedException {
        return new ResponseEntity<>(servicio.update(id, entity), HttpStatus.OK);
    }

    /**
     * Deletes an entity from the system by its unique identifier through HTTP DELETE request.
     * This endpoint extracts the ID from the URL path, delegates the deletion to the service layer,
     * and returns either HTTP 204 (No Content) for successful deletion or HTTP 403 (Forbidden)
     * if the deletion operation fails or is not allowed.
     *
     * <p>The response body is null for successful deletions as per REST conventions for DELETE operations.
     * The HTTP status code indicates the result of the operation.</p>
     *
     * @param id the unique identifier of the entity to delete, extracted from URL path
     * @return ResponseEntity with null body and HTTP status 204 (No Content) for successful deletion,
     *         or HTTP status 403 (Forbidden) if deletion fails
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws OperationNotSupportedException if the delete operation is not supported for this entity type
     * @throws IllegalArgumentException if the id parameter is null or invalid
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<APIResponse<E>> delete(@PathVariable Long id) throws NotFoundException, OperationNotSupportedException {
        return servicio.delete(id)!=null ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}