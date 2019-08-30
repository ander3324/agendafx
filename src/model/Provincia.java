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
public class Provincia {
    
    private int nro;
    private String provincia;
    private List<Localidad> localidades;
    
    public Provincia() {
        localidades = new ArrayList<>();
    }
    
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }
    
    public void addLocalidad(Localidad l) {
        localidades.add(l);
    }
    
    @Override
    public String toString() {
        return nro + " - " + provincia;
    }
    
    
}
