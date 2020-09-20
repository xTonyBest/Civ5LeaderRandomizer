package Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CivRNG {

    public int players;

    public CivRNG(int players) {
        this.players = players;
    }

    public CivRNG() {
        this.players = 0;
    }

    public List<Integer> randomNumberGenerator() {
        Random rnd = new Random();
        List<Integer> leaders = new ArrayList<>();

        for (int i = 0; i < players; ++i) {
            int choice = rnd.nextInt(43);
            for (Integer n : leaders)
                while (choice == 0 || n == choice)
                    choice = rnd.nextInt(43);
            leaders.add(choice);
        }

        return leaders;
    }

}