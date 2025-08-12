package servicio;

import modelo.*;

import java.util.ArrayList;
import java.util.List;

public class Feria {
    private List<Empresa> empresas = new ArrayList<>();
    private List<Stand> stands = new ArrayList<>();
    private List<Visitante> visitantes = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();

    // ======== MÉTODOS DE REGISTRO ========

    // Empresas
    public void registrarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    // Stands
    public void registrarStand(Stand stand) {
        stands.add(stand);
    }

    public List<Stand> getStands() {
        return stands;
    }

    // Visitantes
    public void registrarVisitante(Visitante visitante) {
        visitantes.add(visitante);
    }

    public List<Visitante> getVisitantes() {
        return visitantes;
    }

    // Comentarios
    public void registrarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public List<Comentario> getComentariosPorStand(Stand stand) {
        List<Comentario> lista = new ArrayList<>();
        for (Comentario c : comentarios) {
            if (c.getStand().equals(stand)) {
                lista.add(c);
            }
        }
        return lista;
    }

    // ======== REPORTES ========

    // Reporte 1: Empresas con sus stands asignados
    public void generarReporteEmpresasConStand() {
        System.out.println("\n=== REPORTE DE EMPRESAS Y STANDS ===");
        for (Stand stand : stands) {
            if (stand.estaOcupado()) {
                System.out.println(stand.getEmpresa().getNombre() + " -> " + stand);
            }
        }
    }

    // Reporte 2: Visitantes y stands visitados
    public void generarReporteVisitantesYVisitas() {
        System.out.println("\n=== REPORTE DE VISITANTES Y STANDS VISITADOS ===");
        for (Visitante visitante : visitantes) {
            System.out.println("Visitante: " + visitante.getNombre());
            boolean tieneVisitas = false;
            for (Comentario c : comentarios) {
                if (c.getVisitante().equals(visitante)) {
                    System.out.println("   - " + c.getStand() + " | " + c.getTexto());
                    tieneVisitas = true;
                }
            }
            if (!tieneVisitas) {
                System.out.println("   (No ha visitado stands)");
            }
        }
    }

    // Reporte 3: Calificación promedio de cada stand
    public void generarReportePromedioPorStand() {
        System.out.println("\n=== REPORTE DE CALIFICACIÓN PROMEDIO POR STAND ===");
        for (Stand stand : stands) {
            List<Comentario> lista = getComentariosPorStand(stand);
            if (!lista.isEmpty()) {
                double suma = 0;
                for (Comentario c : lista) {
                    suma += c.getCalificacion();
                }
                double promedio = suma / lista.size();
                System.out.println(stand + " -> Promedio: " + String.format("%.2f", promedio) + " estrellas");
            } else {
                System.out.println(stand + " -> Sin calificaciones");
            }
        }
    }
}
