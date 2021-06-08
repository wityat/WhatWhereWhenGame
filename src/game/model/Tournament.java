package game.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tournament {

    private final StringProperty id;
    private final StringProperty title;
    private final ObjectProperty<LocalDateTime> createdAt;
    private final ArrayList<Tour> tours= new ArrayList<Tour>();


    /**
     * Конструктор по умолчанию.
     */
    public Tournament() {
        this(null, null);
    }

    public Tournament(String id, String title) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());

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

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours.addAll(tours);
    }
}


