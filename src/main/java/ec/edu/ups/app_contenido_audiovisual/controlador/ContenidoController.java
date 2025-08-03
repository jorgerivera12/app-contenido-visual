package ec.edu.ups.app_contenido_audiovisual.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.app_contenido_audiovisual.modelo.*;
import ec.edu.ups.app_contenido_audiovisual.persistencia.GestorArchivos;
import ec.edu.ups.app_contenido_audiovisual.util.PathUtil;

/**
 * Controlador principal que gestiona el flujo de datos entre la capa de persistencia
 * (archivos CSV) y la aplicación.  
 * 
 * Permite cargar, guardar, listar y agregar contenidos audiovisuales
 * (Películas, Series de TV y Documentales) y sus elementos asociados
 * (Actores, Temporadas e Investigadores).
 * 
 * @author Jorge
 * @version 1.0
 */
public class ContenidoController {

    /** Lista de contenidos audiovisuales (Películas, Series y Documentales). */
    private List<ContenidoAudiovisual> contenidos = new ArrayList<>();
    
    /** Lista de actores asociados a las películas. */
    private List<Actor> actores = new ArrayList<>();
    
    /** Lista de investigadores asociados a los documentales. */
    private List<Investigador> investigadores = new ArrayList<>();
    
    /** Lista de temporadas asociadas a las series. */
    private List<Temporada> temporadas = new ArrayList<>();

    /** Objeto que maneja la lectura y escritura de los archivos CSV. */
    private GestorArchivos gestorArchivos = new GestorArchivos();

    /**
     * Carga todos los datos desde los archivos CSV y realiza la asociación 
     * de los elementos (actores, temporadas e investigadores) con cada 
     * contenido audiovisual usando el atributo contenidoId.
     * 
     * @throws IOException Si ocurre un error al leer los archivos CSV.
     */
    public void cargarDatos() throws IOException {
        contenidos = gestorArchivos.leerContenidos(PathUtil.getContenidosPath());
        actores = gestorArchivos.leerActores(PathUtil.getActoresPath());
        investigadores = gestorArchivos.leerInvestigadores(PathUtil.getInvestigadoresPath());
        temporadas = gestorArchivos.leerTemporadas(PathUtil.getTemporadasPath());

        // Asociar datos cargados a cada contenido por su contenidoId
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
     * Guarda todos los datos actuales en los archivos CSV correspondientes.
     * 
     * @throws IOException Si ocurre un error al escribir los archivos CSV.
     */
    public void guardarDatos() throws IOException {
        gestorArchivos.escribirContenidos(PathUtil.getContenidosPath(), contenidos);
        gestorArchivos.escribirActores(PathUtil.getActoresPath(), actores);
        gestorArchivos.escribirInvestigadores(PathUtil.getInvestigadoresPath(), investigadores);
        gestorArchivos.escribirTemporadas(PathUtil.getTemporadasPath(), temporadas);
    }

    /**
     * Devuelve la lista de contenidos audiovisuales cargados.
     * 
     * @return Lista de {@link ContenidoAudiovisual}.
     */
    public List<ContenidoAudiovisual> listarContenidos() {
        return contenidos;
    }

    /**
     * Devuelve la lista de actores cargados.
     * 
     * @return Lista de {@link Actor}.
     */
    public List<Actor> listarActores() {
        return actores;
    }

    /**
     * Devuelve la lista de investigadores cargados.
     * 
     * @return Lista de {@link Investigador}.
     */
    public List<Investigador> listarInvestigadores() {
        return investigadores;
    }

    /**
     * Devuelve la lista de temporadas cargadas.
     * 
     * @return Lista de {@link Temporada}.
     */
    public List<Temporada> listarTemporadas() {
        return temporadas;
    }

    /**
     * Agrega un nuevo contenido audiovisual (Película, Serie o Documental).
     * 
     * @param contenido Objeto de tipo {@link ContenidoAudiovisual} a agregar.
     */
    public void agregarContenido(ContenidoAudiovisual contenido) {
        contenidos.add(contenido);
    }

    /**
     * Agrega un nuevo actor a la lista de actores.
     * 
     * @param actor Objeto de tipo {@link Actor} a agregar.
     */
    public void agregarActor(Actor actor) {
        actores.add(actor);
    }

    /**
     * Agrega un nuevo investigador a la lista de investigadores.
     * 
     * @param investigador Objeto de tipo {@link Investigador} a agregar.
     */
    public void agregarInvestigador(Investigador investigador) {
        investigadores.add(investigador);
    }

    /**
     * Agrega una nueva temporada a la lista de temporadas.
     * 
     * @param temporada Objeto de tipo {@link Temporada} a agregar.
     */
    public void agregarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }
}
