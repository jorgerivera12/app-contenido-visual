package ec.edu.ups.app_contenido_audiovisual;

import ec.edu.ups.app_contenido_audiovisual.vista.Consola;

/**
 * Clase principal de la aplicación.
 * 
 * Este es el punto de entrada (main) que inicializa la vista en consola
 * y lanza el menú de interacción con el usuario.
 */
public class App {
    
    /**
     * Método main: punto de inicio de la aplicación.
     * 
     * @param args argumentos de línea de comandos (no utilizados en este caso)
     */
    public static void main(String[] args) {
        // Crear una instancia de la vista en consola
        Consola consola = new Consola();
        
        // Llamar al método que muestra el menú principal de la aplicación
        consola.mostrarMenu();
    }
}
