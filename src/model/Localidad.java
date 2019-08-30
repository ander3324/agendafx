/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class Localidad {

    private String cpa;
    private String localidad;
    private Provincia provincia;
    private List<Contacto> contactos;

    public Localidad() {
        contactos = new ArrayList<>();
    }

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void addContacto(Contacto c) {
        contactos.add(c);
    }

    @Override
    public String toString() {
        //return cpa + " - " + localidad;

        return cpa + " - " + localidad + " / " + provincia.getProvincia();
    }

}
