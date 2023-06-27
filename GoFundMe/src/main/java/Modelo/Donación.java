/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Donación {
    private int ID;
    private double monto;
    private Date fecha;
    private String comentario;
    private int ID_usuario;
    private int ID_campaña;

    public Donación(int ID, double monto, Date fecha, String comentario, int ID_usuario, int ID_campaña) {
        this.ID = ID;
        this.monto = monto;
        this.fecha = fecha;
        this.comentario = comentario;
        this.ID_usuario = ID_usuario;
        this.ID_campaña = ID_campaña;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public int getID_campaña() {
        return ID_campaña;
    }

    public void setID_campaña(int ID_campaña) {
        this.ID_campaña = ID_campaña;
    }
    
    
}
