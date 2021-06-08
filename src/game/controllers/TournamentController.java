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
import game.model.Tour;
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

public class TournamentController implements Initializable {
    @FXML
    Label label;

    @FXML
    ListView<Tournament> listView;

    @FXML
    private void exitClick(ActionEvent event) throws IOException {
        Platform.exit();
    }

    public void setListView(List<Tournament> lt) {
        listView.setItems(FXCollections.observableArrayList(lt));
    }
    @FXML
    public void handleMouseClick(MouseEvent event) throws IOException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create(String.format("http://api.baza-voprosov.ru/packages/%s", listView.getSelectionModel().getSelectedItem().getId())))
                .header("accept", "application/ld+json")
                .build();

        HttpResponse<Supplier<Tournament>> response = null;
        try {
            response = client.send(request, new JsonBodyHandler<>(Tournament.class));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        Tournament r = response.body().get();
//        label.setText(r.getTitle());
        List<Tour> res = r.getTours();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Tour.fxml"));
        Parent home_page_parent = loader.load();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TourController tourController = loader.getController();
        tourController.setListView(res);

        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}