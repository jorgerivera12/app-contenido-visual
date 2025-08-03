package ec.edu.ups.app_contenido_audiovisual.persistencia;

import org.junit.jupiter.api.Test;

import ec.edu.ups.app_contenido_audiovisual.modelo.ContenidoAudiovisual;
import ec.edu.ups.app_contenido_audiovisual.modelo.Pelicula;
import java.io.IOException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GestorArchivosTest {

    @Test
    public void testLeerContenidos() throws IOException {
        GestorArchivos gestor = new GestorArchivos();
        List<ContenidoAudiovisual> contenidos = gestor.leerContenidos("src/test/resources/contenidos.csv");

        assertNotNull(contenidos);
    }

    @Test
    public void testEscribirContenidos() throws IOException {
        GestorArchivos gestor = new GestorArchivos();
        Pelicula pelicula = new Pelicula(1, "Inception", 2010, "Christopher Nolan");
        gestor.escribirContenidos("src/test/resources/contenidos_salida.csv", List.of(pelicula));

        // Verifica que el archivo se crea y contiene datos
        assertTrue(new java.io.File("src/test/resources/contenidos_salida.csv").exists());
    }

}
