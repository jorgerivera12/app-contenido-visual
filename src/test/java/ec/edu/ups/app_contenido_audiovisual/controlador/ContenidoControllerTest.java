package ec.edu.ups.app_contenido_audiovisual.controlador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ec.edu.ups.app_contenido_audiovisual.modelo.Pelicula;


public class ContenidoControllerTest {
	
	private ContenidoController controller;

    @BeforeEach
    public void setUp() {
        controller = new ContenidoController();
    }

    @Test
    public void testAgregarContenido() {
        Pelicula pelicula = new Pelicula(1, "Inception", 2010, "Christopher Nolan");
        controller.agregarContenido(pelicula);

        assertEquals(1, controller.listarContenidos().size());
    }

    @Test
    public void testCargarDatosNoLanzaExcepcion() {
        assertDoesNotThrow(() -> controller.cargarDatos());
    }
}
