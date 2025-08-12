package modelo;

import java.time.LocalDate;

public class Comentario {
    private Visitante visitante;
    private LocalDate fecha;
    private int calificacion; // 1 a 5
    private String texto;
    private Stand stand;

    public Comentario(Visitante visitante, LocalDate fecha, int calificacion, String texto, Stand stand) {
        this.visitante = visitante;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.texto = texto;
        this.stand = stand;
    }

    public Visitante getVisitante() { return visitante; }
    public LocalDate getFecha() { return fecha; }
    public int getCalificacion() { return calificacion; }
    public String getTexto() { return texto; }
    public Stand getStand() { return stand; }

    @Override
    public String toString() {
        return "[" + fecha + "] " + visitante.getNombre() + " calificó con " +
                calificacion + " estrellas: " + texto;
    }
}
