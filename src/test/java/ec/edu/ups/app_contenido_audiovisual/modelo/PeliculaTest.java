package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PeliculaTest {
	@Test
    public void testCrearPelicula() {
        Pelicula pelicula = new Pelicula(1, "Inception", 2010, "Christopher Nolan");
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(2010, pelicula.getAnio());
        assertEquals("Christopher Nolan", pelicula.getDirector());
    }

    @Test
    public void testAgregarActor() {
        Pelicula pelicula = new Pelicula(1, "Inception", 2010, "Christopher Nolan");
        Actor actor = new Actor(1, "Leonardo DiCaprio", "Cobb", 5);

        pelicula.agregarActor(actor);
        assertEquals(1, pelicula.getActores().size());
        assertEquals("Leonardo DiCaprio", pelicula.getActores().get(0).getNombre());
    }

    @Test
    public void testToCSV() {
        Pelicula pelicula = new Pelicula(1, "Inception", 2010, "Christopher Nolan");
        String csv = pelicula.toCSV();
        assertTrue(csv.startsWith("1,Pelicula"));
        assertTrue(csv.contains("Inception"));
    }
    
}
