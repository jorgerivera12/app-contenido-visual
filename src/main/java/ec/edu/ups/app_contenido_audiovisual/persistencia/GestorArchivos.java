package ec.edu.ups.app_contenido_audiovisual.persistencia;

import java.io.*;
import java.util.*;
import ec.edu.ups.app_contenido_audiovisual.modelo.*;

/**
 * Clase encargada de gestionar la lectura y escritura de los datos
 * en archivos CSV para los diferentes elementos del sistema 
 * (contenidos audiovisuales, actores, investigadores y temporadas).
 * 
 * Implementa la interfaz {@link ArchivoCSV}.
 * 
 * Se encarga de:
 *   Leer y parsear datos desde archivos CSV.
 *   Escribir y persistir listas de objetos en archivos CSV
 * 
 * 
 * Cada tipo de entidad (ContenidoAudiovisual, Actor, Investigador, Temporada) 
 * tiene métodos específicos para su lectura y escritura.
 * 
 * @author Jorge
 * @version 1.0
 */
public class GestorArchivos implements ArchivoCSV {

    /**
     * Lee el archivo de contenidos audiovisuales (Películas, Series, Documentales)
     * y construye los objetos correspondientes.
     * 
     * @param ruta Ruta del archivo CSV de contenidos.
     * @return Lista de contenidos cargados.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public List<ContenidoAudiovisual> leerContenidos(String ruta) throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                int id = Integer.parseInt(datos[0]);
                String tipo = datos[1];

                // Crear instancia según el tipo
                switch (tipo) {
                    case "Pelicula" ->
                        contenidos.add(new Pelicula(id, datos[2], Integer.parseInt(datos[3]), datos[4]));
                    case "SerieTV" ->
                        contenidos.add(new SerieTV(id, datos[2], Integer.parseInt(datos[3])));
                    case "Documental" ->
                        contenidos.add(new Documental(id, datos[2], Integer.parseInt(datos[3]), datos[4]));
                }
            }
        }
        return contenidos;
    }

    /**
     * Lee el archivo CSV de actores y los retorna en una lista.
     * 
     * @param ruta Ruta del archivo CSV de actores.
     * @return Lista de actores cargados.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public List<Actor> leerActores(String ruta) throws IOException {
        List<Actor> actores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int contenidoId = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String personaje = datos[2];
                int premios = Integer.parseInt(datos[3]);

                actores.add(new Actor(contenidoId, nombre, personaje, premios));
            }
        }
        return actores;
    }

    /**
     * Lee el archivo CSV de investigadores y los retorna en una lista.
     * 
     * @param ruta Ruta del archivo CSV de investigadores.
     * @return Lista de investigadores cargados.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public List<Investigador> leerInvestigadores(String ruta) throws IOException {
        List<Investigador> investigadores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int contenidoId = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String especialidad = datos[2];
                String institucion = datos[3];

                investigadores.add(new Investigador(contenidoId, nombre, especialidad, institucion));
            }
        }
        return investigadores;
    }

    /**
     * Lee el archivo CSV de temporadas de series y las retorna en una lista.
     * 
     * @param ruta Ruta del archivo CSV de temporadas.
     * @return Lista de temporadas cargadas.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    @Override
    public List<Temporada> leerTemporadas(String ruta) throws IOException {
        List<Temporada> temporadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int contenidoId = Integer.parseInt(datos[0]);
                int numero = Integer.parseInt(datos[1]);
                int episodios = Integer.parseInt(datos[2]);

                temporadas.add(new Temporada(contenidoId, numero, episodios));
            }
        }
        return temporadas;
    }

    /**
     * Escribe los contenidos audiovisuales en un archivo CSV.
     * 
     * @param ruta Ruta del archivo CSV donde se guardarán los datos.
     * @param lista Lista de contenidos audiovisuales a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    @Override
    public void escribirContenidos(String ruta, List<ContenidoAudiovisual> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (ContenidoAudiovisual c : lista) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        }
    }

    /**
     * Escribe los actores en un archivo CSV.
     * 
     * @param ruta Ruta del archivo CSV donde se guardarán los datos.
     * @param lista Lista de actores a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void escribirActores(String ruta, List<Actor> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Actor a : lista) {
                bw.write(a.toCSV());
                bw.newLine();
            }
        }
    }

    /**
     * Escribe los investigadores en un archivo CSV.
     * 
     * @param ruta Ruta del archivo CSV donde se guardarán los datos.
     * @param lista Lista de investigadores a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void escribirInvestigadores(String ruta, List<Investigador> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Investigador i : lista) {
                bw.write(i.toCSV());
                bw.newLine();
            }
        }
    }

    /**
     * Escribe las temporadas en un archivo CSV.
     * 
     * @param ruta Ruta del archivo CSV donde se guardarán los datos.
     * @param lista Lista de temporadas a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void escribirTemporadas(String ruta, List<Temporada> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Temporada t : lista) {
                bw.write(t.toCSV());
                bw.newLine();
            }
        }
    }
}
