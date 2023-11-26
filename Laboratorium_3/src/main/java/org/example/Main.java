package org.example;
import Logic.OpinionService;
import UI.Interaction;

/**
 * The Main class serves as the entry point for the program. It initializes the OpinionService
 * and Interaction components, providing a command-line interface for users to interact with
 * worker opinions stored in a text file. The text file name is provided as a command-line
 * argument (args[0]). The program begins by reading existing opinions, and users can add,
 * delete, or analyze opinions through the interactive menu.
 */

public class Main {
    public static void main(String[] args) {
        String TxtName = args[0];

        OpinionService Feedback = new OpinionService(TxtName);
        Interaction interaction = new Interaction(Feedback);
        interaction.startInteraction();
    }
}