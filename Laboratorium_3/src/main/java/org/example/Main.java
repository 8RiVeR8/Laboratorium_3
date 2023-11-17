package org.example;

import Logic.OpinionService;
import UI.Interaction;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String dbWay = "/Users/maks_rz/Documents/Lab_3/Laboratorium_3/src/main/resources/Feedback.db";
        String dbFeedback ="Feedback";

        OpinionService Feedback = new OpinionService(dbWay, dbFeedback);
        Interaction interaction = new Interaction();
        interaction.startInteraction();
    }
}