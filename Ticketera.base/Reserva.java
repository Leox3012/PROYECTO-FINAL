package Ticketera;

public class Reserva {
    private int id;
    private int idEvento;
    private String nombreCliente;
    private String cedulaCliente;

    public Reserva(int id, int idEvento, String nombreCliente, String cedulaCliente) {
        this.id = id;
        this.idEvento = idEvento;
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
    }

    public int getId() {
        return id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }
}