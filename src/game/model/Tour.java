package game.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tour {
    private final StringProperty id;
//    private final IntegerProperty number;
    private final StringProperty title;
    private final ObjectProperty<LocalDateTime> createdAt;
    private final List<Question> questions = new ArrayList<Question>();

    public Tour() {
        this(null, null
//                , null
        );
    }

    public Tour(String id, String title
//            , Integer number
    ) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
//        this.number = new SimpleIntegerProperty(number);
    }

    public String toString() {
        return this.getTitle();
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

//    public IntegerProperty numberProperty() {
//        return number;
//    }
//
//    public Integer getNumber() {
//        return number.get();
//    }
//
//    public void setNumber(Integer number) {
//        this.number.set(number);
//    }


    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    public List<Question> getQuestions() {
        return FXCollections.observableArrayList(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions.addAll(questions);
    }

}
