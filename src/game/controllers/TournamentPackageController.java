/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import game.JsonParse.JsonBodyHandler;
import game.model.Tournament;
import game.model.TournamentPackage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Supplier;



public class TournamentPackageController implements Initializable {
    @FXML
    Label label;

    @FXML
    ListView<TournamentPackage> listView;

    @FXML
    private void exitClick(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // create a client
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("http://api.baza-voprosov.ru/groups/INTER"))
                .header("accept", "application/ld+json")
                .build();

        HttpResponse<Supplier<TournamentPackage>> response = null;
        try {
            response = client.send(request, new JsonBodyHandler<>(TournamentPackage.class));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        TournamentPackage r = response.body().get();
        System.out.println(r.getTitle());
        label.setText(r.getTitle());
        List<TournamentPackage> res = r.getSubgroups();



        listView.setItems(FXCollections.observableArrayList(res));


        listView.setCellFactory(param -> new ListCell<TournamentPackage>() {
            @Override
            protected void updateItem(TournamentPackage item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

    }

    @FXML
    public void handleMouseClick(MouseEvent event) throws IOException {
        if (!listView.getSelectionModel().getSelectedItem().getSubgroups().isEmpty()){
            listView.setItems(FXCollections.observableArrayList(listView.getSelectionModel().getSelectedItem().getSubgroups()));
        } else if (!listView.getSelectionModel().getSelectedItem().getPackages().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Tournament.fxml"));
            Parent home_page_parent = loader.load();
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            TournamentController tournamentController = loader.getController();
            tournamentController.setListView(listView.getSelectionModel().getSelectedItem().getPackages());

            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
    }
}
