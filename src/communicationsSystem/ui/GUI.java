package communicationsSystem.ui;

import communicationsSystem.network.Client;
import communicationsSystem.ui.guiWindows.LoginWindow;
import communicationsSystem.ui.guiWindows.MessageWindow;

public class GUI {
    static Client client = null;
    static boolean isAdmin = false;
    static String username;

    public GUI(String window, boolean isAdmin, Client passedClient) {

        client = passedClient;
        GUI.isAdmin = isAdmin;

        if (window.equals("login")) {
            new LoginWindow(client);
        } else {
            new MessageWindow(client);
        }

    }

}
