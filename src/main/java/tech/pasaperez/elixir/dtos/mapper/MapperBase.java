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

package tech.pasaperez.elixir.dtos.mapper;

import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.entities.Status;

/**
 * Base utility class that provides common mapping functionality for converting entities
 * into standardized API response objects within the Elixir application. This class serves
 * as a foundation for consistent response formatting across different services and controllers.
 *
 * <p>The primary purpose of this mapper is to eliminate boilerplate code when creating
 * successful API responses by providing a centralized method that wraps any data object
 * into the standard {@link APIResponse} structure with appropriate success status.</p>
 *
 * <p>This class implements the utility class pattern with static methods only, making it
 * easy to use throughout the application without requiring instantiation. It promotes
 * consistency in API response formatting and reduces code duplication across controllers
 * and service layers.</p>
 *
 * <p><strong>Key benefits:</strong></p>
 * <ul>
 *   <li>Standardizes successful response creation across the application</li>
 *   <li>Reduces boilerplate code in controllers and services</li>
 *   <li>Ensures consistent use of SUCCESS status for positive outcomes</li>
 *   <li>Provides type-safe generic mapping functionality</li>
 *   <li>Centralizes response formatting logic for easier maintenance</li>
 * </ul>
 *
 * <p><strong>Usage examples:</strong></p>
 * <pre>
 * // In a controller method
 * &#64;GetMapping("/users/{id}")
 * public APIResponse&lt;User&gt; getUser(&#64;PathVariable Long id) {
 *     User user = userService.findById(id);
 *     return MapperBase.toDTO(user);
 * }
 *
 * // In a service method
 * public APIResponse&lt;List&lt;Product&gt;&gt; getAllProducts() {
 *     List&lt;Product&gt; products = productRepository.findAll();
 *     return MapperBase.toDTO(products);
 * }
 *
 * // With different data types
 * APIResponse&lt;String&gt; messageResponse = MapperBase.toDTO("Operation completed");
 * APIResponse&lt;Integer&gt; countResponse = MapperBase.toDTO(42);
 * </pre>
 *
 * <p><strong>Note:</strong> This mapper is designed specifically for successful operations.
 * For error responses, use the {@link APIResponse} builder directly with {@link Status#ERROR}
 * and appropriate error details.</p>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see APIResponse
 * @see Status
 */
public class MapperBase {

    /**
     * Converts any entity or data object into a standardized successful API response.
     * This method wraps the provided entity in an {@link APIResponse} object with
     * {@link Status#SUCCESS} status, creating a consistent response structure for
     * successful operations.
     *
     * <p>This utility method eliminates the need to manually create {@code APIResponse}
     * objects with success status throughout the application, ensuring consistency
     * and reducing code duplication. The method uses generic typing to maintain
     * type safety while supporting any data type.</p>
     *
     * <p>The resulting {@code APIResponse} will have:
     * <ul>
     *   <li>{@code data} field populated with the provided entity</li>
     *   <li>{@code status} field set to {@link Status#SUCCESS}</li>
     *   <li>{@code error} field left as null (excluded from JSON serialization)</li>
     * </ul>
     * </p>
     *
     * <p><strong>Type Safety:</strong> The method preserves the generic type of the
     * input entity, ensuring that the returned {@code APIResponse<O>} maintains the
     * same type parameter as the input, providing compile-time type checking.</p>
     *
     * <p><strong>Usage scenarios:</strong></p>
     * <ul>
     *   <li>Wrapping single entities retrieved from database queries</li>
     *   <li>Converting collections of entities for list endpoints</li>
     *   <li>Formatting simple data types (String, Integer, Boolean) for API responses</li>
     *   <li>Creating consistent responses from service layer methods</li>
     * </ul>
     *
     * <p><strong>Example transformations:</strong></p>
     * <pre>
     * // Input: User entity
     * // Output: APIResponse&lt;User&gt; with SUCCESS status and User in data field
     *
     * // Input: List&lt;Product&gt;
     * // Output: APIResponse&lt;List&lt;Product&gt;&gt; with SUCCESS status and list in data field
     *
     * // Input: "Welcome message"
     * // Output: APIResponse&lt;String&gt; with SUCCESS status and message in data field
     * </pre>
     *
     * @param <O> the generic type of the entity being wrapped
     * @param entity the entity or data object to be wrapped in the API response.
     *               Can be any type including primitives, objects, collections, or null
     * @return a new {@link APIResponse} instance containing the entity as data with SUCCESS status
     *
     * @see APIResponse#builder()
     * @see Status#SUCCESS
     */
    public static <O> APIResponse<O> toDTO(O entity) {
        return APIResponse.<O>builder().status(Status.SUCCESS).data(entity).build();
    }
}