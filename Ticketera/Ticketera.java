package Ticketera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ticketera {
    private Connection connection;

    // Constructor: Conexión a la base de datos
    public Ticketera() {
        try {
            String url = "jdbc:postgresql://localhost:5432/ticketera";
            String user = "LeoValencia123";
            String password = "Leovalsan3012";
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }

    // Método principal para iniciar el programa
    public void iniciar() {
        JOptionPane.showMessageDialog(null, "!Bienvenidos a LVSTicket¡ ¿Qué desearía hacer?");
        menuOpciones();
    }

    // Menú de opciones principal
    public void menuOpciones() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                "Seleccione una opción:\n" +
                "1: Ver eventos activos\n" +
                "2: Reservar un evento\n" +
                "3: Consultar estado de compra\n" +
                "4: Salir"
            );
            
            switch (opcion) {
                case "1":
                    mostrarEventosActivos();
                    break;
                case "2":
                    realizarReserva();
                    break;
                case "3":
                    consultarReservasPorCedula();
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Gracias por usar LVTicket. ¡Hasta luego!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtelo de nuevo.");
            }
        } while (!"4".equals(opcion)); // Sale del menú solo si elige "4"
    }

    // Mostrar los eventos activos
    private void mostrarEventosActivos() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM eventos WHERE activo = TRUE");

            StringBuilder eventos = new StringBuilder("Eventos Activos:\n");
            while (rs.next()) {
                eventos.append("ID: ").append(rs.getInt("id_evento"))
                        .append(" | Evento: ").append(rs.getString("nombre_evento"))
                        .append("\nDescripción: ").append(rs.getString("descripcion"))
                        .append("\n-----------------------------------\n");
            }

            if (eventos.length() > "Eventos Activos:\n".length()) {
                JOptionPane.showMessageDialog(null, eventos.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay eventos activos actualmente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los eventos: " + e.getMessage());
        }
    }

    // Realizar una reserva
    private void realizarReserva() {
        try {
            String idEventoStr = JOptionPane.showInputDialog("Ingrese el ID del evento:");
            int idEvento = Integer.parseInt(idEventoStr);
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");

            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO reservas (id_evento, nombre_cliente, cedula_cliente) VALUES (?, ?, ?)"
            );
            pstmt.setInt(1, idEvento);
            pstmt.setString(2, nombre);
            pstmt.setString(3, cedula);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Reserva realizada con éxito.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del evento debe ser un número válido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la reserva: " + e.getMessage());
        }
    }

    // Consultar reservas por cédula
    private void consultarReservasPorCedula() {
        try {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT eventos.nombre_evento, eventos.fecha_evento " +
                "FROM reservas " +
                "INNER JOIN eventos ON reservas.id_evento = eventos.id_evento " +
                "WHERE reservas.cedula_cliente = ?"
            );
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            StringBuilder reservas = new StringBuilder("Reservas:\n");
            while (rs.next()) {
                reservas.append("Evento: ").append(rs.getString("nombre_evento"))
                        .append(" | Fecha: ").append(rs.getDate("fecha_evento"))
                        .append("\n-----------------------------------\n");
            }

            if (reservas.length() > "Reservas:\n".length()) {
                JOptionPane.showMessageDialog(null, reservas.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron reservas para la cédula ingresada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar las reservas: " + e.getMessage());
        }
    }
}