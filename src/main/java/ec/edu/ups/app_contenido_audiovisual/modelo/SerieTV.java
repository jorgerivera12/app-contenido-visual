package ec.edu.ups.app_contenido_audiovisual.modelo;

import java.util.ArrayList;
import java.util.List;

public class SerieTV extends ContenidoAudiovisual {
    private List<Temporada> temporadas = new ArrayList<>();

    public SerieTV(int contenidoId,String titulo, int anio) {
        super(contenidoId,titulo, anio);
    }

    public void agregarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    @Override
    public String toCSV() {
        return getContenidoId() + ",SerieTV," + getTitulo() + "," + getAnio();
    }
}