package communicationsSystem.network.client.ui;

import communicationsSystem.network.client.Client;
import communicationsSystem.network.client.ui.guiWindows.LoginWindow;
import communicationsSystem.network.client.ui.guiWindows.MessageWindow;

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
