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

package tech.pasaperez.elixir.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Custom exception class for handling response-related errors in the Elixir application.
 * This exception extends the standard Exception class and provides additional fields
 * to store specific error information including a custom message and error name.
 *
 * <p>This class uses Lombok annotations to automatically generate getter, setter,
 * toString, and constructor methods, reducing boilerplate code.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * throw new ResponseException("Connection failed", "NetworkError");
 * }
 * </pre>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see Exception
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResponseException extends Exception {

    /**
     * Custom error message describing the specific details of the exception.
     * This message provides more context about what went wrong during execution.
     */
    private String message;

    /**
     * Name or identifier for the type of error that occurred.
     * This field can be used to categorize different types of response exceptions
     * for better error handling and logging purposes.
     */
    private String name;

}