package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {
    public static final double VALOR_RESERVA_X_DIA = 75;
    private int reservaId;
    private String fechaEntrada;
    private String fechaSalida;
    private Double valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, String formaPago) {
        setFechaEntrada(fechaEntrada);
        setFechaSalida(fechaSalida);
        setValor(calcularValorDeReserva(fechaEntrada, fechaSalida));
        setFormaPago(formaPago);
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = formatearFecha(fechaEntrada);
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = formatearFecha(fechaSalida);
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    private String formatearFecha(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    private void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    private double calcularValorDeReserva(Date fechaEntrada, Date fechaSalida) {
        long feMillis = fechaEntrada.getTime();
        long fsMillis = fechaSalida.getTime();
        long tiempoDif = Math.abs(fsMillis - feMillis);
        int diasDif = Math.toIntExact(TimeUnit.DAYS.convert(tiempoDif, TimeUnit.MILLISECONDS));

        return VALOR_RESERVA_X_DIA * diasDif;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getReservaId() {
        return reservaId;
    }
}
