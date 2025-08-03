package ec.edu.ups.app_contenido_audiovisual.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula extends ContenidoAudiovisual {
    private String director;
    private List<Actor> actores = new ArrayList<>();

    public Pelicula(int contenidoId,String titulo, int anio, String director) {
        super(contenidoId,titulo, anio);
        this.director = director;
    }

    public void agregarActor(Actor actor) {
        actores.add(actor);
    }

    public List<Actor> getActores() {
        return actores;
    }

    
    
    public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}

	@Override
    public String toCSV() {
        return getContenidoId() + ",Pelicula," + getTitulo() + "," + getAnio() + "," + director;
    }
}