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

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Base entity class that provides common functionality for all entities in the Elixir application.
 * This class serves as a superclass for other entity classes, providing a standardized
 * primary key field and basic entity structure.
 *
 * <p>This class is annotated with {@code @MappedSuperclass} to indicate that it should
 * not be mapped to a database table itself, but its properties should be inherited by
 * subclasses that are actual entities.</p>
 *
 * <p>The class implements {@link Serializable} to support serialization of entity objects,
 * which is useful for caching, session storage, and distributed computing scenarios.</p>
 *
 * <p>Lombok annotations are used to automatically generate common methods:
 * <ul>
 *   <li>{@code @Getter} and {@code @Setter} - Generate getter and setter methods</li>
 *   <li>{@code @NoArgsConstructor} - Generate default constructor</li>
 *   <li>{@code @AllArgsConstructor} - Generate constructor with all fields</li>
 * </ul>
 * </p>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see MappedSuperclass
 * @see Serializable
 * @see Entity
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Base implements Serializable {

    /**
     * The unique identifier for the entity. This field serves as the primary key
     * for all entities that extend this base class.
     *
     * <p>The ID is automatically generated using the {@link GenerationType#IDENTITY}
     * strategy, which relies on the database's auto-increment feature to assign
     * unique values. This is suitable for most database systems including MySQL,
     * PostgreSQL, and SQL Server.</p>
     *
     * <p>The field is mapped to the "id" column in the database table and is
     * marked as the primary key using the {@code @Id} annotation.</p>
     *
     * @see Id
     * @see GeneratedValue
     * @see GenerationType#IDENTITY
     * @see Column
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}