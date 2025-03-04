package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;
import controllers.HuespedController;
import modelo.Huesped;
import modelo.Reserva;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtNreserva;
    private JDateChooser txtFechaN;
    private JComboBox<Format> txtNacionalidad;
    private JLabel labelExit;
    private JLabel labelAtras;
    int xMouse, yMouse;

    private HuespedController huespedController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistroHuesped frame = new RegistroHuesped();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public RegistroHuesped() {
        establecerVentanaRegistroHuesped();
    }

    public RegistroHuesped(Reserva reserva) {
        establecerVentanaRegistroHuesped();
        txtNreserva.setText(String.valueOf(reserva.getReservaId()));
        txtNreserva.setEditable(false);
    }

    private void establecerVentanaRegistroHuesped() {
        huespedController = new HuespedController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        header.setBackground(SystemColor.text);
        header.setOpaque(false);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(new Color(12, 138, 199));
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setForeground(Color.WHITE);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);


        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNombre.setBounds(560, 135, 285, 33);
        txtNombre.setBackground(Color.WHITE);
        txtNombre.setColumns(10);
        txtNombre.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtApellido.setBounds(560, 204, 285, 33);
        txtApellido.setColumns(10);
        txtApellido.setBackground(Color.WHITE);
        txtApellido.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtApellido);

        txtFechaN = new JDateChooser();
        txtFechaN.setBounds(560, 278, 285, 36);
        txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
        txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtFechaN.setDateFormatString("yyyy-MM-dd");
        contentPane.add(txtFechaN);

        txtNacionalidad = new JComboBox();
        txtNacionalidad.setBounds(560, 350, 289, 36);
        txtNacionalidad.setBackground(SystemColor.text);
        txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNacionalidad.setModel(new DefaultComboBoxModel(new String[]{"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));
        contentPane.add(txtNacionalidad);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setBounds(562, 119, 253, 14);
        lblNombre.setForeground(SystemColor.textInactiveText);
        lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("APELLIDO");
        lblApellido.setBounds(560, 189, 255, 14);
        lblApellido.setForeground(SystemColor.textInactiveText);
        lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblApellido);

        JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
        lblFechaN.setBounds(560, 256, 255, 14);
        lblFechaN.setForeground(SystemColor.textInactiveText);
        lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblFechaN);

        JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
        lblNacionalidad.setBounds(560, 326, 255, 14);
        lblNacionalidad.setForeground(SystemColor.textInactiveText);
        lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNacionalidad);

        JLabel lblTelefono = new JLabel("TELÉFONO");
        lblTelefono.setBounds(562, 406, 253, 14);
        lblTelefono.setForeground(SystemColor.textInactiveText);
        lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtTelefono.setBounds(560, 424, 285, 33);
        txtTelefono.setColumns(10);
        txtTelefono.setBackground(Color.WHITE);
        txtTelefono.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtTelefono);

        JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
        lblTitulo.setBounds(606, 55, 234, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(lblTitulo);

        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
        lblNumeroReserva.setBounds(560, 474, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNumeroReserva);

        txtNreserva = new JFormattedTextField(getNumberFormatter());
        txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNreserva.setBounds(560, 495, 285, 33);
        txtNreserva.setColumns(10);
        txtNreserva.setBackground(Color.WHITE);
        txtNreserva.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNreserva);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, 289, 2);
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, 289, 2);
        separator_1_2_1.setForeground(new Color(12, 138, 199));
        separator_1_2_1.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, 289, 2);
        separator_1_2_2.setForeground(new Color(12, 138, 199));
        separator_1_2_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, 289, 2);
        separator_1_2_3.setForeground(new Color(12, 138, 199));
        separator_1_2_3.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, 289, 2);
        separator_1_2_4.setForeground(new Color(12, 138, 199));
        separator_1_2_4.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, 289, 2);
        separator_1_2_5.setForeground(new Color(12, 138, 199));
        separator_1_2_5.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_5);

        JPanel btnguardar = new JPanel();
        btnguardar.setBounds(723, 560, 122, 35);
        btnguardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registrarHuesped();
            }
        });
        btnguardar.setLayout(null);
        btnguardar.setBackground(new Color(12, 138, 199));
        contentPane.add(btnguardar);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel labelGuardar = new JLabel("GUARDAR");
        labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
        labelGuardar.setForeground(Color.WHITE);
        labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelGuardar.setBounds(0, 0, 122, 35);
        btnguardar.add(labelGuardar);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(new Color(12, 138, 199));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 121, 479, 502);
        panel.add(imagenFondo);
        imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));

        JPanel btnexit = new JPanel();
        btnexit.setBounds(857, 0, 53, 36);
        contentPane.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal principal = new MenuPrincipal();
                principal.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.white);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(SystemColor.black);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
    }

    private void registrarHuesped() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        Date fechaNacimiento = txtFechaN.getDate();
        String nacionalidad = Objects.requireNonNull(txtNacionalidad.getSelectedItem()).toString();
        String telefono = txtTelefono.getText();
        int numeroReserva;

        try {
            numeroReserva = Integer.parseInt(txtNreserva.getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "El campo de número de reserva no puede estar vacio.");
            return;
        }

        if (validarDatosDeHuesped(nombre, apellido, fechaNacimiento, nacionalidad, telefono, numeroReserva)) {
            String fecha = new SimpleDateFormat("dd-MM-yyyy").format(fechaNacimiento);
            Huesped huesped = new Huesped(nombre, apellido, fecha, nacionalidad, telefono, numeroReserva);
            huespedController.guardar(huesped);

            Exito exito = new Exito();
            exito.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Huesped ingresado inválido. Debes llenar todos los campos");
        }
    }

    private boolean validarDatosDeHuesped(String nombre, String apellido, Date fechaNacimiento,
                                          String nacionalidad, String telefono, int numeroReserva) {
        return nombre != null
                && apellido != null
                && (fechaNacimiento != null && validarFechaDeNacimiento(fechaNacimiento))
                && nacionalidad != null
                && telefono != null
                && numeroReserva > 0;
    }

    private boolean validarFechaDeNacimiento(Date fechaNacimiento) {
        boolean esFechaValida = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date fechaMinima = formatoFecha.parse("01-01-1900");
            Period periodo = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
            if (periodo.getYears() > 18 && fechaNacimiento.compareTo(fechaMinima) > 0) {
                esFechaValida = true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Fecha de nacimiento inválida." +
                                " Debe ser mayor de 18 años de edad y haber nacido después del año 1900.");

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return esFechaValida;
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

    private NumberFormatter getNumberFormatter() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);

        return formatter;
    }

}
