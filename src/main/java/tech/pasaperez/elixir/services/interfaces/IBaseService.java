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

package tech.pasaperez.elixir.services.interfaces;

import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.entities.Base;
import tech.pasaperez.elixir.exceptions.AlreadyExistException;
import tech.pasaperez.elixir.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;

/**
 * Generic base service interface that defines common CRUD operations for entities.
 * This interface provides a standard contract for service layer implementations
 * that handle basic entity operations with a consistent API response structure.
 *
 * @param <E> the entity type that extends Base
 * @param <ID> the type of the entity's identifier must be Serializable
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Base
 * @see APIResponse
 */
public interface IBaseService<E extends Base, ID extends Serializable> {

    /**
     * Creates a new entity in the system.
     * This method persists the provided entity and returns an API response
     * containing the created entity with its assigned identifier.
     *
     * @param entity the entity to be created must not be null
     * @return APIResponse containing the created entity with generated ID
     * @throws AlreadyExistException if an entity with the same unique constraints already exists
     * @throws IllegalArgumentException if the entity parameter is null
     */
    APIResponse<E> create(E entity) throws AlreadyExistException;

    /**
     * Checks whether an entity with the same unique characteristics already exists in the system.
     * This method is typically used for validation before creating or updating entities.
     *
     * @param entity the entity to check for existence must not be null
     * @return true if an entity with matching unique constraints exists, false otherwise
     * @throws IllegalArgumentException if the entity parameter is null
     */
    boolean checkIfExist(E entity);

    /**
     * Retrieves all entities of the specified type from the system.
     * This method returns a complete list of all persisting entities.
     *
     * @return APIResponse containing a list of all entities, empty list if no entities exist
     */
    APIResponse<List<E>> findAll();

    /**
     * Finds and retrieves a specific entity by its unique identifier.
     * This method performs a lookup operation using the provided ID.
     *
     * @param id the unique identifier of the entity to retrieve must not be null
     * @return APIResponse containing the found entity
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null
     */
    APIResponse<E> findById(ID id) throws NotFoundException;

    /**
     * Updates an existing entity with new data.
     * This method finds the entity by ID and updates it with the provided entity data.
     *
     * @param id the unique identifier of the entity to update must not be null
     * @param entity the entity containing updated data must not be null
     * @return APIResponse containing the updated entity
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if either id or entity parameters are null
     */
    APIResponse<E> update(ID id, E entity) throws NotFoundException;

    /**
     * Deletes an entity from the system by its unique identifier.
     * This method performs a soft or hard delete operation depending on implementation.
     *
     * @param id the unique identifier of the entity to delete must not be null
     * @return APIResponse containing the deleted entity information
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null
     */
    APIResponse<E> delete(ID id) throws NotFoundException;
}