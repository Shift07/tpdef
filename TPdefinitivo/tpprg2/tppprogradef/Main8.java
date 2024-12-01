package tppprogradef;

import java.util.List;

public class Main8 {
	  public static void main(String[] args) {
	        // Crear la instancia de Aerolinea
	        Aerolinea aerolinea = new Aerolinea("BondiJet", "20-12345678-9");

	        // Registrar los aeropuertos necesarios
	        aerolinea.registrarAeropuerto("Aeroparque", "Argentina", "Buenos Aires", "Av. Costanera Rafael Obligado");
	        aerolinea.registrarAeropuerto("Bariloche", "Argentina", "Río Negro", "Ruta 237 Km 1.1");

	        // Registrar un cliente
	        int dni = 12345678;
	        String nombre = "Juan Perez";
	        String telefono = "011-1234-5678";
	        aerolinea.registrarCliente(dni, nombre, telefono);

	        // Configurar los datos del vuelo
	        double[] precios = {5000.0, 10000.0};
	        int[] cantAsientos = {150, 20};

	        // Registrar el primer vuelo
	        String codVuelo = aerolinea.registrarVueloPublicoNacional(
	                "Aeroparque", "Bariloche", "15/12/2024", 6, 2000, precios, cantAsientos);

	        // Vender un pasaje al cliente en el primer vuelo
	        int codPasaje = aerolinea.venderPasaje(dni, codVuelo, 1, true);
	        System.out.println("Pasaje vendido con código: " + codPasaje);

	        // Registrar un segundo vuelo con el mismo destino
	        String codVueloNuevo = aerolinea.registrarVueloPublicoNacional(
	                "Aeroparque", "Bariloche", "16/12/2024", 6, 2000, precios, cantAsientos);

	        // Cancelar el primer vuelo y reprogramar pasajeros
	        List<String> resultado = aerolinea.cancelarVuelo(codVuelo);

	        // Imprimir el resultado
	        if (resultado != null && !resultado.isEmpty()) {
	            System.out.println("Listado de pasajeros reprogramados/cancelados:");
	            for (String registro : resultado) {
	                System.out.println(registro);
	            }
	        } else {
	            System.out.println("No se encontró ningún pasajero para reprogramar o cancelar.");
	        }
	    }
	}

