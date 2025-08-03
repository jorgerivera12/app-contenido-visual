package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DocumentalTest {
    @Test
    public void testCrearDocumental() {
        Documental doc = new Documental(3, "Planeta Tierra", 2006, "Naturaleza");
        assertEquals("Planeta Tierra", doc.getTitulo());
        assertEquals("Naturaleza", doc.getTema());
    }

    @Test
    public void testAgregarInvestigador() {
        Documental doc = new Documental(3, "Planeta Tierra", 2006, "Naturaleza");
        Investigador inv = new Investigador(3, "David Attenborough", "Biología", "BBC");

        doc.agregarInvestigador(inv);
        assertEquals(1, doc.getInvestigadores().size());
        assertEquals("David Attenborough", doc.getInvestigadores().get(0).getNombre());
    }

    @Test
    public void testToCSV() {
        Documental doc = new Documental(3, "Planeta Tierra", 2006, "Naturaleza");
        String csv = doc.toCSV();
        assertTrue(csv.contains("Planeta Tierra"));
    }
}
