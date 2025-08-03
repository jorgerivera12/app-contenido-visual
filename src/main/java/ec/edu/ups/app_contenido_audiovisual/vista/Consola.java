package ec.edu.ups.app_contenido_audiovisual.vista;

import java.io.IOException;
import java.util.Scanner;

import ec.edu.ups.app_contenido_audiovisual.controlador.ContenidoController;
import ec.edu.ups.app_contenido_audiovisual.modelo.*;

/**
 * Clase que representa la vista en consola de la aplicación. 
 * 
 * Se encarga de mostrar un menú de opciones al usuario y 
 * gestionar la interacción con el {@link ContenidoController} 
 * para listar y agregar contenidos audiovisuales (Películas, Series de TV y Documentales).
 * 
 * @author Jorge
 * @version 1.0
 */
public class Consola {
    
    /** Controlador que maneja la lógica de negocio y el acceso a datos. */
    private ContenidoController controller = new ContenidoController();
    
    /** Objeto Scanner para leer los datos ingresados por el usuario. */
    private Scanner sc = new Scanner(System.in);

    /**
     * Método principal que muestra el menú en consola y procesa 
     * las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {
        // Cargar datos automáticamente al iniciar
        cargarDatos(); 

        int opcion;
        do {
            // Mostrar el menú principal
            System.out.println("======= MENÚ PRINCIPAL =======");
            System.out.println("1. Listar contenidos audiovisuales");
            System.out.println("2. Agregar nuevo contenido audiovisual");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> listarContenidos();
                case 2 -> agregarContenido();
                case 3 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 3);
    }

    /**
     * Lista todos los contenidos audiovisuales existentes junto con
     * sus datos asociados (actores, temporadas o investigadores).
     */
    private void listarContenidos() {
        if (controller.listarContenidos().isEmpty()) {
            System.out.println("No hay contenidos cargados.");
            return;
        }

        System.out.println("📽 Contenidos audiovisuales:");
        controller.listarContenidos().forEach(c -> {
            // Determinar tipo de contenido
            String tipo = c instanceof Pelicula ? "🎬 Película"
                        : c instanceof SerieTV ? "📺 SerieTV"
                        : c instanceof Documental ? "📚 Documental"
                        : "Desconocido";
            
            // Mostrar título y año
            System.out.println("- [" + tipo + "] " + c.getTitulo() + " (" + c.getAnio() + ")");

            // Mostrar elementos asociados
            if (c instanceof Pelicula p) {
                if (p.getActores().isEmpty()) {
                    System.out.println("   🎭 Actores: [Sin actores asignados]");
                } else {
                    System.out.println("   🎭 Actores:");
                    p.getActores().forEach(a -> System.out.println("     - " + a.getNombre()));
                }
            } else if (c instanceof SerieTV s) {
                if (s.getTemporadas().isEmpty()) {
                    System.out.println("   📺 Temporadas: [Sin temporadas asignadas]");
                } else {
                    System.out.println("   📺 Temporadas:");
                    s.getTemporadas().forEach(t ->
                        System.out.println("     - Temporada " + t.getNumero() + ": " + t.getEpisodios() + " episodios")
                    );
                }
            } else if (c instanceof Documental d) {
                if (d.getInvestigadores().isEmpty()) {
                    System.out.println("   🔬 Investigadores: [Sin investigadores asignados]");
                } else {
                    System.out.println("   🔬 Investigadores:");
                    d.getInvestigadores().forEach(i -> System.out.println("     - " + i.getNombre()));
                }
            }
        });
    }
    
    /**
     * Permite al usuario seleccionar el tipo de contenido a agregar (Película, Serie de TV o Documental)
     * y delega a los métodos correspondientes.
     */
    private void agregarContenido() {
        System.out.println("Seleccione el tipo de contenido a agregar:");
        System.out.println("1. Película");
        System.out.println("2. Serie de TV");
        System.out.println("3. Documental");
        
        int tipo = sc.nextInt(); 
        sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Año: ");
        int anio = sc.nextInt(); 
        sc.nextLine();

        switch (tipo) {
            case 1 -> agregarPelicula(titulo, anio);
            case 2 -> agregarSerieTV(titulo, anio);
            case 3 -> agregarDocumental(titulo, anio);
            default -> System.out.println("❌ Tipo inválido.");
        }
    }
    
    /**
     * Genera un identificador único incremental para los contenidos.
     * 
     * @return Un ID entero único.
     */
    private int generarIdContenido() {
        return controller.listarContenidos().size() + 1;
    }

    /**
     * Agrega una nueva película con sus actores asociados.
     * 
     * @param titulo Título de la película.
     * @param anio Año de estreno de la película.
     */
    private void agregarPelicula(String titulo, int anio) {
        System.out.print("Director: ");
        String director = sc.nextLine();

        int id = generarIdContenido();
        Pelicula pelicula = new Pelicula(id, titulo, anio, director);

        // Agregar actores
        System.out.print("¿Cuántos actores quieres agregar? ");
        int nActores = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < nActores; i++) {
            System.out.println("Actor " + (i + 1) + ":");
            System.out.print("   Nombre: ");
            String nombreActor = sc.nextLine();
            System.out.print("   Personaje más famoso: ");
            String personaje = sc.nextLine();
            System.out.print("   Premios ganados: ");
            int premios = sc.nextInt(); sc.nextLine();

            Actor actor = new Actor(id, nombreActor, personaje, premios);
            pelicula.agregarActor(actor);
            controller.agregarActor(actor);
        }

        controller.agregarContenido(pelicula);
        guardarDatos();
        System.out.println("✅ Película agregada correctamente.");
    }

    /**
     * Agrega una nueva serie de TV con sus temporadas.
     * 
     * @param titulo Título de la serie.
     * @param anio Año de estreno de la serie.
     */
    private void agregarSerieTV(String titulo, int anio) {
        int id = generarIdContenido();
        SerieTV serie = new SerieTV(id, titulo, anio);

        // Agregar temporadas
        System.out.print("¿Cuántas temporadas quieres agregar? ");
        int nTemporadas = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < nTemporadas; i++) {
            System.out.println("Temporada " + (i + 1) + ":");
            System.out.print("   Número: ");
            int numero = sc.nextInt();
            System.out.print("   Cantidad de episodios: ");
            int episodios = sc.nextInt(); sc.nextLine();

            Temporada temporada = new Temporada(id, numero, episodios);
            serie.agregarTemporada(temporada);
            controller.agregarTemporada(temporada);
        }

        controller.agregarContenido(serie);
        guardarDatos();
        System.out.println("✅ Serie de TV agregada correctamente.");
    }

    /**
     * Agrega un nuevo documental con sus investigadores asociados.
     * 
     * @param titulo Título del documental.
     * @param anio Año de estreno del documental.
     */
    private void agregarDocumental(String titulo, int anio) {
        System.out.print("Tema del documental: ");
        String tema = sc.nextLine();
        int id = generarIdContenido();
        Documental documental = new Documental(id, titulo, anio, tema);

        // Agregar investigadores
        System.out.print("¿Cuántos investigadores quieres agregar? ");
        int nInvestigadores = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < nInvestigadores; i++) {
            System.out.println("Investigador " + (i + 1) + ":");
            System.out.print("   Nombre: ");
            String nombreInvestigador = sc.nextLine();
            System.out.print("   Especialidad: ");
            String especialidad = sc.nextLine();
            System.out.print("   Institución: ");
            String institucion = sc.nextLine();

            Investigador investigador = new Investigador(id, nombreInvestigador, especialidad, institucion);
            documental.agregarInvestigador(investigador);
            controller.agregarInvestigador(investigador);
        }

        controller.agregarContenido(documental);
        guardarDatos();
        System.out.println("✅ Documental agregado correctamente.");
    }

    /**
     * Carga los datos desde los archivos CSV usando el {@link ContenidoController}.
     */
    private void cargarDatos() {
        try {
            controller.cargarDatos();
            System.out.println("✅ Datos cargados correctamente desde los archivos CSV.");
        } catch (IOException e) {
            System.out.println("❌ Error al cargar los datos: " + e.getMessage());
        }
    }
    
    /**
     * Guarda los datos actuales en los archivos CSV usando el {@link ContenidoController}.
     */
    private void guardarDatos() {
        try {
            controller.guardarDatos();
            System.out.println("✅ Datos guardados correctamente en contenidos.csv.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar los datos: " + e.getMessage());
        }
    }
}
