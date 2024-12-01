package tppprogradef;

public class Pasaje {
	private Cliente cliente;
    private Vuelo vuelo;
    private String seccion; // Ej.: "Turista", "Ejecutiva", "Primera"
    private double precio;

    public Pasaje(Cliente cliente, Vuelo vuelo, String seccion, double precio) {
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.seccion = seccion;
        this.precio = precio;
    }
    
    public void obtenerVuelo(Vuelo vuelo) {
    	this.vuelo = vuelo;
    }

}
