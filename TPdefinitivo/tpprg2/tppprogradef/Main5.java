package tppprogradef;

import java.util.List;

public class Main5 {
	 public static void main(String[] args) {
	        // Crear la aerolínea
	        Aerolinea aerolinea = new Aerolinea("Bondilet", "30-12345678-9");

	        // Registrar algunos aeropuertos
	        aerolinea.registrarAeropuerto("Aeroparque", "Argentina", "Ciudad de Buenos Aires", "Avenida del Libertador 4500");
	        aerolinea.registrarAeropuerto("Bariloche", "Argentina", "Río Negro", "Calle Ficticia 123");

	        // Crear precios y cantidades de asientos para el vuelo
	        double[] precios = {5000.0, 10000.0};
	        int[] cantAsientos = {150, 20};

	        // Registrar un vuelo público nacional
	        String codVuelo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "15/12/2024", 5, 3000, precios, cantAsientos);

	        // Ahora, probamos el método consultarVuelosSimilares con una fecha dentro del rango
	        System.out.println("=== Consulta vuelos similares (dentro de una semana desde el 10/12/2024) ===");
	        List<String> vuelosSimilares = aerolinea.consultarVuelosSimilares("Aeroparque", "Bariloche", "10/12/2024");

	        // Mostrar resultados
	        if (vuelosSimilares.isEmpty()) {
	            System.out.println("No se encontraron vuelos similares.");
	        } else {
	            System.out.println("Vuelos similares encontrados: ");
	            for (String vuelo : vuelosSimilares) {
	                System.out.println(vuelo);
	            }
	        }

	        // Probar una fecha que esté fuera del rango de una semana
	        System.out.println("\n=== Consulta vuelos similares (dentro de una semana desde el 20/12/2024) ===");
	        vuelosSimilares = aerolinea.consultarVuelosSimilares("Aeroparque", "Bariloche", "20/12/2024");

	        // Mostrar resultados
	        if (vuelosSimilares.isEmpty()) {
	            System.out.println("No se encontraron vuelos similares.");
	        } else {
	            System.out.println("Vuelos similares encontrados: ");
	            for (String vuelo : vuelosSimilares) {
	                System.out.println(vuelo);
	            }
	        }
	    }
	}


