package ec.edu.ups.app_contenido_audiovisual.persistencia;

import java.io.*;
import java.util.*;
import ec.edu.ups.app_contenido_audiovisual.modelo.*;

public class GestorArchivos implements ArchivoCSV {

    // LECTURA de Contenidos (Películas, Series y Documentales)
    @Override
    public List<ContenidoAudiovisual> leerContenidos(String ruta) throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                int id = Integer.parseInt(datos[0]);
                String tipo = datos[1];

                switch (tipo) {
                    case "Pelicula":
                        contenidos.add(new Pelicula(id, datos[2], Integer.parseInt(datos[3]), datos[4]));
                        break;
                    case "SerieTV":
                        contenidos.add(new SerieTV(id, datos[2], Integer.parseInt(datos[3])));
                        break;
                    case "Documental":
                        contenidos.add(new Documental(id, datos[2], Integer.parseInt(datos[3]), datos[4]));
                        break;
                }
            }
        }
        return contenidos;
    }

    // LECTURA de Actores
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

    // LECTURA de Investigadores
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

    // LECTURA de Temporadas
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

    // ESCRITURA de Contenidos
    @Override
    public void escribirContenidos(String ruta, List<ContenidoAudiovisual> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (ContenidoAudiovisual c : lista) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        }
    }

    // ESCRITURA de Actores
    public void escribirActores(String ruta, List<Actor> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Actor a : lista) {
                bw.write(a.toCSV());
                bw.newLine();
            }
        }
    }

    // ESCRITURA de Investigadores
    public void escribirInvestigadores(String ruta, List<Investigador> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Investigador i : lista) {
                bw.write(i.toCSV());
                bw.newLine();
            }
        }
    }

    // ESCRITURA de Temporadas
    public void escribirTemporadas(String ruta, List<Temporada> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Temporada t : lista) {
                bw.write(t.toCSV());
                bw.newLine();
            }
        }
    }
}
