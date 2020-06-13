package com.eozdemir.notepad.model;


import io.realm.RealmList;
import io.realm.RealmObject;

public class Note extends RealmObject {

    private RealmList<String> note;
    private String date;

    public Note() {
    }

    public Note(RealmList<String> note, String date) {
        this.note = note;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                ", date=" + date +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RealmList<String> getNote() {
        return note;
    }

    public void setNote(RealmList<String> note) {
        this.note = note;
    }
}
