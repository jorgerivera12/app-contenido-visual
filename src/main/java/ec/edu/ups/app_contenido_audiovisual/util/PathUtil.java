package ec.edu.ups.app_contenido_audiovisual.util;


/**
 * Clase de utilidades para centralizar las rutas de los archivos CSV.
 * 
 * Permite obtener la ubicación de los archivos de datos sin repetir 
 * el path en varias clases. Si el directorio cambia, solo hay que
 * actualizarlo aquí.
 */
public class PathUtil {
    
    // Carpeta base donde se encuentran los archivos CSV
    private static final String BASE_PATH = "src/main/resources/";

    /**
     * Devuelve la ruta completa del archivo contenidos.csv
     * @return String con el path del archivo contenidos.csv
     */
    public static String getContenidosPath() {
        return BASE_PATH + "contenidos.csv";
    }

    /**
     * Devuelve la ruta completa del archivo actores.csv
     * @return String con el path del archivo actores.csv
     */
    public static String getActoresPath() {
        return BASE_PATH + "actores.csv";
    }

    /**
     * Devuelve la ruta completa del archivo investigadores.csv
     * @return String con el path del archivo investigadores.csv
     */
    public static String getInvestigadoresPath() {
        return BASE_PATH + "investigadores.csv";
    }

    /**
     * Devuelve la ruta completa del archivo temporadas.csv
     * @return String con el path del archivo temporadas.csv
     */
    public static String getTemporadasPath() {
        return BASE_PATH + "temporadas.csv";
    }
}
