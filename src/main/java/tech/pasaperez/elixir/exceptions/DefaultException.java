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

/**
 * Default runtime exception class for general application errors.
 * This exception serves as a base runtime exception for the Elixir application
 * when specific exception types are not required or available. It extends RuntimeException
 * to provide unchecked exception behavior.
 *
 * <p>This exception is typically used for general error scenarios where a more specific
 * exception type is not necessary or when wrapping other exceptions with application-specific
 * context. Being a RuntimeException, it does not require explicit handling in method signatures.</p>
 *
 * <p>Usage examples include configuration errors, unexpected application states,
 * or when converting checked exceptions to unchecked ones for cleaner API design.</p>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see java.lang.RuntimeException
 */
public class DefaultException extends RuntimeException {

    /**
     * Constructs a new DefaultException with the specified detail message.
     * The detail message is saved for later retrieval by the getMessage() method
     * and provides information about the specific error condition that occurred.
     *
     * @param message the detail message explaining the reason for the exception may be null
     */
    public DefaultException(String message) {
        super(message);
    }
}