package tppprogradef;

public class Aeropuerto {
	protected String nombre;
	protected String pais;
	protected String provincia;
	protected String direccion;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
		this.nombre=nombre;
		this.pais=pais;
		this.provincia=provincia;
		this.direccion=direccion;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();	
	    sb.append(this.nombre)  
	      .append(" - ")
	      .append(this.pais)
	      .append(" - ")
	      .append(this.provincia)
	      .append(" - ")
	      .append(this.direccion);    
	    return sb.toString();
	}
}