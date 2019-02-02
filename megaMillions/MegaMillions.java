/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megaMillions;

import java.util.Random;

/**
 *
 * @author Dimitri Liska using
 * @author professor Eric Charneski PowerBall source code!
 */
public class MegaMillions {

    static final int TICKET_PRICE = 1;

    private int[] whiteBalls;
    private int yellowBall;
    private int winnings;

    public MegaMillions() {
        Random random = new Random();
        whiteBalls = new int[5];
        yellowBall = random.nextInt(15) + 1;

        int[] bucket = new int[75];
        for (int number = 0; number < bucket.length; number++) {
            bucket[number] = number + 1;
        }

        for (int whiteBallIndex = 0; whiteBallIndex < whiteBalls.length; whiteBallIndex++) {
            int randomBall = random.nextInt(bucket.length);
            while (bucket[randomBall] == 0) {
                randomBall = random.nextInt(bucket.length);
            }

            whiteBalls[whiteBallIndex] = bucket[randomBall];
            bucket[randomBall] = 0;
        }
    }

    public MegaMillions(int[] whiteBalls, int yellowBall) {
        // TODO - this is bad - we should check for dupes
        for (int index = 1; index < whiteBalls.length; index++) {
            for (int ballToCheck = 0; ballToCheck < index; ballToCheck++) {
                if (whiteBalls[ballToCheck] == whiteBalls[index]) {
                    // do something here
                }
            }

        }

        this.whiteBalls = whiteBalls;
        this.yellowBall = yellowBall;
    }

    public int getWinnings(MegaMillions winningTicket) {

        boolean yellowMatches = winningTicket.yellowBall == this.yellowBall;

        int whiteMatches = 0;

        for (int myWhiteBall : whiteBalls) {
            for (int winningWhiteBall : winningTicket.whiteBalls) {
                if (myWhiteBall == winningWhiteBall) {
                    whiteMatches++;
                }
            }
        }
        this.winnings = MegaMillionsSimulator.winnings(whiteMatches, yellowMatches);

        return winnings;
    }
}
