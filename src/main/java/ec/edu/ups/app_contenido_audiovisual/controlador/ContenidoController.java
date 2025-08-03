package ec.edu.ups.app_contenido_audiovisual.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.app_contenido_audiovisual.modelo.*;
import ec.edu.ups.app_contenido_audiovisual.persistencia.GestorArchivos;
import ec.edu.ups.app_contenido_audiovisual.util.PathUtil;

public class ContenidoController {

    private List<ContenidoAudiovisual> contenidos = new ArrayList<>();
    private List<Actor> actores = new ArrayList<>();
    private List<Investigador> investigadores = new ArrayList<>();
    private List<Temporada> temporadas = new ArrayList<>();

    private GestorArchivos gestorArchivos = new GestorArchivos();

    /**
     * Carga todos los datos desde los archivos CSV y asocia los elementos por contenidoId.
     */
    public void cargarDatos() throws IOException {
        contenidos = gestorArchivos.leerContenidos(PathUtil.getContenidosPath());
        actores = gestorArchivos.leerActores(PathUtil.getActoresPath());
        investigadores = gestorArchivos.leerInvestigadores(PathUtil.getInvestigadoresPath());
        temporadas = gestorArchivos.leerTemporadas(PathUtil.getTemporadasPath());

        // Asociar los datos cargados a cada contenido por su ID
        for (ContenidoAudiovisual contenido : contenidos) {
            if (contenido instanceof Pelicula pelicula) {
                actores.stream()
                        .filter(a -> a.getContenidoId() == pelicula.getContenidoId())
                        .forEach(pelicula::agregarActor);

            } else if (contenido instanceof SerieTV serie) {
                temporadas.stream()
                        .filter(t -> t.getContenidoId() == serie.getContenidoId())
                        .forEach(serie::agregarTemporada);

            } else if (contenido instanceof Documental documental) {
                investigadores.stream()
                        .filter(i -> i.getContenidoId() == documental.getContenidoId())
                        .forEach(documental::agregarInvestigador);
            }
        }
    }

    /**
     * Guarda todos los datos actuales en sus respectivos archivos CSV.
     */
    public void guardarDatos() throws IOException {
        gestorArchivos.escribirContenidos(PathUtil.getContenidosPath(), contenidos);
        gestorArchivos.escribirActores(PathUtil.getActoresPath(), actores);
        gestorArchivos.escribirInvestigadores(PathUtil.getInvestigadoresPath(), investigadores);
        gestorArchivos.escribirTemporadas(PathUtil.getTemporadasPath(), temporadas);
    }

    // Métodos de listar
    public List<ContenidoAudiovisual> listarContenidos() {
        return contenidos;
    }

    public List<Actor> listarActores() {
        return actores;
    }

    public List<Investigador> listarInvestigadores() {
        return investigadores;
    }

    public List<Temporada> listarTemporadas() {
        return temporadas;
    }

    // Métodos de agregar contenido y sus elementos
    public void agregarContenido(ContenidoAudiovisual contenido) {
        contenidos.add(contenido);
    }

    public void agregarActor(Actor actor) {
        actores.add(actor);
    }

    public void agregarInvestigador(Investigador investigador) {
        investigadores.add(investigador);
    }

    public void agregarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }
}
