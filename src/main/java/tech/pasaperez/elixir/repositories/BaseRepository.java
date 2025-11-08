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

package tech.pasaperez.elixir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tech.pasaperez.elixir.entities.Base;

import java.io.Serializable;

/**
 * Base repository interface that provides common data access functionality for all entities
 * in the Elixir application. This interface serves as a foundation for repository classes,
 * establishing a consistent data access pattern across the application.
 *
 * <p>This interface extends {@link JpaRepository} to inherit all standard JPA repository
 * operations including CRUD operations, pagination, and batch operations. By creating
 * a base repository interface, the application ensures that all entity repositories
 * follow the same contract and provide consistent functionality.</p>
 *
 * <p>The interface uses generic type parameters to provide type safety while remaining
 * flexible enough to work with any entity that extends the {@link Base} class:</p>
 * <ul>
 *   <li><strong>E</strong> - The entity type that must extend {@link Base}</li>
 *   <li><strong>ID</strong> - The identifier type that must implement {@link Serializable}</li>
 * </ul>
 *
 * <p>The {@code @NoRepositoryBean} annotation prevents Spring from attempting to create
 * an instance of this interface directly, as it's intended to be used only as a base
 * interface for concrete repository implementations.</p>
 *
 * <p><strong>Inherited functionality from JpaRepository includes:</strong></p>
 * <ul>
 *   <li>Basic CRUD operations (save, find, update, delete)</li>
 *   <li>Batch operations (saveAll, deleteAll, deleteInBatch)</li>
 *   <li>Query methods (fi(ndAll, findById, existsById, count)</li>
 *   <li>Pagination and sorting support (findAll with Pageable/Sort)</li>
 *   <li>Flush operations for immediate persistence</li>
 * </ul>
 *
 * <p><strong>Usage example:</strong></p>
 * <pre>
 * // Concrete repository implementation
 * &#64;Repository
 * public interface UserRepository extends BaseRepository&lt;User, Long&gt; {
 *     // Custom query methods specific to User entity
 *     List&lt;User&gt; findByEmail(String email);
 *     Optional&lt;User&gt; findByUsername(String username);
 * }
 *
 * // Service layer usage
 * &#64;Service
 * public class UserService {
 *
 *     &#64;Autowired
 *     private UserRepository userRepository;
 *
 *     public User createUser(User user) {
 *         return userRepository.save(user); // Inherited from BaseRepository
 *     }
 *
 *     public List&lt;User&gt; getAllUsers() {
 *         return userRepository.findAll(); // Inherited from BaseRepository
 *     }
 * }
 * </pre>
 *
 * <p><strong>Benefits of using BaseRepository:</strong></p>
 * <ul>
 *   <li>Consistent data access patterns across all repositories</li>
 *   <li>Centralized place to add common repository functionality</li>
 *   <li>Type safety through generic constraints</li>
 *   <li>Reduced boilerplate code in concrete repository interfaces</li>
 *   <li>Easy extension with custom base methods if needed in the future</li>
 * </ul>
 *
 * @param <E> the entity type that extends {@link Base}, representing the domain object
 *           to be persisted and retrieved from the database
 * @param <ID> the type of the entity's identifier, must implement {@link Serializable}
 *            to support various ID types (Long, String, UUID, etc.)
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see JpaRepository
 * @see Base
 * @see NoRepositoryBean
 * @see Serializable
 */
@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
}