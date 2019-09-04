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
import javafx.scene.control.Alert;
import jdbc.ConnectionFactory;
import model.Contacto;
import model.Localidad;
import model.Provincia;

/**
 *
 * @author ander
 */
public class ContactoRepo {

    private List<Contacto> contactos;
    private Contacto contacto;

    public ContactoRepo() {
        contactos = new ArrayList<>();
        cargarContactos();
    }

    private void cargarContactos() {

        //Sentencias sql empotradas:
        String sqlPrv = "select * from provincias";
        String sqlLoc = "select * from localidades where id_prv = ?";
        String sqlCon = "select * from contactos where cpa = ?";

        try {
            //Objeto conexión:
            Connection cn = new ConnectionFactory().getConnection();

            //Preparar consulta 1:
            PreparedStatement ps = cn.prepareStatement(sqlPrv);

            ResultSet rsPrv = ps.executeQuery();

            //Recorrer resultados de select de provincias:
            while (rsPrv.next()) {

                //Crear objeto provincia:
                Provincia p = new Provincia();
                p.setNro(rsPrv.getInt(1));
                p.setProvincia(rsPrv.getString(2));

                //Obtener las localidades correspondientes a la provincia:
                ps = cn.prepareStatement(sqlLoc);
                ps.setInt(1, p.getNro());
                ResultSet rsLoc = ps.executeQuery();

                //Recorrer resultados de select de localidades:
                while (rsLoc.next()) {

                    //Crear instancia de Localidad:
                    Localidad l = new Localidad();
                    l.setCpa(rsLoc.getString(1));
                    l.setLocalidad(rsLoc.getString(2));
                    l.setProvincia(p);

                    //(Opcional) Guardar localidad en la colección del obj Provincia:
                    p.addLocalidad(l);

                    //Obtener los contactos correspondientes a la localidad:
                    ps = cn.prepareStatement(sqlCon);
                    ps.setString(1, l.getCpa());
                    ResultSet rsCon = ps.executeQuery();

                    //Recorrer resultados de select de contactos...
                    while (rsCon.next()) {
                        //Crear objeto Contacto:
                        contacto = new Contacto();
                        contacto.setNro(rsCon.getInt(1));
                        contacto.setApellido(rsCon.getString(2));
                        contacto.setNombre(rsCon.getString(3));
                        contacto.setDomicilio(rsCon.getString(4));
                        contacto.setTelefono(rsCon.getString(5));
                        contacto.setObs(rsCon.getString(6));
                        contacto.setLocalidad(l);

                        //(Opcional) Guardar contacto en la colección del obj Localidad
                        l.addContacto(contacto);

                        //Guardar contacto en la lista del repositorio...
                        contactos.add(contacto);
                    }
                }
            }

        } catch (Exception e) {
            //Mostrar dialogo...
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html

            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }

    }

    public void insertContacto(Contacto contacto) {
        try {
            Connection cn = new ConnectionFactory().getConnection();

            String insert = "insert into contactos (ape, nom, dom, tel, obs, cpa) values (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setString(1, contacto.getApellido());
            ps.setString(2, contacto.getNombre());
            ps.setString(3, contacto.getDomicilio());
            ps.setString(4, contacto.getTelefono());
            ps.setString(5, contacto.getObs());
            ps.setString(6, contacto.getLocalidad().getCpa());
            
            ps.execute();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Contacto '" + 
                    contacto.getNombre() + 
                    " " + contacto.getApellido() + "' guardado.");
            alert.showAndWait();
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
