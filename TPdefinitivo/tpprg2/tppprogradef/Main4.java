package tppprogradef;

public class Main4 {
	public static void main(String[] args) {
        try {
            // Crear la aerolínea
            Aerolinea aerolinea = new Aerolinea("Bondilet", "30-12345678-9");

            // Prueba 1: Cliente no registrado
            System.out.println("=== Prueba 1: Cliente no registrado ===");
            try {
                double[] precios = {5000.0, 10000.0};
                int[] cantAsientos = {150, 20};
                String codVuelo = aerolinea.registrarVueloPublicoNacional(
                        "Aeroparque", "Bariloche", "15/12/2024", 5, 2000, precios, cantAsientos);
                aerolinea.venderPasaje(87654321, codVuelo, 1, true);
            } catch (RuntimeException e) {
                System.out.println("Excepción esperada: " + e.getMessage());
            }

            // Prueba 2: Cliente registrado
            System.out.println("=== Prueba 2: Cliente registrado ===");
            aerolinea.registrarCliente(12345678, "Juan Perez", "011-1234-5678");
            double[] precios = {5000.0, 10000.0};
            int[] cantAsientos = {150, 20};
            String codVuelo = aerolinea.registrarVueloPublicoNacional(
                    "Aeroparque", "Bariloche", "15/12/2024", 6, 2000, precios, cantAsientos);
            int codPasaje = aerolinea.venderPasaje(12345678, codVuelo, 1, true);
            System.out.println("Pasaje vendido con éxito. Código del pasaje: " + codPasaje);

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


