package tech.pasaperez.elixir.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseTest {

    @Test
    public void testIdGeneration() {
        Base base = new Base();
        assertNull(base.getId());

        base.setId(1L);
        assertEquals(1L, base.getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Base base1 = new Base();
        base1.setId(1L);
        Base base2 = new Base();
        base2.setId(1L);

        assertNotEquals(base1, base2);
        assertNotEquals(base1.hashCode(), base2.hashCode());
    }

    @Test
    public void testNotEquals() {
        Base base1 = new Base();
        base1.setId(1L);

        Base base2 = new Base();
        base2.setId(2L);

        assertNotEquals(base1, base2);
        assertNotEquals(base1.hashCode(), base2.hashCode());
    }
}