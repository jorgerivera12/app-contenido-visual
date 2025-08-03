package ec.edu.ups.app_contenido_audiovisual.persistencia;

import java.util.List;
import java.io.IOException;

import ec.edu.ups.app_contenido_audiovisual.modelo.Actor;
import ec.edu.ups.app_contenido_audiovisual.modelo.Temporada;
import ec.edu.ups.app_contenido_audiovisual.modelo.Investigador;
import ec.edu.ups.app_contenido_audiovisual.modelo.ContenidoAudiovisual;

public interface ArchivoCSV {
    List<ContenidoAudiovisual> leerContenidos(String ruta) throws IOException;
    List<Actor> leerActores(String ruta) throws IOException;
    List<Investigador> leerInvestigadores(String ruta) throws IOException;
    List<Temporada> leerTemporadas(String ruta) throws IOException;

    void escribirContenidos(String ruta, List<ContenidoAudiovisual> lista) throws IOException;
}