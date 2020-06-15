package com.eozdemir.notepad.model;


import io.realm.RealmList;
import io.realm.RealmObject;

public class Note extends RealmObject {

    RealmList<String> note;

    public Note() {
    }

    public Note(RealmList<String> note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note;
    }

    public RealmList<String> getNote() {
        return note;
    }

    public void setNote(RealmList<String> note) {
        this.note = note;
    }
}
