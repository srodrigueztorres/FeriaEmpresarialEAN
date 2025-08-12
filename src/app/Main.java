package app;

import modelo.*;
import servicio.Feria;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Feria feria = new Feria();
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        do {
            System.out.println("\n=== MENÚ FERIA EMPRESARIAL ===");
            System.out.println("1. Registrar Empresa");
            System.out.println("2. Registrar Stand");
            System.out.println("3. Registrar Visitante");
            System.out.println("4. Mostrar Empresas");
            System.out.println("5. Mostrar Stands");
            System.out.println("6. Registrar Comentario");
            System.out.println("7. Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) continue;

            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre empresa: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Sector: ");
                    String sector = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    feria.registrarEmpresa(new Empresa(nombre, sector, email));
                    System.out.println("Empresa registrada.");
                }
                case 2 -> {
                    System.out.print("Número stand: ");
                    int num = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Ubicación: ");
                    String ubicacion = sc.nextLine().trim();
                    System.out.print("Tamaño: ");
                    String tamano = sc.nextLine().trim();
                    feria.registrarStand(new Stand(num, ubicacion, tamano));
                    System.out.println("Stand registrado.");
                }
                case 3 -> {
                    System.out.print("Nombre visitante: ");
                    String nv = sc.nextLine().trim();
                    System.out.print("ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String ev = sc.nextLine().trim();
                    feria.registrarVisitante(new Visitante(nv, id, ev));
                    System.out.println("Visitante registrado.");
                }
                case 4 -> feria.getEmpresas().forEach(System.out::println);
                case 5 -> feria.getStands().forEach(System.out::println);
                case 6 -> {
                    System.out.print("ID visitante: ");
                    String idv = sc.nextLine().trim();
                    Visitante visitante = feria.getVisitantes().stream()
                            .filter(v -> Objects.equals(v.getId(), idv))
                            .findFirst().orElse(null);
                    if (visitante == null) {
                        System.out.println("Visitante no encontrado.");
                        break;
                    }

                    System.out.print("Número stand: ");
                    int ns = Integer.parseInt(sc.nextLine().trim());
                    Stand stand = feria.getStands().stream()
                            .filter(s -> s.getNumero() == ns)
                            .findFirst().orElse(null);
                    if (stand == null) {
                        System.out.println("Stand no encontrado.");
                        break;
                    }

                    System.out.print("Calificación (1-5): ");
                    int cal = Integer.parseInt(sc.nextLine().trim());
                    if (cal < 1 || cal > 5) {
                        System.out.println("Calificación inválida.");
                        break;
                    }

                    System.out.print("Comentario: ");
                    String texto = sc.nextLine().trim();
                    feria.registrarComentario(new Comentario(visitante, LocalDate.now(), cal, texto, stand));
                    System.out.println("Comentario registrado.");
                }
                case 7 -> {
                    System.out.println("\n=== MENÚ DE REPORTES ===");
                    System.out.println("1. Empresas con stand");
                    System.out.println("2. Visitantes y stands visitados");
                    System.out.println("3. Calificación promedio por stand");
                    System.out.print("Seleccione: ");
                    int rep = Integer.parseInt(sc.nextLine().trim());
                    switch (rep) {
                        case 1 -> feria.generarReporteEmpresasConStand();
                        case 2 -> feria.generarReporteVisitantesYVisitas();
                        case 3 -> feria.generarReportePromedioPorStand();
                        default -> System.out.println("Opción inválida.");
                    }
                }
                case 0 -> System.out.println("Saliendo...");
                default -> {
                    if (opcion != 0) System.out.println("Opción inválida.");
                }
            }
        } while (opcion != 0);

        sc.close();
    }
}