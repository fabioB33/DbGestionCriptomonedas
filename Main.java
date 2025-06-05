package org.example;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuario = new Usuario("admin", "1234");
    private static List<Billetera> billeteras = new ArrayList<>();

    public static void main(String[] args) {
        if (login()) {
            menuPrincipal();
        } else {
            System.out.println("Acceso denegado.");
        }
    }

    private static boolean login() {
        System.out.print("Ingrese usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String pass = scanner.nextLine();
        return usuario.getNombre().equals(nombre) && usuario.validarPassword(pass);
    }

    private static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear Billetera");
            System.out.println("2. Ver Billeteras");
            System.out.println("3. Enviar Cripto");
            System.out.println("4. Recibir Cripto");
            System.out.println("5. Buscar cotización de una criptomoneda en particular");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: crearBilletera(); break;
                    case 2: verBilleteras(); break;
                    case 3: operarCripto(true); break;
                    case 4: operarCripto(false); break;
                    case 5: System.out.print("Ingrese el ID de la criptomoneda (ej: bitcoin, ethereum, litecoin): ");
                    String criptoId = scanner.nextLine().toLowerCase();
                    Cotizacion.consultarCotizacion(criptoId);
                    break;
                    case 6: System.out.println("Saliendo del sistema."); break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = 0;
            }
        } while (opcion != 6);
    }

    private static void crearBilletera() {
        System.out.print("Ingrese alias de la billetera: ");
        String alias = scanner.nextLine();
        billeteras.add(new Billetera(alias));
        System.out.println("Billetera creada con éxito.");
    }

    private static void verBilleteras() {
        if (billeteras.isEmpty()) {
            System.out.println("No hay billeteras registradas.");
        } else {
            for (Billetera b : billeteras) {
                b.mostrarSaldo();
            }
        }
    }

    private static void operarCripto(boolean esEnvio) {
        System.out.print("Ingrese alias de la billetera: ");
        String alias = scanner.nextLine();
        Billetera billetera = buscarBilletera(alias);
        if (billetera != null) {
            System.out.print("Ingrese tipo de criptomoneda: ");
            String cripto = scanner.nextLine();
            System.out.print("Ingrese monto: ");
            try {
                double monto = Double.parseDouble(scanner.nextLine());
                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor que cero.");
                    return;
                }

                Transaccion operacion = esEnvio ? new TransaccionEnviar(cripto, monto)
                        : new TransaccionRecibir(cripto, monto);
                operacion.procesar(billetera);

            } catch (NumberFormatException e) {
                System.out.println("Monto inválido.");
            }
        } else {
            System.out.println("Billetera no encontrada.");
        }
    }

    private static Billetera buscarBilletera(String alias) {
        for (Billetera b : billeteras) {
            if (b.getAlias().equalsIgnoreCase(alias)) {
                return b;
            }
        }
        return null;
    }
}
