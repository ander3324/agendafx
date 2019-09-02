/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Contacto;
import repository.ContactoRepo;
import util.ventanasUtil;

/**
 *
 * @author ander
 */
public class frmAgendaController implements Initializable {

    private Label label;
    @FXML
    private MenuItem mniNuevoContacto;
    @FXML
    private MenuItem mniNuevaLocalidad;
    @FXML
    private MenuItem mniNuevaProvincia;
    @FXML
    private MenuItem mniCerrar;
    @FXML
    private MenuItem mniModificarLocalidad;
    @FXML
    private MenuItem mniModificarProvincia;
    @FXML
    private MenuItem mniEliminarLocalidad;
    @FXML
    private MenuItem mniEliminarProvincia;
    @FXML
    private MenuItem mniDocumentacion;
    @FXML
    private MenuItem mniAcercaDe;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Contacto> tbvContactos;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarContactos();
    }

    void cargarContactos() {
        ContactoRepo repo = new ContactoRepo();
        for (Contacto c : repo.getContactos()) {
            tbvContactos.getItems().add(c);
        }
    }

    @FXML
    private void mniNuevoContacto_OnAction(ActionEvent event) {
        btnNuevo.fire();
    }

    @FXML
    private void mniNuevaLocalidad_OnAction(ActionEvent event) {
    }

    @FXML
    private void mniNuevaProvincia_OnAction(ActionEvent event) {
    }

    @FXML
    private void cerrar_OnAction(ActionEvent event) {
        Stage stage = (Stage) btnEditar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mniModificarLocalidad_OnAction(ActionEvent event) {
        ventanasUtil.abrirDialogo(btnNuevo.getScene().getWindow(), "/view/frmLocalidades.fxml", "Listado de Localidades");
    }

    @FXML
    private void mniModificarProvincia_OnAction(ActionEvent event) {
    }

    @FXML
    private void mniEliminarLocalidad_OnAction(ActionEvent event) {
    }

    @FXML
    private void mniEliminarProvincia_OnAction(ActionEvent event) {
    }

    @FXML
    private void mniDocumentacion_OnAction(ActionEvent event) {
    }

    @FXML
    private void mniAcercaDe_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnNuevo_OnAction(ActionEvent event) {
        ventanasUtil.abrirDialogo(btnNuevo.getScene().getWindow(), "/view/frmContacto.fxml", "Nuevo Contacto");
    }

    @FXML
    private void btnEditar_OnAction(ActionEvent event) {
        cargarContactos();
    }

    @FXML
    private void btnEliminar_OnAction(ActionEvent event) {
    }

}
