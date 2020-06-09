package com.eozdemir.notepad.model;


import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Note extends RealmObject {

    private RealmList<String> note;
    private Date date;

    public Note() {
    }

    public Note(RealmList<String> note, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getNote() {
        return note;
    }

    public void setNote(RealmList<String> note) {
        this.note = note;
    }
}
