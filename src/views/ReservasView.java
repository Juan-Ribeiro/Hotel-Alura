package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import controllers.ReservaController;
import modelo.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class ReservasView extends JFrame {

    private JPanel contentPane;
    public static JTextField txtValor;
    public static JDateChooser txtFechaE;
    public static JDateChooser txtFechaS;
    public static JComboBox<Format> txtFormaPago;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel lblValorSimbolo;
    private JLabel labelAtras;

    private ReservaController reservaController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReservasView frame = new ReservasView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ReservasView() {
        super("Reserva");

        this.reservaController = new ReservaController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 560);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);


        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 910, 560);
        contentPane.add(panel);
        panel.setLayout(null);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(SystemColor.textHighlight);
        separator_1_2.setBounds(68, 195, 289, 2);
        separator_1_2.setBackground(SystemColor.textHighlight);
        panel.add(separator_1_2);

        JSeparator separator_1_3 = new JSeparator();
        separator_1_3.setForeground(SystemColor.textHighlight);
        separator_1_3.setBackground(SystemColor.textHighlight);
        separator_1_3.setBounds(68, 453, 289, 2);
        panel.add(separator_1_3);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(SystemColor.textHighlight);
        separator_1_1.setBounds(68, 281, 289, 11);
        separator_1_1.setBackground(SystemColor.textHighlight);
        panel.add(separator_1_1);

        txtFechaE = new JDateChooser();
        txtFechaE.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtFechaE.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
        txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        txtFechaE.setBounds(68, 161, 289, 35);
        txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
        txtFechaE.setBackground(Color.WHITE);
        txtFechaE.setBorder(new LineBorder(SystemColor.window));
        txtFechaE.setDateFormatString("yyyy-MM-dd");
        txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
        panel.add(txtFechaE);

        lblValorSimbolo = new JLabel("$");
        lblValorSimbolo.setVisible(false);
        lblValorSimbolo.setBounds(121, 332, 17, 25);
        lblValorSimbolo.setForeground(SystemColor.textHighlight);
        lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

        panel.add(lblValorSimbolo);

        JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
        lblCheckIn.setForeground(SystemColor.textInactiveText);
        lblCheckIn.setBounds(68, 136, 169, 14);
        lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblCheckIn);

        JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
        lblCheckOut.setForeground(SystemColor.textInactiveText);
        lblCheckOut.setBounds(68, 221, 187, 14);
        lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblCheckOut);

        txtFechaS = new JDateChooser();
        txtFechaS.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
        txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
        txtFechaS.setBounds(68, 246, 289, 35);
        txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
        txtFechaS.setBackground(Color.WHITE);
        txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
        txtFechaS.setDateFormatString("yyyy-MM-dd");
        txtFechaS.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));

        txtFechaS.addPropertyChangeListener(evt -> {
            Date fechaEntrada = txtFechaE.getDate();
            Date fechaSalida = txtFechaS.getDate();

            if (fechaEntrada != null && fechaSalida != null) {
                validarFechas(fechaEntrada, fechaSalida);
            }
        });
        panel.add(txtFechaS);

        txtValor = new JTextField();
        txtValor.setBackground(SystemColor.text);
        txtValor.setHorizontalAlignment(SwingConstants.LEFT);
        txtValor.setForeground(Color.BLACK);
        txtValor.setBounds(78, 328, 200, 33);
        txtValor.setEditable(false);
        txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
        txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        panel.add(txtValor);
        txtValor.setColumns(10);

        JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
        lblValor.setForeground(SystemColor.textInactiveText);
        lblValor.setBounds(72, 303, 196, 14);
        lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblValor);

        txtFormaPago = new JComboBox();
        txtFormaPago.setBounds(68, 417, 289, 38);
        txtFormaPago.setBackground(SystemColor.text);
        txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtFormaPago.setModel(new DefaultComboBoxModel(new String[]{"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
        panel.add(txtFormaPago);

        JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
        lblFormaPago.setForeground(SystemColor.textInactiveText);
        lblFormaPago.setBounds(68, 382, 187, 24);
        lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblFormaPago);

        JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
        lblTitulo.setBounds(109, 60, 219, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(428, 0, 482, 560);
        panel_1.setBackground(new Color(12, 138, 199));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setBounds(197, 68, 104, 107);
        panel_1.add(logo);
        logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 140, 500, 409);
        panel_1.add(imagenFondo);
        imagenFondo.setBackground(Color.WHITE);
        imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

        JPanel btnexit = new JPanel();
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
                btnexit.setBackground(new Color(12, 138, 199));
                labelExit.setForeground(Color.white);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setBounds(429, 0, 53, 36);
        panel_1.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setForeground(Color.WHITE);
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

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
        header.setBackground(Color.WHITE);
        panel.add(header);

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
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(SystemColor.textHighlight);
        separator_1.setBounds(68, 362, 289, 2);
        separator_1.setBackground(SystemColor.textHighlight);
        panel.add(separator_1);

        JPanel btnsiguiente = new JPanel();
        btnsiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (ReservasView.txtFechaE.getDate() != null && ReservasView.txtFechaS.getDate() != null) {
                    Reserva reserva = new Reserva(txtFechaE.getDate(), txtFechaS.getDate(), txtFormaPago.getSelectedItem().toString());
                    reservaController.guardar(reserva);

                    RegistroHuesped registro = new RegistroHuesped(reserva);
                    registro.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
                }
            }
        });
        btnsiguiente.setLayout(null);
        btnsiguiente.setBackground(SystemColor.textHighlight);
        btnsiguiente.setBounds(238, 493, 122, 35);
        panel.add(btnsiguiente);
        btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblSiguiente = new JLabel("SIGUIENTE");
        lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
        lblSiguiente.setForeground(Color.WHITE);
        lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblSiguiente.setBounds(0, 0, 122, 35);
        btnsiguiente.add(lblSiguiente);
    }

    private void validarFechas(Date fechaEntrada, Date fechaSalida) {
        long feMillis = fechaEntrada.getTime();
        long fsMillis = fechaSalida.getTime();
        long tiempoDif = Math.abs(fsMillis - feMillis);
        int diasDif = Math.toIntExact(TimeUnit.DAYS.convert(tiempoDif, TimeUnit.MILLISECONDS));

        if (fechaSalida.compareTo(fechaEntrada) > 0 && diasDif > 0) {
            double valor = diasDif * Reserva.VALOR_RESERVA_X_DIA;
            txtValor.setText(String.format("%.2f USD.", valor));
        } else {
            JOptionPane.showMessageDialog(null, "La fecha de salida debe ser mayor a la de entrada.");
            txtFechaS.setDate(null);
        }
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
