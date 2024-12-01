package tppprogradef;

import java.util.List;

public class Main6 {
	public static void main(String[] args) {
        // Crear instancia de la aerolínea
        Aerolinea aerolinea = new Aerolinea("BondiJet", "20-12345678-9");
        
        // Registrar cliente
        int dni = 12345678;
        String nombre = "Juan Perez";
        String telefono = "011-1234-5678";
        aerolinea.registrarCliente(dni, nombre, telefono);
        
        // Configuración de precios y capacidad
        double[] precios = { 5000.0, 10000.0 };
        int[] cantAsientos = { 150, 20 };
        
        // Registrar el primer vuelo
        String codVuelo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "15/12/2024", 6, 2000, precios, cantAsientos);
        System.out.println("Código del vuelo registrado: " + codVuelo);
        
        // Vender un pasaje en el primer vuelo
        int codPasaje = aerolinea.venderPasaje(dni, codVuelo, 1, true);
        if (codPasaje > 0) {
            System.out.println("Pasaje vendido con código: " + codPasaje);
        } else {
            System.out.println("Error al vender el pasaje.");
        }
        
        // Registrar un vuelo alternativo
        String codVueloNuevo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "16/12/2024", 6, 2000, precios, cantAsientos);
        System.out.println("Código del nuevo vuelo registrado: " + codVueloNuevo);
        
        // Cancelar el vuelo original
        List<String> resultado = aerolinea.cancelarVuelo(codVuelo);
        System.out.println("Resultados de la cancelación:");
        if (resultado != null && !resultado.isEmpty()) {
            for (String registro : resultado) {
                System.out.println(registro);
            }
        } else {
            System.out.println("No se encontraron pasajeros para reprogramar.");
        }
    }
}


