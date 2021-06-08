package game.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private final StringProperty id;
    private final IntegerProperty number;
    private final StringProperty type;
    private final StringProperty question;
    private final StringProperty answer;
    private final StringProperty authors;
    private final StringProperty sources;
    private final StringProperty comments;

    public Question() {
        this(null, 0, null, null, null ,null , null,null);
    }

    public Question(String id, Integer number, String type, String question,
                    String answer, String authors, String sources, String comments) {
        this.id = new SimpleStringProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.type = new SimpleStringProperty(type);
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
        this.authors = new SimpleStringProperty(authors);
        this.sources = new SimpleStringProperty(sources);
        this.comments = new SimpleStringProperty(comments);
    }

    public String toString() {
        return this.getQuestion();
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

    public IntegerProperty numberProperty() {
        return number;
    }

    public Integer getNumber() {
        return number.get();
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public StringProperty questionProperty() {
        return question;
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public String getSources() {
        return sources.get();
    }

    public void setSources(String sources) {
        this.sources.set(sources);
    }

    public StringProperty sourcesProperty() {
        return sources;
    }

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public String getAuthors() {
        return authors.get();
    }

    public void setAuthors(String authors) {
        this.authors.set(authors);
    }

    public StringProperty authorsProperty() {
        return authors;
    }

}
