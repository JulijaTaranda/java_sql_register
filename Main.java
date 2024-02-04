import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //RegisterScreen registerScreen = new RegisterScreen();
        // ar tadu inicializaciju, programma palaizas, bet arii uzreiz aizveras ciet

        //funkcijas parametrs ir cita funkcija
        SwingUtilities.invokeLater(() -> {
            RegisterScreen registerScreen = new RegisterScreen();
            registerScreen.setVisible(true);
        });

    }
}