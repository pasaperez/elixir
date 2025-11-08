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

package tech.pasaperez.elixir.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class that handles Cross-Origin Resource Sharing (CORS) settings
 * for the Elixir application. This configuration allows the application to handle
 * requests from different origins by configuring appropriate CORS mappings.
 *
 * <p>This class implements {@link WebMvcConfigurer} to customize the Spring MVC
 * configuration and enables web MVC through the {@code @EnableWebMvc} annotation.</p>
 *
 * @author Angel Santiago Perez
 * @version 1.0
 * @since 1.0
 * @see WebMvcConfigurer
 * @see Configuration
 * @see EnableWebMvc
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures Cross-Origin Resource Sharing (CORS) mappings for the application.
     * This method sets up CORS to allow requests from any origin with specific
     * HTTP methods (PUT, DELETE, GET, POST).
     *
     * <p>The configuration applies to all endpoints ("/**") and permits the most
     * commonly used HTTP methods for RESTful APIs. All origins ("*") are allowed
     * to make requests to this application.</p>
     *
     * <p><strong>Security Note:</strong> Allowing all origins ("*") may pose security
     * risks in production environments. Consider restricting origins to specific
     * domains for production deployments.</p>
     *
     * @param registry the {@link CorsRegistry} used to register CORS mappings
     * @see CorsRegistry#addMapping(String)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("PUT", "DELETE", "GET", "POST")
                .allowedOrigins("*");
    }
}