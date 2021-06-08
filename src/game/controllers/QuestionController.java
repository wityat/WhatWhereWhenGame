package game.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import game.model.Question;


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML
    Label question_number;
    @FXML
    Label time;
    @FXML
    Label progress;
    @FXML
    Label scoreField;
    @FXML
    ProgressBar progressBar;
    @FXML
    TextFlow questionText;
    @FXML
    TextField userAnswer;


    private Timeline timeline;
    private Integer timeForQuestion = 5;
    private Integer seconds = timeForQuestion;


    List<Question> questions;
    Iterator<Question> questionIterator;
    Question current_question;

    private Integer score = 0;

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        this.questionIterator = questions.iterator();
        nextQuestion();
    }

    public void submitAnswer (ActionEvent event) throws IOException {
        timeline.stop();
        seconds = timeForQuestion;
        confirmResults();

    }


    public void nextQuestion(){
        this.current_question = questionIterator.next();

        question_number.setText(String.format("Вопрос №%d", current_question.getNumber()));
        scoreField.setText("Баллов: " + score.toString());


        Text text_1 = new Text(current_question.getQuestion() + "\n\n");
        text_1.setFill(Color.RED);
        text_1.setFont(Font.font("Verdana", 12));

        Text text_2 = new Text(current_question.getAuthors());
        text_2.setFill(Color.BLUE);
        text_2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 10));

        questionText.getChildren().clear();

        questionText.getChildren().add(text_1);
        questionText.getChildren().add(text_2);

        userAnswer.clear();


        time.setText(String.format("У вас осталось: %d сек", seconds));
        timeline.playFromStart();

        progress.setText("Прогресс по вопросам: " + current_question.getNumber().toString() + "/" + questions.size());
        progressBar.setProgress(current_question.getNumber() * 1.0/questions.size());

    }

    public void confirmResults() throws IOException {
        System.out.println(userAnswer.getText());
        if (userAnswer.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Время вышло!");
            alert.setHeaderText(null);
            alert.setContentText("Ответ не дан. Балл не получаете.\n\nНажмите ОК для перехода к следующему вопросу.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType yesBtn = new ButtonType("Да");
            ButtonType noBtn = new ButtonType("Нет");
            alert.getButtonTypes().setAll(yesBtn,  noBtn);
            alert.setTitle("Время вышло!");
            alert.setHeaderText("Ваш ответ сопадает с правильным?");
            alert.setContentText("Ваш: " + userAnswer.getText() + "\n\nПравильный: " + current_question.getAnswer());
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == yesBtn){
                score += 1;
            }
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Супер!");
            alert1.setHeaderText(null);
            alert1.setContentText("Жми ОК и переходи к следующему вопросу!");
            alert1.showAndWait();
        }
        try {
            nextQuestion();
        } catch (Exception e1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType againBtn = new ButtonType("Заново");
            ButtonType changeBtn = new ButtonType("Другой");
            ButtonType exitBtn = new ButtonType("Выход");
            alert.getButtonTypes().setAll(againBtn,  changeBtn, exitBtn);
            alert.setTitle("Вы закончили игру!");
            alert.setHeaderText("Ура! Количество ваших баллов: " + score.toString());
            alert.setContentText("Хотите начать заново или выбрать другой турнир?");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == againBtn){
                this.questionIterator = questions.iterator();
                this.score = 0;
                this.seconds = timeForQuestion;
                nextQuestion();
            } else if (result.get() == changeBtn) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TournamentPackage.fxml"));
                Parent home_page_parent = loader.load();
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);

                app_stage.setScene(home_page_scene);
                app_stage.show();
            } else {
                Platform.exit();
            }
        }
    }
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                seconds --;
                time.setText(String.format("У вас осталось: %d сек", seconds));
                if (seconds <= 0){
                    timeline.stop();
                    Platform.runLater(() -> {
                        try {
                            confirmResults();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    seconds = timeForQuestion;
                }
            }
        });

        timeline.getKeyFrames().add(keyFrame);

    }
}