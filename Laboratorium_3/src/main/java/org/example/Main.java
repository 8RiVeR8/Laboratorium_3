package org.example;
import Logic.OpinionService;
import UI.Interaction;

public class Main {
    public static void main(String[] args) {
        String TxtName = args[0];

        OpinionService Feedback = new OpinionService(TxtName);
        Interaction interaction = new Interaction(Feedback);
        interaction.startInteraction();
    }
}