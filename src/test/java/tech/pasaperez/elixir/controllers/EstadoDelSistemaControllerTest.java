package tech.pasaperez.elixir.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EstadoDelSistemaControllerTest {

    @InjectMocks
    private EstadoDelSistemaController estadoDelSistemaController;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            System.out.println("Mocks initialized");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStatus() {
        ResponseEntity<String> response = estadoDelSistemaController.status();
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Todo Bien.", response.getBody());
    }
}
