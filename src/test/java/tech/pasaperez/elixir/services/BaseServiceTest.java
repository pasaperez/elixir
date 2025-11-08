package tech.pasaperez.elixir.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.pasaperez.elixir.dtos.APIResponse;
import tech.pasaperez.elixir.dtos.mapper.MapperBase;
import tech.pasaperez.elixir.entities.Base;
import tech.pasaperez.elixir.exceptions.AlreadyExistException;
import tech.pasaperez.elixir.exceptions.NotFoundException;
import tech.pasaperez.elixir.services.interfaces.IBaseService;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class BaseServiceTest<E extends Base, ID extends Serializable> {

    @Mock
    private IBaseService<E, ID> baseService;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            System.out.println("Mocks initialized");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreate() throws AlreadyExistException {
        E entity = createSampleEntity();

        when(baseService.create(entity)).thenReturn(createSampleAPIResponse(entity));
        APIResponse<E> response = baseService.create(entity);

        assertNotNull(response);
        assertEquals(entity, response.getData());
    }

    @Test
    public void testFindById() throws NotFoundException {
        E entity = createSampleEntity();

        when(baseService.findById(null)).thenReturn(createSampleAPIResponse(entity));
        APIResponse<E> response = baseService.findById(null);

        assertNotNull(response);
        assertEquals(entity, response.getData());
    }

    @Test
    public void testUpdate() throws NotFoundException {
        E updatedEntity = createSampleEntity();

        when(baseService.update(null, updatedEntity)).thenReturn(createSampleAPIResponse(updatedEntity));
        APIResponse<E> response = baseService.update(null, updatedEntity);

        assertNotNull(response);
        assertEquals(updatedEntity, response.getData());
    }

    @Test
    public void testDelete() throws NotFoundException {
        E entity = createSampleEntity();

        when(baseService.delete(null)).thenReturn(createSampleAPIResponse(entity));
        APIResponse<E> response = baseService.delete(null);

        assertNotNull(response);
        assertEquals(entity, response.getData());
    }

    @SuppressWarnings("unchecked")
    private E createSampleEntity() {
        return (E) new Base();
    }

    private APIResponse<E> createSampleAPIResponse(E entity) {
        return MapperBase.toDTO(entity);
    }
}
