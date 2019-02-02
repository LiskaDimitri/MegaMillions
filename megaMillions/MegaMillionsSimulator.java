/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megaMillions;

import javax.swing.JOptionPane;

/**
 *
 * @author Dimitri Liska using
 * @author professor Eric Charneski PowerBall source code!
 */
public class MegaMillionsSimulator {

    static long startTime = System.nanoTime();
    static long endTime = System.nanoTime();
    static double nanoPower9 = 1e9;

    static int numberOfTicketsToPurchase;

    static int arrayTotalSpent = 0;
    static int arrayTotalWon = 0;
    static double arrayTotalTime = 0;

    static int arrayBagTotalSpent = 0;
    static int arrayBagTotalWon = 0;
    static double arrayBagTotalTime = 0;

    static int linkedBagTotalSpent = 0;
    static int linkedBagTotalWon = 0;
    static double linkedBagTotalTime = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        numberOfTicketsToPurchase = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Please enter the number of tickets you want to buy, or 0 to quit"));

        while (numberOfTicketsToPurchase != 0) {

            arrayRun(numberOfTicketsToPurchase);
            arrayBagRun(numberOfTicketsToPurchase);
            linkedBagRun(numberOfTicketsToPurchase);

            String message = String.format("1 - Array: Total Spent: $%,d  - Total Won: $%,d - Net Loss: $%,d \n"
                    + "2 - ArrayBag: Total Spent: $%,d  - Total Won: $%,d - Net Loss: $%,d \n"
                    + "3 - LinkedBag: $%,d  - Total Won: $%,d - Net Loss: $%,d",
                    arrayTotalSpent, arrayTotalWon, (arrayTotalWon - arrayTotalSpent),
                    arrayBagTotalSpent, arrayBagTotalWon, (arrayBagTotalWon - arrayBagTotalSpent),
                    linkedBagTotalSpent, linkedBagTotalWon, (linkedBagTotalWon - linkedBagTotalSpent));

            JOptionPane.showMessageDialog(null, message);
            JOptionPane.showMessageDialog(null, String.format(" 1 - Array Total Time: %,.4f seconds\n"
                    + " 2 - ArrayBag Total Time: %,.4f seconds\n"
                    + " 3 - LinkedBag Total Time: %,.4f seconds",
                    arrayTotalTime, arrayBagTotalTime, linkedBagTotalTime));

            numberOfTicketsToPurchase = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Please enter the number of tickets you want to buy, or 0 to quit"));
        }
    }

    static public void arrayRun(int numberOfTicketsPurchached) {
        startTime = System.nanoTime();

        arrayTotalSpent += numberOfTicketsPurchached * MegaMillions.TICKET_PRICE;
        MegaMillions winningTicket = new MegaMillions();

        for (int ticketNumber = 0; ticketNumber < numberOfTicketsPurchached; ticketNumber++) {
            MegaMillions myTicket = new MegaMillions();
            arrayTotalWon += myTicket.getWinnings(winningTicket);
        }
        endTime = System.nanoTime();
        System.nanoTime();

        arrayTotalTime = (endTime - startTime) / nanoPower9;
    }

    static public void arrayBagRun(int numberOfTicketsPurchached) {
        startTime = System.nanoTime();

        arrayBagTotalSpent += numberOfTicketsPurchached * MegaMillions.TICKET_PRICE;
        MegaMillionsArrayBag winningTicket = new MegaMillionsArrayBag();

        for (int ticketNumber = 0; ticketNumber < numberOfTicketsPurchached; ticketNumber++) {
            MegaMillionsArrayBag myTicket = new MegaMillionsArrayBag();
            arrayBagTotalWon += myTicket.getWinnings(winningTicket);
        }

        endTime = System.nanoTime();

        arrayBagTotalTime = (endTime - startTime) / nanoPower9;
    }

    static public void linkedBagRun(int numberOfTicketsPurchached) {
        startTime = System.nanoTime();

        linkedBagTotalSpent += numberOfTicketsPurchached * MegaMillions.TICKET_PRICE;
        MegaMillionsLinkedBag winningTicket = new MegaMillionsLinkedBag();

        for (int ticketNumber = 0; ticketNumber < numberOfTicketsPurchached; ticketNumber++) {
            MegaMillionsLinkedBag myTicket = new MegaMillionsLinkedBag();
            linkedBagTotalWon += myTicket.getWinnings(winningTicket);
        }

        endTime = System.nanoTime();

        linkedBagTotalTime = (endTime - startTime) / nanoPower9;
    }

    public static int winnings(int whiteMatches, boolean yellowMatches) {

        int winnings = 0;

        if (whiteMatches == 5 && yellowMatches) {
            winnings = 100000000;
            System.out.println("JackPot");

        } else if (whiteMatches == 5) {
            winnings = 1000000;

        } else if (whiteMatches == 4 && yellowMatches) {
            winnings = 5000;

        } else if (whiteMatches == 3 & yellowMatches) {
            winnings = 50;

        } else if (whiteMatches == 3 || (whiteMatches == 2 && yellowMatches)) {
            winnings = 5;

        } else if (whiteMatches == 1 && yellowMatches) {
            winnings = 2;

        } else if (yellowMatches) {
            winnings = 1;
        }

        return winnings;
    }
}
