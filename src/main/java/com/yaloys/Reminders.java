package com.yaloys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.time.LocalDate;

public class Reminders implements Serializable
{
    private static final long serialVersionUID = 1L; //контроль версії серіалізації
    private String text;
    private final LocalDate date;
    private boolean isCompleted;
    public static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting().create();
//    private static Gson gson;

    public Reminders (String text, LocalDate date)
    {
        this.text = text;
        this.date = date;
        this.isCompleted = false;
    }
    public Gson getGson()
    {
        return gson;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public boolean isCompleted()
    {
        return isCompleted;
    }
    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }

    @Override
    public String toString()
    {
        return "Reminder: " + text  + ", created at: " + date + ", is completed=" + isCompleted + '.';
    }
}

