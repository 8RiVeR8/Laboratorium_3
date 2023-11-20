package org.example;

import Logic.OpinionService;
import UI.Interaction;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        //String dbWay = "/Users/maks_rz/Documents/Lab_3/Laboratorium_3/src/main/resources/Feedback.db";
        //String pythonWay = "/Users/maks_rz/Documents/Lab_3/Python/TrendLine.py";
        //String dbFeedback ="Feedback";

        String dbWay = args[0];
        String dbFeedback = args[1];
        String pythonWay = args[2];

        OpinionService Feedback = new OpinionService(dbWay, dbFeedback);
        Interaction interaction = new Interaction(Feedback, pythonWay);
        interaction.startInteraction();
    }
}