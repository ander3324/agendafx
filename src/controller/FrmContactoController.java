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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Localidad;
import model.Provincia;
import repository.LocalidadRepo;
import repository.ProvinciaRepo;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class FrmContactoController implements Initializable {

    @FXML
    private ComboBox<Localidad> cmbLocalidad;
    @FXML
    private TextArea txaObs;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        LocalidadRepo locRepo = new LocalidadRepo();
//        Localidad l;
//        for(int i = 0; i < locRepo.getLocalidades().size(); i++) {
//            l = new Localidad();
//            l = locRepo.getLocalidades().get(i);
//            cmbLocalidad.getItems().add(l);
//        }
        ProvinciaRepo repo = new ProvinciaRepo();

        for (Provincia p : repo.getProvincias()) {
            for (Localidad l : p.getLocalidades()) {
                cmbLocalidad.getItems().add(l);
            }
        }

        cmbLocalidad.getSelectionModel().selectFirst();
    }

    @FXML
    private void guardar_OnAction(ActionEvent event) {
    }

    @FXML
    private void cancelar_OnAction(ActionEvent event) {
        Stage stg = (Stage) btnCancelar.getScene().getWindow();
        stg.close();
    }

}
