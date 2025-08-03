package ec.edu.ups.app_contenido_audiovisual.vista;

import java.io.IOException;
import java.util.Scanner;

import ec.edu.ups.app_contenido_audiovisual.controlador.ContenidoController;
import ec.edu.ups.app_contenido_audiovisual.modelo.*;

/**
 * Clase que representa la vista en consola de la aplicación.
 * Permite interactuar con el usuario a través de un menú de opciones.
 */
public class Consola {
    
    // Controlador que maneja la lógica de negocio y los datos
    private ContenidoController controller = new ContenidoController();
    
    // Objeto Scanner para leer datos ingresados por el usuario
    private Scanner sc = new Scanner(System.in);

    /**
     * Método principal de la vista que muestra el menú y procesa las opciones.
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
                case 1: listarContenidos(); break;
                case 2: agregarContenido(); break;
                case 3: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida, intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 3);
    }

    /**
     * Muestra la lista de contenidos audiovisuales con sus datos asociados.
     */
    private void listarContenidos() {
        if (controller.listarContenidos().isEmpty()) {
            System.out.println("No hay contenidos cargados.");
            return;
        }

        System.out.println("📽 Contenidos audiovisuales:");
        controller.listarContenidos().forEach(c -> {
        	// Mostrar tipo + título + año
            String tipo = c instanceof Pelicula ? "🎬 Película"
                        : c instanceof SerieTV ? "📺 SerieTV"
                        : c instanceof Documental ? "📚 Documental"
                        : "Desconocido";
            
            System.out.println("- [" + tipo + "] " + c.getTitulo() + " (" + c.getAnio() + ")");

            // Si es película, listar actores
            if (c instanceof Pelicula) {
                Pelicula p = (Pelicula) c;
                if (p.getActores().isEmpty()) {
                    System.out.println("   🎭 Actores: [Sin actores asignados]");
                } else {
                    System.out.println("   🎭 Actores:");
                    p.getActores().forEach(a -> System.out.println("     - " + a.getNombre()));
                }
            }

            // Si es serie de TV, listar temporadas
            else if (c instanceof SerieTV) {
                SerieTV s = (SerieTV) c;
                if (s.getTemporadas().isEmpty()) {
                    System.out.println("   📺 Temporadas: [Sin temporadas asignadas]");
                } else {
                    System.out.println("   📺 Temporadas:");
                    s.getTemporadas().forEach(t ->
                        System.out.println("     - Temporada " + t.getNumero() + ": " + t.getEpisodios() + " episodios")
                    );
                }
            }

            // Si es documental, listar investigadores
            else if (c instanceof Documental) {
                Documental d = (Documental) c;
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
     * Permite agregar un nuevo contenido audiovisual desde la consola.
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
    
 // Generar un ID único simple para el contenido (puedes mejorarlo)
    private int generarIdContenido() {
        return controller.listarContenidos().size() + 1;
    }

    private void agregarPelicula(String titulo, int anio) {
        System.out.print("Director: ");
        String director = sc.nextLine();

        // Crear película con ID
        int id = generarIdContenido();
        Pelicula pelicula = new Pelicula(id, titulo, anio, director);

        // Preguntar y agregar actores relacionados al ID de la película
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
            controller.agregarActor(actor); // Guardar en lista global
        }

        controller.agregarContenido(pelicula);
        guardarDatos(); // Guardar en CSVs
        System.out.println("✅ Película agregada correctamente.");
    }

    private void agregarSerieTV(String titulo, int anio) {
        int id = generarIdContenido();
        SerieTV serie = new SerieTV(id, titulo, anio);

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
            controller.agregarTemporada(temporada); // Guardar en lista global
        }

        controller.agregarContenido(serie);
        guardarDatos();
        System.out.println("✅ Serie de TV agregada correctamente.");
    }

    private void agregarDocumental(String titulo, int anio) {
        System.out.print("Tema del documental: ");
        String tema = sc.nextLine();
        int id = generarIdContenido();
        Documental documental = new Documental(id, titulo, anio, tema);

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
     * Carga los datos desde los archivos CSV.
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
     * Guarda los contenidos actuales en el archivo contenidos.csv.
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

