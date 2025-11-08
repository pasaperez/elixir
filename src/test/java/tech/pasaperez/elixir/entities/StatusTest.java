package tech.pasaperez.elixir.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    public void testEnumValues() {
        assertEquals(Status.SUCCESS, Status.valueOf("SUCCESS"));
        assertEquals(Status.ERROR, Status.valueOf("ERROR"));
    }

    @Test
    public void testEnumToString() {
        assertEquals("SUCCESS", Status.SUCCESS.toString());
        assertEquals("ERROR", Status.ERROR.toString());
    }
}
