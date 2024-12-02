package Ticketera;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private boolean activo;

    public Evento(int id, String nombre, String descripcion, String fecha, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public String toString() {
        return id + ": " + nombre + " - " + descripcion + " (Fecha: " + fecha + ")";
    }
}