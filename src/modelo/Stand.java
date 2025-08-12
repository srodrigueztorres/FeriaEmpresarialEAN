package modelo;

public class Stand {
    private int numero;
    private String ubicacion;
    private String tamano;
    private Empresa empresa; // null si está libre

    public Stand(int numero, String ubicacion, String tamano) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tamano = tamano;
    }

    public int getNumero() { return numero; }
    public String getUbicacion() { return ubicacion; }
    public String getTamano() { return tamano; }
    public Empresa getEmpresa() { return empresa; }

    public void asignarEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean estaOcupado() {
        return empresa != null;
    }

    @Override
    public String toString() {
        return "Stand " + numero + " - " + ubicacion + " - " + tamano +
                (empresa != null ? " [Ocupado por " + empresa.getNombre() + "]" : " [Libre]");
    }
}
