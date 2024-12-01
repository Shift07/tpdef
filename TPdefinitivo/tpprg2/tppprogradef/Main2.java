package tppprogradef;

public class Main2 {
    public static void main(String[] args) {
        // Crear instancia de la aerolínea
        Aerolinea aerolinea = new Aerolinea("BondiJet", "123456789");

        // Registrar aeropuertos válidos
        aerolinea.registrarAeropuerto("Aeroparque", "Argentina", "Buenos Aires", "Avenida Costanera");
        aerolinea.registrarAeropuerto("Bariloche", "Argentina", "Río Negro", "Ruta 40");

        // Test 1: Destino no registrado
        try {
            System.out.println("Prueba 1: Intentar registrar un vuelo con destino no registrado.");
            double[] precios = { 5000.0, 10000.0 };
            int[] cantAsientos = { 150, 20 };

            // Aquí, el aeropuerto "Jujuy" no está registrado, debería lanzar una excepción
            aerolinea.registrarVueloPublicoNacional("Aeroparque", "Jujuy", "15/12/2024", 6, 2000, precios, cantAsientos);
        } catch (RuntimeException e) {
            System.out.println("Excepción esperada: " + e.getMessage());
        }

        // Test 2: Vuelo registrado correctamente
        try {
            System.out.println("\nPrueba 2: Registrar un vuelo con origen y destino válidos.");
            double[] precios = { 5000.0, 10000.0 };
            int[] cantAsientos = { 150, 20 };

            // Aquí, los datos son correctos, por lo que debería registrarse el vuelo sin errores
            String codVuelo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "15/12/2024", 6, 2000, precios, cantAsientos);

            System.out.println("Código del vuelo registrado: " + codVuelo);

            // Validaciones manuales para simular los asserts
            if (codVuelo != null && codVuelo.endsWith("-PUB")) {
                System.out.println("El código del vuelo es válido y termina en '-PUB'.");
            } else {
                System.out.println("Error: El código del vuelo no es válido.");
            }
        } catch (RuntimeException e) {
            System.out.println("Excepción no esperada: " + e.getMessage());
        }
    }

}
