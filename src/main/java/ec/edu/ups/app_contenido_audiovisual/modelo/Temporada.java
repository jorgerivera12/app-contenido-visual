package ec.edu.ups.app_contenido_audiovisual.modelo;

public class Temporada {
    private int contenidoId; 
    private int numero;
    private int episodios;

    public Temporada(int contenidoId,int numero, int episodios) {
        this.contenidoId = contenidoId;	
        this.numero = numero;
        this.episodios = episodios;
    }

	public int getContenidoId() {
		return contenidoId;
	}

	public void setContenidoId(int contenidoId) {
		this.contenidoId = contenidoId;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEpisodios() {
		return episodios;
	}

	public void setEpisodios(int episodios) {
		this.episodios = episodios;
	}

   public String toCSV() {
        return contenidoId + "," + numero + "," + episodios;
    }

}