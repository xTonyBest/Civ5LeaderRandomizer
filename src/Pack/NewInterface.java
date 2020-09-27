package Pack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewInterface {
    public static void generateNewInterface(CivRNG newPool) {
        // Inizializzazione del frame
        JFrame frame = new JFrame("Civilization 5 : Leader Randomizer");
        frame.setSize(1024, 432);

        // Messaggio di inserimento
        JTextArea insert = new JTextArea("Insert the number of players.");
        insert.setBounds(50, 20, 184, 30);
        insert.setBackground(frame.getBackground());
        insert.setVisible(true);
        insert.setEditable(false);

        // Textfield dove inserire il numero di giocatori
        JTextField insertion = new JTextField();
        insertion.setBounds(50, 60, 120, 30);
        insertion.setVisible(true);

        // Bottone per far partire il randomizer
        JButton button = new JButton("Enter");
        button.setBounds(50, 100, 120, 30);

        // Messaggio di errore (visibile se si inserisce un valore fuori dal dominio 1 <= D <= 43)
        JTextArea error = new JTextArea("Error: insert a value between 1 and 43");
        error.setBounds(50, 600, 260, 30);
        error.setVisible(false);
        error.setEditable(false);

        // Immagine del logo di Civ5
        ImageIcon img = new ImageIcon("E:\\Scuola\\PROGETTI\\Civ5LeaderRandomizer\\icons\\civ_logo.png");
        JLabel civIcon = new JLabel(img);
        civIcon.setBounds(396, 50, img.getIconWidth(), img.getIconHeight());
        civIcon.setVisible(true);

        // Leaderboard che visualizza i leaders
        JTextArea leaderboard = new JTextArea();
        leaderboard.setVisible(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(insertion.getText().trim());
                if (number < 43 && number > 0) {
                    // Il messaggio di errore viene disabilitato (se attivo)
                    error.setVisible(false);

                    // La board viene pulita (se già scritta)
                    leaderboard.selectAll();
                    leaderboard.replaceSelection("");

                    // Avviene l'assegnamento delle civiltà ai giocatori
                    newPool.players = number;
                    List<Integer> leaders = newPool.randomNumberGenerator();

                    // Vengono definite le dimensioni della board in base al numero di giocatori
                    leaderboard.setRows(newPool.players);
                    leaderboard.setBounds(50, 200, 120, 30 * newPool.players);

                    // Vengono visualizzate le civiltà per ogni giocatore
                    for (int i = 0; i < newPool.players; ++i) {
                        leaderboard.insert(String.format("  Player %d : %d\n", newPool.players - i, leaders.get(i)), 0);
                    }
                    leaderboard.setVisible(true);
                    leaderboard.setEditable(false);
                } else
                    // Il messaggio di errore è visulaizzato se non si rientra nel dominio 1 <= n <= 43
                    error.setVisible(true);
            }
        });

        frame.add(button);
        frame.add(insert);
        frame.add(insertion);
        frame.add(error);
        frame.add(leaderboard);
        frame.add(civIcon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
