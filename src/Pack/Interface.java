package Pack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Interface {

    public static void generateInterface(CivRNG newPool) {
        // Creazione del frame della finestra
        JFrame frame = new JFrame("Civilization 5 : Leader Randomizer");
        frame.setSize(1024, 768);

        // Crezione del bottone di azione
        JButton button = new JButton("Enter");
        button.setBounds(50, 100, 150, 30);

        // Creazione del textfield per inserie il numero di giocatori
        JTextField textField = new JTextField();
        textField.setBounds(50, 50, 150, 30);
        textField.setToolTipText("Insert an integer");
        textField.setVisible(true);

        // Creazione del messaggio di errore in caso di numeri non compresi nel dominio 1 <= n <= 43
        JTextArea message = new JTextArea("Error: insert a value between 1 and 43");
        message.setBounds(50, 600, 260, 40);
        message.setVisible(false);

        // Creazione della board per visualizzare gli assegnamenti delle civiltà
        JTextArea leaderBoard = new JTextArea();
        leaderBoard.setVisible(false);

        // Creazione del logo di Civ5
        ImageIcon img = new ImageIcon("E:\\Scuola\\PROGETTI\\Civ5RNG\\icons\\civ_logo.png");
        JLabel civIcon = new JLabel(img);
        civIcon.setBounds(350, 20, img.getIconWidth(), img.getIconHeight());
        civIcon.setVisible(true);

        // Lambda per avviare l'azione
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(textField.getText().trim()) < 43 && Integer.parseInt(textField.getText().trim()) > 0) {
                    // Il messaggio di errore viene disabilitato (se attivo)
                    message.setVisible(false);

                    // La board viene pulita (se già scritta)
                    leaderBoard.selectAll();
                    leaderBoard.replaceSelection("");

                    // Avviene l'assegnamento delle civiltà ai giocatori
                    newPool.players = Integer.parseInt(textField.getText().trim());
                    List<Integer> leaders = newPool.randomNumberGenerator();

                    // Vengono definite le dimensioni della board in base al numero di giocatori
                    leaderBoard.setRows(newPool.players);
                    leaderBoard.setBounds(50, 200, 120, 30 * newPool.players);

                    // Vengono visualizzate le civiltà per ogni giocatore
                    for (int i = 0; i < newPool.players; ++i) {
                        leaderBoard.insert(String.format("  Player %d : %d\n", newPool.players - i, leaders.get(i)), 0);
                    }
                    leaderBoard.setVisible(true);
                    leaderBoard.setEditable(false);
                } else
                    // Il messaggio di errore è visulaizzato se non si rientra nel dominio 1 <= n <= 43
                    message.setVisible(true);
            }
        });

        // Aggiunta dei componenti al frame
        frame.add(button);
        frame.add(textField);
        frame.add(message);
        frame.add(leaderBoard);
        frame.add(civIcon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
