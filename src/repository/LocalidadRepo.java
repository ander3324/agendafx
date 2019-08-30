/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionFactory;
import model.Localidad;
import model.Provincia;

/**
 *
 * @author ander
 */
public class LocalidadRepo {

    private List<Localidad> localidades;
    private Localidad localidad;

    public LocalidadRepo() {
        localidades = new ArrayList<>();
        cargarLocalidades();
    }

    private void cargarLocalidades() {
        try {
            Connection cn = new ConnectionFactory().getConnection();
            String sqlPrv = "select * from provincias";
            String sqlLoc = "select * from localidades where id_prv = ?";

            PreparedStatement ps = cn.prepareStatement(sqlPrv);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                //Cargando la provincia...
                Provincia p = new Provincia();
                p.setNro(rs.getInt(1));
                p.setProvincia(rs.getString(2));
                
                //Ahora, consultar localidades 
                //dónde coincidan con la provincia...
                PreparedStatement psLocs = cn.prepareStatement(sqlLoc);
                psLocs.setInt(1, p.getNro());
                
                ResultSet rsLocs = psLocs.executeQuery();
                
                while (rsLocs.next()) {
                    localidad = new Localidad();
                    localidad.setCpa(rsLocs.getString(1));
                    localidad.setLocalidad(rsLocs.getString(2));
                    localidad.setProvincia(p);

                    localidades.add(localidad);
                }
//                
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
