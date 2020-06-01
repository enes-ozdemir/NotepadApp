package com.eozdemir.notepad.model;


import java.util.Date;

import io.realm.RealmObject;

public class Note extends RealmObject {

    private String note;
    private Date date;

    public Note() {
    }

    public Note(String note, Date date) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
