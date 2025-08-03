package ec.edu.ups.app_contenido_audiovisual.modelo;

public abstract class ContenidoAudiovisual {
	private int contenidoId;
    private String titulo;
    private int anio;

    public ContenidoAudiovisual(int contenidoId,String titulo, int anio) {
        this.contenidoId=contenidoId;
    	this.titulo = titulo;
        this.anio = anio;
    }

    public int getContenidoId() {
		return contenidoId;
	}

	public void setContenidoId(int contenidoId) {
		this.contenidoId = contenidoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public abstract String toCSV();
}