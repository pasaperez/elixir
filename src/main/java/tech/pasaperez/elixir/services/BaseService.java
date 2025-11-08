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

package tech.pasaperez.elixir.services;

import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.dtos.mapper.MapperBase;
import tech.pasaperez.elixir.entities.Base;
import tech.pasaperez.elixir.exceptions.AlreadyExistException;
import tech.pasaperez.elixir.exceptions.NotFoundException;
import tech.pasaperez.elixir.repositories.BaseRepository;
import tech.pasaperez.elixir.services.interfaces.IBaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Abstract base service implementation that provides common CRUD operations for entities.
 * This class implements the IBaseService interface and provides default implementations
 * for standard entity operations. It serves as a foundation for specific service implementations
 * by handling common functionality such as entity persistence, retrieval, updates, and deletion.
 *
 * <p>This class uses a repository pattern for data access and includes automatic mapping
 * to API response DTOs through the MapperBase utility class. All operations include
 * proper error handling with custom exceptions for common scenarios.</p>
 *
 * @param <E> the entity type that extends Base
 * @param <ID> the type of the entity's identifier must be Serializable
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see IBaseService
 * @see Base
 * @see BaseRepository
 * @see MapperBase
 */
public abstract class BaseService <E extends Base, ID extends Serializable> implements IBaseService<E, ID> {

    /**
     * The repository instance used for data persistence operations.
     * This field provides access to the underlying data layer for entity management.
     */
    protected final BaseRepository<E, ID> baseRepository;

    /**
     * Constructs a new BaseService with the specified repository.
     * This constructor initializes the service with a repository instance that will be used
     * for all data access operations throughout the service lifecycle.
     *
     * @param baseRepository the repository instance for data access operations must not be null
     * @throws IllegalArgumentException if baseRepository is null
     */
    public BaseService(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * Creates a new entity in the system after validating it doesn't already exist.
     * This method first checks for entity's existence using the checkIfExist method,
     * then persists the entity if it's unique and returns the saved entity wrapped in an API response.
     *
     * @param entity the entity to be created must not be null
     * @return APIResponse containing the created entity with its assigned identifier
     * @throws AlreadyExistException if an entity with the same characteristics already exists
     * @throws IllegalArgumentException if the entity parameter is null
     * @see #checkIfExist(Base)
     */
    @Override
    public APIResponse<E> create(E entity) throws AlreadyExistException {
        if(checkIfExist(entity)){
            throw new AlreadyExistException();
        }
        return MapperBase.toDTO(baseRepository.save(entity));
    }

    /**
     * Checks whether an entity with the same characteristics already exists in the system.
     * This method retrieves all entities from the repository and compares each one with the
     * provided entity using the equals method to determine if a duplicate exists.
     *
     * @param entity the entity to check for existence must not be null
     * @return true if an entity with matching characteristics exists, false otherwise
     * @throws IllegalArgumentException if the entity parameter is null
     */
    @Override
    public boolean checkIfExist(E entity) {
        List<E> entitiesList = baseRepository.findAll();

        for(E eachEntity :entitiesList){
            if(eachEntity.equals(entity)){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and retrieves a specific entity by its unique identifier.
     * This method performs a repository lookup using the provided ID and returns
     * the found entity wrapped in an API response or throws an exception if not found.
     *
     * @param id the unique identifier of the entity to retrieve must not be null
     * @return APIResponse containing the found entity
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null
     */
    @Override
    public APIResponse<E> findById(ID id) throws NotFoundException {
        Optional<E> optional = baseRepository.findById(id);

        if (optional.isEmpty()){
            throw new NotFoundException(String.valueOf(id));
        }
        return MapperBase.toDTO(optional.get());
    }

    /**
     * Retrieves all entities of the specified type from the system.
     * This method fetches all persisting entities from the repository and returns
     * them wrapped in an API response containing a list of entities.
     *
     * @return APIResponse containing a list of all entities, empty list if no entities exist
     */
    @Override
    public APIResponse<List<E>> findAll() {
        return MapperBase.toDTO(baseRepository.findAll());
    }

    /**
     * Updates an existing entity with new data while preserving its original identifier.
     * This method first verifies the entity exists by its ID, then updates the provided
     * entity with the existing entity's ID before saving and returning the updated entity.
     *
     * @param id the unique identifier of the entity to update must not be null
     * @param entity the entity containing updated data must not be null
     * @return APIResponse containing the updated entity
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if either id or entity parameters are null
     */
    @Override
    public APIResponse<E> update(ID id, E entity) throws NotFoundException {
        Optional<E> optional  = baseRepository.findById(id);

        if (optional.isEmpty()){
            throw new NotFoundException(String.valueOf(id));
        }
        entity.setId(optional.get().getId());
        return MapperBase.toDTO(baseRepository.save(entity));
    }

    /**
     * Deletes an entity from the system by its unique identifier.
     * This method first verifies the entity exists, then performs the deletion operation
     * and returns the deleted entity information wrapped in an API response.
     *
     * @param id the unique identifier of the entity to delete must not be null
     * @return APIResponse containing the deleted entity information
     * @throws NotFoundException if no entity exists with the provided identifier
     * @throws IllegalArgumentException if the id parameter is null
     */
    @Override
    public APIResponse<E> delete(ID id) throws NotFoundException {
        Optional<E> optional  = baseRepository.findById(id);

        if (optional.isEmpty()){
            throw new NotFoundException(String.valueOf(id));
        }
        E entityDelete = optional.get();
        baseRepository.delete(entityDelete);
        return MapperBase.toDTO(entityDelete);
    }
}