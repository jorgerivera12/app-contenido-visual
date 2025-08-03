package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TemporadaTest {
	
	@Test
    public void testCrearTemporada() {
        Temporada temporada = new Temporada(2, 1, 8);
        assertEquals(1, temporada.getNumero());
        assertEquals(8, temporada.getEpisodios());
    }

    @Test
    public void testToCSV() {
        Temporada temporada = new Temporada(2, 1, 8);
        String csv = temporada.toCSV();
        assertTrue(csv.contains("1"));
    }
}
