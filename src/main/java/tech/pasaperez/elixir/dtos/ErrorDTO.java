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

/**
 * Data Transfer Object (DTO) record that represents error information for field-specific validation errors
 * or general error reporting within the Elixir application. This record provides a standardized structure
 * for communicating error details between different layers of the application and to API clients.
 *
 * <p>As a record, this class automatically provides:
 * <ul>
 *   <li>Immutable data structure with final fields</li>
 *   <li>Automatic generation of constructor, getters, equals(), hashCode(), and toString() methods</li>
 *   <li>Compact and readable syntax for simple data carriers</li>
 * </ul>
 * </p>
 *
 * <p>This DTO is commonly used for:
 * <ul>
 *   <li>Form validation error reporting</li>
 *   <li>API error response standardization</li>
 *   <li>Field-specific error messaging in user interfaces</li>
 *   <li>Validation constraint violation reporting</li>
 * </ul>
 * </p>
 *
 * <p><strong>Usage examples:</strong></p>
 * <pre>
 * // Field validation error
 * ErrorDTO emailError = new ErrorDTO("email", "Email format is invalid");
 *
 * // General error without specific field
 * ErrorDTO generalError = new ErrorDTO(null, "Operation failed due to server error");
 *
 * // Collection of errors for API response
 * List&lt;ErrorDTO&gt; errors = Arrays.asList(
 *     new ErrorDTO("username", "Username is required"),
 *     new ErrorDTO("password", "Password must be at least 8 characters")
 * );
 * </pre>
 *
 * @param field the name of the field associated with the error, or null for general errors
 * @param message the human-readable error message describing the issue
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Record
 */
public record ErrorDTO(String field, String message) {
}