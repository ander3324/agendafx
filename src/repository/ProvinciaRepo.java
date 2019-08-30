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
public class ProvinciaRepo {
    
    private List<Provincia> provincias;
    private Provincia provincia;

    public ProvinciaRepo() {
        provincias = new ArrayList<>();
        cargarProvincias();
    }
    
    private void cargarProvincias() {
        String sqlPrv = "select * from provincias";
        String sqlLoc = "select * from localidades where id_prv = ?";
        
        try {
            //Objeto de conexión:
            Connection cn = new ConnectionFactory().getConnection();
            
            //Preparar la primer sentencia sql:
            PreparedStatement ps = cn.prepareStatement(sqlPrv);
            
            //Crear objeto de conjunto de resultados de consulta sql de provincias:
            ResultSet rsPrv = ps.executeQuery(); //Ejecutar sentencia...
            
            //Recorrer las provincias cargadas de la bd:
            while(rsPrv.next()) {
                
                //Transformar los resultados en objeto Provincia:
                provincia = new Provincia();
                provincia.setNro(rsPrv.getInt(1));
                provincia.setProvincia(rsPrv.getString(2));
                
                //Crear la lista de localidades para la provincia actual:
                ps = cn.prepareStatement(sqlLoc);
                ps.setInt(1, provincia.getNro());
                ResultSet rsLoc = ps.executeQuery();
                
                while(rsLoc.next()) {
                    
                    //Transformar los resultados en objetos Localidad:
                    Localidad l = new Localidad();
                    l.setCpa(rsLoc.getString(1));
                    l.setLocalidad(rsLoc.getString(2));
                    l.setProvincia(provincia);
                    
                    //Guardar la localidad en la lista del objeto provincia:
                    provincia.addLocalidad(l);
                }
                
                //Añadir la provincia a la lista del repo...
                provincias.add(provincia);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    
    
}
