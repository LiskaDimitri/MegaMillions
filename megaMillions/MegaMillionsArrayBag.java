/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megaMillions;

import arraybag.ArrayBag;

import java.util.Random;

/**
 *
 * @author Dimitri Liska using
 * @author professor Eric Charneski PowerBall source code!
 */
public class MegaMillionsArrayBag {

    static final int TICKET_PRICE = 1;

    private ArrayBag<Integer> whiteBalls;
    private int yellowBall;
    private int winnings;

    public MegaMillionsArrayBag() {
        Random random = new Random();
        whiteBalls = new ArrayBag<>(5);
        yellowBall = random.nextInt(15) + 1;

        int[] bucket = new int[75];
        for (int number = 0; number < bucket.length; number++) {
            bucket[number] = number + 1;
        }

        while (whiteBalls.getCurrentSize() < 5) {
            int randomBall = random.nextInt(bucket.length);
            while (bucket[randomBall] == 0) {
                randomBall = random.nextInt(bucket.length);
            }

            whiteBalls.add(bucket[randomBall]);
            bucket[randomBall] = 0;
        }
    }

    public MegaMillionsArrayBag(ArrayBag<Integer> whiteBalls, int yellowBall) {
        // TODO - this is bad - we should check for dupes

        this.whiteBalls = whiteBalls;
        this.yellowBall = yellowBall;
    }

    public int getWinnings(MegaMillionsArrayBag winningTicket) {

        boolean yellowMatches = winningTicket.yellowBall == this.yellowBall;

        int whiteMatches = 0;

        while (!whiteBalls.isEmpty()) {
            if (winningTicket.whiteBalls.contains(whiteBalls.remove())) {
                whiteMatches++;
            }
        }
        this.winnings = MegaMillionsSimulator.winnings(whiteMatches, yellowMatches);

        return winnings;
    }
}
