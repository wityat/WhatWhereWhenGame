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

public class TourController implements Initializable {
    @FXML
    Label label;

    @FXML
    ListView<Tour> listView;

    @FXML
    private void exitClick(ActionEvent event) throws IOException {
        Platform.exit();
    }

    public void setListView(List<Tour> lt) {
        listView.setItems(FXCollections.observableArrayList(lt));
    }
    @FXML
    public void handleMouseClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Question.fxml"));
        Parent home_page_parent = loader.load();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        QuestionController questionController = loader.getController();
        questionController.setQuestions(listView.getSelectionModel().getSelectedItem().getQuestions());

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