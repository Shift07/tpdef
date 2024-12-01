package tppprogradef;

import java.util.Map;

public class Main3 {
	public static void main(String[] args) {
        // Creación de la aerolínea
        Aerolinea aerolinea = new Aerolinea("BondiJet", "30-12345678-9");

        // Definir precios y cantidad de asientos para cada clase
        double[] precios = { 5000.0, 10000.0 }; // Precios para clases Turista y Ejecutiva
        int[] cantAsientos = { 150, 20 }; // Cantidad de asientos para cada clase (Turista, Ejecutiva)

        // Registro de un vuelo público nacional
        String codVuelo = aerolinea.registrarVueloPublicoNacional(
                "Aeroparque", "Bariloche", "15/01/2025", 5, 2500, precios, cantAsientos);
        
        // Mostrar el código del vuelo registrado
        System.out.println("Vuelo Nacional registrado con código: " + codVuelo);

        // Obtener los asientos disponibles para el vuelo registrado
        Map<Integer, String> asientosDisponibles = aerolinea.asientosDisponibles(codVuelo);
        
        // Mostrar la cantidad de asientos disponibles
        System.out.println("Cantidad de asientos disponibles: " + asientosDisponibles.size());

        // Mostrar algunos asientos disponibles como ejemplo (el primer asiento)
        System.out.println("Ejemplo de asiento disponible: ");
        asientosDisponibles.forEach((numeroAsiento, seccion) -> {
            if (numeroAsiento == 1) { // Mostrar el primer asiento
                System.out.println("Asiento número: " + numeroAsiento + ", Sección: " + seccion);
            }
        });
    }

}
