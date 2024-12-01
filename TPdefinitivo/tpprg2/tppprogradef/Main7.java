package tppprogradef;

import java.util.List;

public class Main7 {
    public static void main(String[] args) {
        Aerolinea aerolinea = new Aerolinea("BondiJet", "123-456-789");

        // 1. Registrar un cliente.
        int dni = 12345678;
        String nombre = "Juan Perez";
        String telefono = "011-1234-5678";
        aerolinea.registrarCliente(dni, nombre, telefono);
        System.out.println("Cliente registrado: " + dni + " - " + nombre);

        // 2. Registrar el vuelo inicial.
        double[] precios = {5000.0, 10000.0};
        int[] cantAsientos = {150, 20};
        String codVuelo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "15/12/2024", 6, 2000, precios, cantAsientos);
        System.out.println("Vuelo inicial registrado: " + codVuelo);

        // 3. Vender un pasaje.
        int codPasaje = aerolinea.venderPasaje(dni, codVuelo, 1, true);
        System.out.println("Pasaje vendido: Código " + codPasaje);
        if (codPasaje <= 0) {
            System.out.println("Error: No se pudo vender el pasaje.");
            return;
        }

        // 4. Registrar un vuelo similar para reubicación.
        String codVueloNuevo = aerolinea.registrarVueloPublicoNacional("Aeroparque", "Bariloche", "16/12/2024", 6, 2000, precios, cantAsientos);
        System.out.println("Vuelo similar registrado: " + codVueloNuevo);

        // 5. Cancelar el vuelo original y verificar la reubicación.
        System.out.println("Cancelando el vuelo " + codVuelo + "...");
        List<String> resultado = aerolinea.cancelarVuelo(codVuelo);

        // 6. Mostrar los resultados.
        if (resultado == null || resultado.isEmpty()) {
            System.out.println("No se reubicó ningún pasajero.");
        } else {
            System.out.println("Resultados de la cancelación:");
            for (String registro : resultado) {
                System.out.println(registro);
            }
        }
    }
}


