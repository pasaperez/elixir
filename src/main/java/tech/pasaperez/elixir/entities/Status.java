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

package tech.pasaperez.elixir.entities;

import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.dtos.ErrorDTO;

/**
 * Enumeration that represents the possible status states for API responses in the Elixir application.
 * This enum provides a standardized way to indicate whether an API operation has completed
 * successfully or encountered an error, primarily used within {@link APIResponse}.
 *
 * <p>This enum serves as the primary status indicator for all API responses, enabling clients
 * to quickly determine the outcome of their requests without needing to parse complex response
 * structures or HTTP status codes alone.</p>
 *
 * <p>The enum is designed to work seamlessly with the {@code APIResponse<O>} wrapper class,
 * providing consistent status reporting across all API endpoints in the application.</p>
 *
 * <p><strong>Usage in API responses:</strong></p>
 * <pre>
 * // Successful API response
 * APIResponse&lt;User&gt; response = APIResponse.&lt;User&gt;builder()
 *     .data(user)
 *     .status(Status.SUCCESS)
 *     .build();
 *
 * // Error API response
 * APIResponse&lt;Void&gt; errorResponse = APIResponse.&lt;Void&gt;builder()
 *     .status(Status.ERROR)
 *     .error(errorList)
 *     .build();
 * </pre>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Enum
 */
public enum Status {

    /**
     * Indicates that an API operation has completed successfully without any errors.
     * When this status is used in an {@code APIResponse}, it typically means:
     * <ul>
     *   <li>The requested operation was executed successfully</li>
     *   <li>Data validation passed without issues</li>
     *   <li>Business logic constraints were satisfied</li>
     *   <li>Database operations completed successfully</li>
     *   <li>The response contains valid data in the {@code data} field</li>
     * </ul>
     *
     * <p>Responses with SUCCESS status should have a populated {@code data} field
     * and a null or empty {@code error} field.</p>
     */
    SUCCESS,
    /**
     * Indicates that an API operation has encountered an error and could not complete successfully.
     * When this status is used in an {@code APIResponse}, it typically means:
     * <ul>
     *   <li>Input validation failed with specific field errors</li>
     *   <li>Business logic constraints were violated</li>
     *   <li>Database operations failed or were rolled back</li>
     *   <li>External service dependencies are unavailable</li>
     *   <li>Authentication or authorization failures occurred</li>
     * </ul>
     *
     * <p>Responses with ERROR status should have a null {@code data} field and
     * a populated {@code error} field containing detailed error information using
     * {@link ErrorDTO} objects.</p>
     *
     * <p><strong>Best practice:</strong> Always provide meaningful error details
     * in the {@code error} field to help clients understand what went wrong and
     * how to potentially resolve the issue.</p>
     */
    ERROR
}