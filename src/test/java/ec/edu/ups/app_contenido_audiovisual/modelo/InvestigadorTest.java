package ec.edu.ups.app_contenido_audiovisual.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InvestigadorTest {
    @Test
    public void testCrearInvestigador() {
        Investigador inv = new Investigador(3, "Jane Goodall", "Primates", "National Geographic");
        assertEquals("Jane Goodall", inv.getNombre());
        assertEquals("Primates", inv.getEspecialidad());
        assertEquals("National Geographic", inv.getInstitucion());
    }

    @Test
    public void testToCSV() {
        Investigador inv = new Investigador(3, "Jane Goodall", "Primates", "National Geographic");
        String csv = inv.toCSV();
        assertTrue(csv.contains("Jane Goodall"));
    }

}
