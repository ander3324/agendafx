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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Localidad;
import model.Provincia;
import repository.ProvinciaRepo;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class FrmLocalidadesController implements Initializable {

    @FXML
    private ComboBox<Provincia> cmbProvincias;
    @FXML
    private TableView<Localidad> tbvLocalidades;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    ProvinciaRepo repo = new ProvinciaRepo();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarProvincias();
        cargarLocalidades(cmbProvincias.getSelectionModel().getSelectedItem());
    }

    void cargarProvincias() {
        for (Provincia p : repo.getProvincias()) {
            cmbProvincias.getItems().add(p);
        }
        cmbProvincias.getSelectionModel().selectFirst();
    }

    void cargarLocalidades(Provincia provincia) {
        tbvLocalidades.getItems().addAll(provincia.getLocalidades());
    }

    @FXML
    private void cmbProvincias_OnAction(ActionEvent event) {
        tbvLocalidades.getItems().clear();
        cargarLocalidades(cmbProvincias.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void btnGuardar_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelar_OnAction(ActionEvent event) {
    }

}
