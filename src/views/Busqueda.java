package views;

import controllers.HuespedController;
import controllers.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloH;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;

    private HuespedController huespedController;
    private ReservaController reservaController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Busqueda frame = new Busqueda();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        this.huespedController = new HuespedController();
        this.reservaController = new ReservaController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
        modeloH = (DefaultTableModel) tbHuespedes.getModel();
        modeloH.addColumn("Numero de Huesped");
        modeloH.addColumn("Nombre");
        modeloH.addColumn("Apellido");
        modeloH.addColumn("Fecha de Nacimiento");
        modeloH.addColumn("Nacionalidad");
        modeloH.addColumn("Telefono");
        modeloH.addColumn("Numero de Reserva");

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.getSelectedIndex() == 1) {
                    buscarHuespedes();
                } else {
                    buscarReservas();
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modificar(panel);
                limpiarTabla(getTablaActual(panel));
                cargarTabla(panel);
            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminar(panel);
                limpiarTabla(getTablaActual(panel));
                cargarTabla(panel);
            }
        });
    }

    private void cargarTabla(JTabbedPane panel) {
        String nombreTabla = getNombreTablaElegida(panel);
        DefaultTableModel modelo = (DefaultTableModel) getTablaActual(panel).getModel();
        List<?> elementos;

        switch (nombreTabla) {
            case "Reservas":
                elementos = this.reservaController.listar();

                elementos.forEach(elemento -> {
                    agregarReservaATabla(modelo, (Reserva) elemento);
                });
                break;
            case "Huespedes":
                elementos = this.huespedController.listar();

                elementos.forEach(elemento ->{
                    Huesped huesped = (Huesped) elemento;

                    modelo.addRow(new Object[]{
                            huesped.getHuespedId(),
                            huesped.getNombre(),
                            huesped.getApellido(),
                            huesped.getFechaNacimiento(),
                            huesped.getNacionalidad(),
                            huesped.getTelefono(),
                            huesped.getNumeroReserva()
                    });
                });
                break;
            default:
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error. La tabla ingresada no existe.");
                throw new RuntimeException("Ha ocurrido un error. La tabla ingresada no existe.");
        }

    }

    private void agregarReservaATabla(DefaultTableModel modelo, Reserva elemento) {
        Reserva reserva = elemento;

        modelo.addRow(new Object[]{
                reserva.getReservaId(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getValor(),
                reserva.getFormaPago()
        });
    }

    private void limpiarTabla(JTable tablaActual) {
        DefaultTableModel modelo = (DefaultTableModel) tablaActual.getModel();
        modelo.getDataVector().clear();
    }

    private void modificar(JTabbedPane panel) {
        JTable tabla = getTablaActual(panel);
        String nombreTablaElegida = getNombreTablaElegida(panel);
        if (tieneFilaElegida(tabla)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        TableModel modeloTabla = tabla.getModel();
        Optional.ofNullable(modeloTabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id, filasModificadas;

                    switch (nombreTablaElegida) {
                        case "Reservas":
                            id = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                            String fechaEntrada = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 1);
                            String fechaSalida = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 2);
                            Double valor = (Double) modeloTabla.getValueAt(tabla.getSelectedRow(), 3);
                            String formaPago = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 4);

                            filasModificadas = this.reservaController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);

                            break;

                        case "Huespedes":
                            id = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                            String nombre = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 1);
                            String apellido = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 2);
                            String fechaNacimiento = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 3);
                            String nacionalidad = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 4);
                            String telefono = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 5);
                            Integer idReserva = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 6);

                            filasModificadas = this.huespedController.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Ha ocurrido un error. La tabla seleccionada no existe.");
                            throw new RuntimeException("Ha ocurrido un error. La tabla seleccionada no existe.");
                    }
                    JOptionPane.showMessageDialog(this, String.format("%d item/s modificados con éxito.", filasModificadas));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }


    private void eliminar(JTabbedPane panel) {
        JTable tabla = getTablaActual(panel);
        String nombreTablaElegida = getNombreTablaElegida(panel);
        if (tieneFilaElegida(tabla)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        TableModel modeloTabla = tabla.getModel();
        Optional.ofNullable(modeloTabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id, cantidadEliminada;
                    id = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                    switch (nombreTablaElegida) {
                        case "Reservas":
                            cantidadEliminada = this.reservaController.eliminar(id);
                            break;
                        case "Huespedes":
                            cantidadEliminada = this.huespedController.eliminar(id);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Ha ocurrido un error. No existe elemento con ese id: " + id);
                            throw new RuntimeException("Ha ocurrido un error. No existe elemento con ese id: " + id);
                    }

                    JOptionPane.showMessageDialog(this, cantidadEliminada + " item/s eliminados con éxito.");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private String getNombreTablaElegida(JTabbedPane panel) {
        String tablaElegida = null;
        switch (panel.getSelectedIndex()) {
            case 0:
                tablaElegida = "Reservas";
                break;
            case 1:
                tablaElegida = "Huespedes";
                break;
            default:
                break;
        }
        return tablaElegida;
    }

    private JTable getTablaActual(JTabbedPane panel) {
        return (JTable) panel.getSelectedComponent();
    }

    private boolean tieneFilaElegida(JTable tabla) {
        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
    }

    private void buscarReservas() {
        modelo.setRowCount(0);

        String busqueda = txtBuscar.getText();
        List<Reserva> listaReservas = null;

        if (busqueda != null && !busqueda.equals("")) {
            try {
                int reservaId = Integer.parseInt(busqueda);
                listaReservas = reservaController.listar(reservaId);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido." +
                        " La búsqueda de reservas solo puede hacerse por número de reserva.");
            }
        } else {
            listaReservas = reservaController.listar();
        }

        if (listaReservas != null && listaReservas.size() > 0) {
            listaReservas.forEach(this::agregarNuevaColumnaReservas);
        } else {
            JOptionPane.showMessageDialog(null, "No se hallaron resultados para: " + busqueda);
        }
    }

    private void agregarNuevaColumnaReservas(Reserva reserva) {
        modelo.addRow(
                new Object[]{
                        reserva.getReservaId(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        reserva.getValor(),
                        reserva.getFormaPago()
                }
        );
    }

    private void buscarHuespedes() {
        modeloH.setRowCount(0);

        String busqueda = txtBuscar.getText();
        List<Huesped> listaHuespedes;

        if (busqueda != null && !busqueda.equals("")) {
            listaHuespedes = huespedController.listar(busqueda);
        } else {
            listaHuespedes = huespedController.listar();
        }

        if (listaHuespedes != null && listaHuespedes.size() > 0) {
            listaHuespedes.forEach(this::agregarNuevaColumnaHuesped);
        } else {
            JOptionPane.showMessageDialog(null, "No se hallaron resultados para: " + busqueda);
        }
    }

    private void agregarNuevaColumnaHuesped(Huesped huesped) {
        modeloH.addRow(
                new Object[]{
                        huesped.getHuespedId(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getFechaNacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getNumeroReserva()
                }
        );
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
