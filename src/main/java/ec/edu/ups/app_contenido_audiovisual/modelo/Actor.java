package ec.edu.ups.app_contenido_audiovisual.modelo;

public class Actor extends Persona {
    private int contenidoId; 
    private String personajeMasFamoso;
    private int premiosGanados;

    public Actor(int contenidoId,String nombre, String personajeMasFamoso, int premiosGanados) {
        super(nombre);
        this.contenidoId = contenidoId;	
        this.personajeMasFamoso = personajeMasFamoso;
        this.premiosGanados = premiosGanados;
    }

	public int getContenidoId() {
		return contenidoId;
	}

	public void setContenidoId(int contenidoId) {
		this.contenidoId = contenidoId;
	}

	public String getPersonajeMasFamoso() {
		return personajeMasFamoso;
	}

	public void setPersonajeMasFamoso(String personajeMasFamoso) {
		this.personajeMasFamoso = personajeMasFamoso;
	}

	public int getPremiosGanados() {
		return premiosGanados;
	}

	public void setPremiosGanados(int premiosGanados) {
		this.premiosGanados = premiosGanados;
	}

	public String toCSV() {
        return contenidoId + "," + getNombre() + "," + personajeMasFamoso + "," + premiosGanados;
    }
}
