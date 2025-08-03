package ec.edu.ups.app_contenido_audiovisual.modelo;

import java.util.ArrayList;
import java.util.List;

public class Documental extends ContenidoAudiovisual {
    private String tema;
    private List<Investigador> investigadores = new ArrayList<>();

    public Documental(int contenidoId,String titulo, int anio, String tema) {
        super(contenidoId,titulo, anio);
        this.tema = tema;
    }

    public void agregarInvestigador(Investigador investigador) {
        investigadores.add(investigador);
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public void setInvestigadores(List<Investigador> investigadores) {
		this.investigadores = investigadores;
	}

	@Override
    public String toCSV() {
        return getContenidoId() + ",Documental," + getTitulo() + "," + getAnio() + "," + tema;
    }
}