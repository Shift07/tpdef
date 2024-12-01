package tppprogradef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import tpprg2.Asiento;
import tpprg2.Vuelo;

import java.util.Date;

public class Aerolinea implements IAerolinea {
	private String nombre;
	private String cuit;
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private HashMap<String, Aeropuerto> aeropuertos = new HashMap<>();
	private HashMap<String, Vuelo> vuelos = new HashMap<>();
	private HashMap<String,Double> totalRecaudadoPorDestino = new HashMap<>();
    private int contadorVuelosPrivados = 1;
    private int codigoPasajeIncremental = 1;
	private int asiento;
    private static int contadorVuelos = 1;
    private static int contadorVuelosPublicos = 1;
    
    
    
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit=cuit;
		this.vuelos = vuelos;
	}
	public String toString() {
	    StringBuilder sb = new StringBuilder();	
	    
	    sb.append(nombre)  
	      .append(" - ")
	      .append(cuit);   
	    return sb.toString();
	}
	
	@Override

	public void registrarCliente(int dni, String nombre, String telefono) {
		if (this.clientes.containsKey(dni))
			throw new RuntimeException("El cliente ya está registrado");
		clientes.put(dni, new Cliente(dni,nombre,telefono));
	}
	
	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(this.aeropuertos.containsKey(nombre))
			throw new RuntimeException("Aeropuerto ya registrado");
		aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
}
	
	
	
	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
	        double valorRefrigerio, double[] precios, int[] cantAsientos) {

	    Aeropuerto origenAeropuerto = aeropuertos.get(origen);
	    Aeropuerto destinoAeropuerto = aeropuertos.get(destino);
	  
	    if(!aeropuertos.containsKey(origen) || !aeropuertos.containsKey(destino))
	    	throw new RuntimeException("Origen o destino no registrado en la aerolínea.");
	    if (origenAeropuerto == null || destinoAeropuerto == null) {
	        throw new RuntimeException("Origen o destino no registrado en la aerolínea.");
	    }

	    if (!"Argentina".equals(origenAeropuerto.pais) && !"Argentina".equals(destinoAeropuerto.pais)) {
	        throw new RuntimeException("Origen o destino no está en Argentina.");
	    }

	    // Validar parámetros de entrada
	    if (precios == null || (precios.length != 2 || precios.length > 2)) {
	        throw new RuntimeException("El array de precios debe contener exactamente dos elementos.");
	    }
	    if (cantAsientos == null || (cantAsientos.length != 2 || cantAsientos.length > 2)) {
	        throw new RuntimeException("El array de cantidad de asientos debe contener exactamente dos elementos.");
	    }

	    // Crear el vuelo nacional
	    VueloNacional vuelo = new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);

	    // Generar código de vuelo único
	    String codigoVuelo = contadorVuelosPublicos++ + "-PUB";
	    vuelo.setCodigo(codigoVuelo); //agrega codigo
	    
	    //inicializo asientos
	    vuelo.inicializarAsientos(cantAsientos);
	    // Registrar el vuelo en la aerolínea
	    vuelos.put(codigoVuelo, vuelo);

	    return codigoVuelo;
	}
	
		
	
	@Override
		public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
				double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		int totalAsientos=1;
		for (int cant : cantAsientos) {
	        totalAsientos += cant;
	    }
	    if (totalAsientos < 141) { 
	        throw new IllegalStateException("La capacidad total del vuelo debe ser al menos 140 asientos.");
	    }	
		
		if (precios == null || precios.length != 3) {
	        throw new RuntimeException("El array de precios debe contener exactamente tres elementos.");
	    }
	    if (cantAsientos == null || cantAsientos.length != 3) {
	        throw new RuntimeException("El array de cantidad de asientos debe contener exactamente tres elementos.");
	    }
	    if (escalas == null) {
	        throw new RuntimeException("El array de escalas no puede ser nulo. Use un array vacío si no tiene escalas.");
	    }
	    if (tripulantes <= 0) {
	        throw new RuntimeException("La cantidad de tripulantes debe ser mayor a 0.");
	    }
	    if (valorRefrigerio < 0 || cantRefrigerios < 0) {
	        throw new RuntimeException("El valor o cantidad de refrigerios no puede ser negativo.");
	    }

	    // Validar aeropuertos
	    Aeropuerto origenAeropuerto = aeropuertos.get(origen);
	    Aeropuerto destinoAeropuerto = aeropuertos.get(destino);

	    if (origenAeropuerto == null || destinoAeropuerto == null) {
	        throw new RuntimeException("Origen o destino no registrado en la aerolínea.");
	    }

	    // Validar que la fecha sea posterior a la actual
//	    validarFecha(fecha);

	    // Crear el vuelo
	    VueloInternacional vuelo = new VueloInternacional(origen, destino, fecha, tripulantes, valorRefrigerio, cantRefrigerios, precios, cantAsientos, escalas);

	    // Generar código de vuelo
	    String codigoVuelo = contadorVuelosPublicos++ + "-PUB";
	    vuelo.setCodigoVuelo(codigoVuelo);
	    
	    //inicializo asientos
	    vuelo.inicializarAsientos(cantAsientos);
	    

	    // Registrar el vuelo
	    vuelos.put(codigoVuelo, vuelo);

	    return codigoVuelo;
	}
	
//	private void validarFecha(String fecha) {
//	    // Validar formato y fecha futura
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	    LocalDate fechaVuelo;
//
//	    try {
//	        fechaVuelo = LocalDate.parse(fecha, formatter);
//	    } catch (DateTimeParseException e) {
//	        throw new RuntimeException("El formato de la fecha debe ser 'dd/MM/yyyy'.", e);
//	    }
//
//	    if (fechaVuelo.isAfter(LocalDate.now())) {
//	        throw new RuntimeException("La fecha del vuelo debe ser posterior a la actual.");
//	    }
//	}
	
	
	
	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaVuelo;
	    
	    try {
	        fechaVuelo = sdf.parse(fecha);
	        
	    } catch (ParseException e) {
	        throw new RuntimeException("Formato de fecha inválido. Use dd/MM/yyyy.");
	    }

	    // 2. Obtener la fecha actual
//	    Date fechaActual = new Date();
//
//	    // 3. Comparar la fecha del vuelo con la fecha actual
//	    if (fechaVuelo.before(fechaActual)) {
//	        throw new RuntimeException("La fecha de vuelo caducó. No se puede vender un vuelo en una fecha anterior.");
//	    }
	    
	    Cliente comprador = clientes.get(dniComprador);
	    if (comprador == null) {
	        throw new RuntimeException("El cliente comprador no está registrado.");
	    }
	    
	    
	    // 3. Verificar si los acompañantes están registrados
	    for (int dni : acompaniantes) {
	        if (clientes.get(dni) == null) {
	            throw new RuntimeException("Uno o más acompañantes no están registrados.");
	        }
	    }
	    
	 // 4. Generar un código de vuelo único que termine en "-PRI"
	    String codigoVuelo =  contadorVuelosPrivados++ + "-PRI";
	    
	    // 5. Crear y registrar el vuelo privado
	    VueloPrivado vueloPrivado = new VueloPrivado(origen, destino, fecha, tripulantes, precio, dniComprador, acompaniantes);
	    vueloPrivado.setCodigoVuelo(codigoVuelo); //agrego el codigo
	    vuelos.put(codigoVuelo, vueloPrivado);

	    // 6. Retornar el código del vuelo
	    return codigoVuelo;
	}
	
	
	
	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		 // Verificar que el vuelo exista
	    if (!vuelos.containsKey(codVuelo)) {
	        throw new RuntimeException("El vuelo no existe.");
	    }

	    // Obtener el vuelo
	    Vuelo vuelo = vuelos.get(codVuelo);

	    // Crear el mapa de asientos disponibles
	    Map<Integer, String> asientosDisponibles = new HashMap<>();

	    // Recorrer los asientos del vuelo
	    for (Asiento asiento : vuelo.asientos) {
	        if (asiento.isDisponible()) { // Verificar si el asiento está disponible
	            asientosDisponibles.put(asiento.obtenerNumero(), asiento.obtenerSeccion());
	        }
	    }

	    return asientosDisponibles;
	}
	
	 // Método para buscar un cliente por su DNI sin usar get directamente
    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.coincideDni(dni)) {
                return cliente;
            }
        }
        throw new RuntimeException("El cliente no está registrado.");
    }
	
	
	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		 Cliente cliente = buscarCliente(dni);
		 
		 if (!clientes.containsKey(dni)) {
		      throw new IllegalArgumentException("El cliente con DNI " + dni + " no está registrado.");
		    }
		 
		    // Verificar si el vuelo existe
		 Vuelo vuelo = vuelos.get(codVuelo);
		 if (!vuelos.containsKey(codVuelo)) {
		     throw new IllegalArgumentException("El vuelo con código " + codVuelo + " no existe.");
		    }

//		 if (!vuelo.asientoEstaDisponible(nroAsiento)) {
//		        throw new IllegalStateException("El asiento número " + nroAsiento + " ya está ocupado.");
//		    }

		    // Si aOcupar es true, registrar al cliente como pasajero del vuelo
		 if (aOcupar) {
			 vuelo.registrarPasajero(cliente.dni, cliente.nombre); // Agrega al pasajero al vuelo
			}

		    // Ocupar el asiento en el vuelo
		    vuelo.ocuparAsiento(nroAsiento);

		    // Generar y devolver un código único para el pasaje
		    int codigoPasaje = codigoPasajeIncremental++;
		    return codigoPasaje;
		}
	    
	    
	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		List<String> vuelosSimilares = new ArrayList<>();

	    // Parsear la fecha de entrada
	    LocalDate fechaInicio = LocalDate.parse(Fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	    // Fecha límite de una semana después
	    LocalDate fechaFin = fechaInicio.plusDays(7);

	    // Recorrer todos los vuelos registrados en la aerolínea
	    for (Vuelo vuelo : vuelos.values()) {
	        // Parsear la fecha del vuelo
	        LocalDate fechaVuelo = LocalDate.parse(vuelo.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	        // Verificar origen, destino y si la fecha está en el rango
	        if (vuelo.origen.equals(origen) &&
	            vuelo.destino.equals(destino) &&
	            !fechaVuelo.isBefore(fechaInicio) &&
	            !fechaVuelo.isAfter(fechaFin)) {
	            vuelosSimilares.add(vuelo.codigo);
	        }
	    }

	    return vuelosSimilares;
	}
	
	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
			// Verificar si el vuelo existe
		    if (!vuelos.containsKey(codVuelo)) {
		        throw new RuntimeException("El vuelo no existe.");
		    }
		    // obtener el vuelo
		    Vuelo vuelo = vuelos.get(codVuelo);
		    // verificar que el asiento sea válido
		    if (nroAsiento < 1 || nroAsiento >= 141) {
		        throw new RuntimeException("Número de asiento inválido.");
		    }
		    // obtener el asiento
		    Asiento asiento = vuelo.asientos.get(nroAsiento);

		    // verificar que el asiento está ocupado por el cliente
		    if (vuelo.asientosOcupados.containsKey(dni)) {
		        throw new RuntimeException("El cliente no tiene este asiento reservado.");
		    }

		    // liberar el asiento
		    asiento.liberar(); // método en la clase Asiento para marcarlo como disponible

		    // Remover del mapa de asientos ocupados
		    vuelo.asientosOcupados.remove(dni);
		}

	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public List<String> cancelarVuelo(String codigoVuelo) {
	    List<String> pasajerosReprogramados = new ArrayList<>();
	    List<String> pasajerosCancelados = new ArrayList<>();
	    
	    // Obtener el vuelo a cancelar
	    Vuelo vueloCancelado = vuelos.get(codigoVuelo);
	    //
	    if (vueloCancelado != null) {
	        for (Pasajero pasajero : vueloCancelado.ObtenerPasajeross()) { // <- Asegúrate de que esta lista no sea nula
	            boolean reprogramado = false;
	            for (Vuelo vuelo : vuelos.values()) {
	                if (!vuelo.codigo.equals(codigoVuelo) && vuelo.destino.equals(vueloCancelado.destino)) {
	                    pasajerosReprogramados.add(pasajero.dni + " - " + pasajero.nombre + " - " + pasajero.telefono + " - " + vuelo.obtenerCodigo());
	                    reprogramado = true;
	                    break;
	                }
	            }
	            if (!reprogramado) {
	                pasajerosCancelados.add(pasajero.dni + " - " + pasajero.nombre + " - " + pasajero.telefono + " - CANCELADO");
	            }
	        }
	        vuelos.remove(codigoVuelo); // Remover el vuelo cancelado
	    }
	    // Combinar las dos listas y devolver el resultado
	    List<String> resultadoFinal = new ArrayList<>();
	    resultadoFinal.addAll(pasajerosReprogramados);
	    resultadoFinal.addAll(pasajerosCancelados);
	    return resultadoFinal;
	}

    // Método auxiliar para verificar si un pasajero puede ser reprogramado en un asiento
    private boolean puedeReprogramarse(Pasajero pasajero, Asiento asiento) {
        // Verificar si el asiento tiene la misma sección o una mejor
        return pasajero.obtenerSeccion().equals(asiento.getSeccion()) || esSeccionSuperior(pasajero.obtenerSeccion(), asiento.getSeccion());
    }

    // Método para verificar si una sección es superior a otra
    private boolean esSeccionSuperior(String seccionPasajero, String seccionAsiento) {
        List<String> secciones = Arrays.asList("Turista", "Ejecutiva", "Primera");
        return secciones.indexOf(seccionAsiento) > secciones.indexOf(seccionPasajero);
    }
	
	
	
	@Override
	public double totalRecaudado(String destino) {
        double total = 0.0;      
        // Recorrer todos los vuelos y sumar la recaudación si el destino coincide
        for (Vuelo vuelo : vuelos.values()) {
            if (vuelo.destino.equals(destino)) {
                total += vuelo.calcularRecaudacion();
            }
        }
        return total;
    }

    
    private void actualizarRecaudacion(String destino, double monto) {
        totalRecaudadoPorDestino.put(destino, totalRecaudadoPorDestino.getOrDefault(destino, 0.0) + monto);
    }
	
	
	@Override
	public String detalleDeVuelo(String codVuelo) {
		 if (!vuelos.containsKey(codVuelo)) {
		        throw new IllegalArgumentException("Código de vuelo no existe: " + codVuelo);
		    }
		 return vuelos.get(codVuelo).toString();
	}
}


