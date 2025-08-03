package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;	

public class ActorTest {
	
	@Test
    public void testCrearActor() {
        Actor actor = new Actor(1, "Leonardo DiCaprio", "Cobb", 5);
        assertEquals("Leonardo DiCaprio", actor.getNombre());
        assertEquals("Cobb", actor.getPersonajeMasFamoso());
        assertEquals(5, actor.getPremiosGanados());
    }

    @Test
    public void testToCSV() {
        Actor actor = new Actor(1, "Leonardo DiCaprio", "Cobb", 5);
        String csv = actor.toCSV();
        assertTrue(csv.contains("Leonardo DiCaprio"));
    }
}
