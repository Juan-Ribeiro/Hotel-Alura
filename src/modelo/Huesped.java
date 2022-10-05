package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Huesped {
    private int huespedId;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private int numeroReserva;

    public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, int numeroReserva) {
        setNombre(nombre);
        setApellido(apellido);
        setFechaNacimiento(fechaNacimiento);
        setNacionalidad(nacionalidad);
        setTelefono(telefono);
        setNumeroReserva(numeroReserva);
    }

    public Huesped(int huespedId, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, int reservaId) {
        this(nombre, apellido, fechaNacimiento, nacionalidad, telefono, reservaId);
        setHuespedId(huespedId);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setHuespedId(int huespedId) {
        this.huespedId = huespedId;
    }

    public int getHuespedId() {
        return huespedId;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Nombre: %s - Apellido: %s - Fecha de Nacimiento: %s - Nacionalidad: %s - Telefono: %s - Numero de Reserva: %d",
                this.huespedId, this.nombre, this.apellido, this.fechaNacimiento, this.nacionalidad, this.telefono, this.numeroReserva);
    }
}
