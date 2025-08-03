package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SerieTVTest {

    @Test
    public void testCrearSerieTV() {
        SerieTV serie = new SerieTV(2, "Stranger Things", 2016);
        assertEquals("Stranger Things", serie.getTitulo());
        assertEquals(2016, serie.getAnio());
    }

    @Test
    public void testAgregarTemporada() {
        SerieTV serie = new SerieTV(2, "Stranger Things", 2016);
        Temporada temporada = new Temporada(2, 1, 8);

        serie.agregarTemporada(temporada);
        assertEquals(1, serie.getTemporadas().size());
        assertEquals(8, serie.getTemporadas().get(0).getEpisodios());
    }

    @Test
    public void testToCSV() {
        SerieTV serie = new SerieTV(2, "Stranger Things", 2016);
        String csv = serie.toCSV();
        assertTrue(csv.startsWith("2,SerieTV"));
    }
	
}
