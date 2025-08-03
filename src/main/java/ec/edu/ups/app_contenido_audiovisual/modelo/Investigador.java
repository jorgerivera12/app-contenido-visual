package ec.edu.ups.app_contenido_audiovisual.modelo;

public class Investigador extends Persona {
    private int contenidoId; 
    private String especialidad;
    private String institucion;

    public Investigador(int contenidoId,String nombre, String especialidad, String institucion) {
        super(nombre);
        this.contenidoId = contenidoId;	
        this.especialidad = especialidad;
        this.institucion = institucion;
    }

	public int getContenidoId() {
		return contenidoId;
	}

	public void setContenidoId(int contenidoId) {
		this.contenidoId = contenidoId;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String toCSV() {
        return contenidoId + "," + getNombre() + "," + especialidad + "," + institucion;
    }
}