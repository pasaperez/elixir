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

package tech.pasaperez.elixir.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.pasaperez.elixir.entities.Status;

import java.util.List;

/**
 * Generic API response wrapper class that provides a standardized structure for all API responses
 * in the Elixir application. This class encapsulates response data along with status information
 * and error details, ensuring consistent API communication patterns.
 *
 * <p>This response wrapper implements a common API design pattern where all endpoints return
 * responses in a consistent format, making it easier for clients to handle responses uniformly
 * regardless of the specific operation or data type involved.</p>
 *
 * <p>The class uses generic typing ({@code <O>}) to support any type of response data while
 * maintaining type safety. The response structure includes:
 * <ul>
 *   <li><strong>data</strong> - The actual response payload of generic type O</li>
 *   <li><strong>status</strong> - Success or error status indicator</li>
 *   <li><strong>error</strong> - List of detailed error information when applicable</li>
 * </ul>
 * </p>
 *
 * <p>Lombok annotations provide automatic generation of:
 * <ul>
 *   <li>{@code @Data} - Getters, setters, toString(), equals(), and hashCode() methods</li>
 *   <li>{@code @Builder} - Builder pattern implementation for fluent object creation</li>
 *   <li>{@code @AllArgsConstructor} - Constructor with all fields</li>
 *   <li>{@code @NoArgsConstructor} - Default no-argument constructor</li>
 * </ul>
 * </p>
 *
 * <p>JSON serialization is optimized with {@code @JsonInclude(NON_NULL)} to exclude
 * null fields from the response, reducing payload size and improving API performance.</p>
 *
 * <p><strong>Usage examples:</strong></p>
 * <pre>
 * // Successful response with data
 * APIResponse&lt;User&gt; successResponse = APIResponse.&lt;User&gt;builder()
 *     .data(user)
 *     .status(Status.SUCCESS)
 *     .build();
 *
 * // Error response with validation errors
 * APIResponse&lt;Void&gt; errorResponse = APIResponse.&lt;Void&gt;builder()
 *     .status(Status.ERROR)
 *     .error(Arrays.asList(
 *         new ErrorDTO("email", "Email is required"),
 *         new ErrorDTO("password", "Password too short")
 *     ))
 *     .build();
 *
 * // Simple success response without data
 * APIResponse&lt;String&gt; simpleResponse = APIResponse.&lt;String&gt;builder()
 *     .data("Operation completed successfully")
 *     .status(Status.SUCCESS)
 *     .build();
 * </pre>
 *
 * @param <O> the type of the response data payload
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Status
 * @see ErrorDTO
 * @see JsonInclude
 * @see Builder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<O> {

    /**
     * The main response payload containing the actual data returned by the API operation.
     * This field uses generic typing to support any type of response data while maintaining
     * type safety throughout the application.
     *
     * <p>This field contains:
     * <ul>
     *   <li>The requested resource data for successful GET operations</li>
     *   <li>The created or updated resource for successful POST/PUT operations</li>
     *   <li>Confirmation messages for successful DELETE operations</li>
     *   <li>{@code null} for error responses or operations that don't return data</li>
     * </ul>
     * </p>
     *
     * <p>Due to the {@code @JsonInclude(NON_NULL)} annotation on the class, this field
     * will be excluded from JSON serialization when it's null, keeping response
     * payloads clean and minimal.</p>
     */
    private O data;
    /**
     * The status indicator that specifies whether the API operation completed successfully
     * or encountered an error. This field provides a quick way for clients to determine
     * the outcome of their request without parsing the entire response.
     *
     * <p>Status values:
     * <ul>
     *   <li>{@link Status#SUCCESS} - Operation completed successfully</li>
     *   <li>{@link Status#ERROR} - Operation failed due to validation, business logic, or system errors</li>
     * </ul>
     * </p>
     *
     * <p>This field should always be populated in API responses to ensure consistent
     * status reporting across all endpoints.</p>
     *
     * @see Status
     */
    private Status status;
    /**
     * A list of detailed error information that provides specific details about what
     * went wrong during the API operation. This field is primarily used when the
     * status is {@link Status#ERROR} and contains field-specific validation errors
     * or general error messages.
     *
     * <p>This list typically contains:
     * <ul>
     *   <li>Field validation errors with specific field names and error messages</li>
     *   <li>Business rule violation details</li>
     *   <li>System error information (when appropriate for client consumption)</li>
     *   <li>Multiple related errors that occurred during a single operation</li>
     * </ul>
     * </p>
     *
     * <p>For successful operations (status = SUCCESS), this field is typically null
     * and will be excluded from JSON serialization due to the class-level
     * {@code @JsonInclude(NON_NULL)} annotation.</p>
     *
     * <p><strong>Example error list:</strong></p>
     * <pre>
     * [
     *   {"field": "email", "message": "Email format is invalid"},
     *   {"field": "password", "message": "Password must be at least 8 characters"},
     *   {"field": null, "message": "User account is temporarily locked"}
     * ]
     * </pre>
     *
     * @see ErrorDTO
     * @see Status#ERROR
     */
    private List<ErrorDTO> error;

}