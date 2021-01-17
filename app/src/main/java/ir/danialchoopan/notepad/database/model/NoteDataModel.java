package ir.danialchoopan.notepad.database.model;

public class NoteDataModel {
    int id;
    String text_body;
    String created_at;
    String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText_body() {
        return text_body;
    }

    public void setText_body(String text_body) {
        this.text_body = text_body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public NoteDataModel(int id, String text_body, String created_at, String updated_at) {
        this.id = id;
        this.text_body = text_body;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public NoteDataModel() {
    }
}
