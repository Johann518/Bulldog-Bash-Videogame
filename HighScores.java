// this file handles leaderboard creation/manipulation. 

import java.io.File;
import java.io.FileNotFoundException;
// import java.io.IOException;
import java.util.*;


public class HighScores {

    // Setting stuff up for future use
    static String scoresFile = "LocalHighScores.txt";
    static String[] nameArray;
    static int[] scoreArray;
    static int N = 0; // number of players saved
    

    public static void main (String[] args){
        
        // call method to read file and create arrays
        readLocalHighScores();

        // call method to sort the thingy and have it ready to display
        sortHighScores();

        // prints the sorted high scores (testing method)
        printSortedScores(N);

    }

    private static void readLocalHighScores(){
        
        // setup scanner
        Scanner scan;

        try {
            Scanner counter = new Scanner (new File(scoresFile));
            scan = new Scanner (new File(scoresFile));

            // count the number of entries
            while (counter.hasNext()) {
                counter.next();
                counter.nextInt();
                N++;
            }

            // close scanner
            counter.close();

            // setup array sizes
            nameArray = new String[N];
            scoreArray = new int[N];
            int i = 0;

            // while there are more scores
            while (scan.hasNext()) {
                nameArray[i] = scan.next(); // save player name to array
                scoreArray[i] = scan.nextInt(); // save player score to array
                i++; // count up to increase index
            }
                
            // close the scanner
            scan.close();

        // deal with missing files
        } catch (FileNotFoundException e) {

            // user-friendly error message
            System.out.println("The highscore file (LocalHighScores.txt) could not be found.");

            // exit
            System.exit(1);
        }
    }

    // sort the high scores
    private static void sortHighScores() {

        // sorting the array
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < scoreArray.length; j++) {
                if (scoreArray[i] < scoreArray[j]) {

                    // switch the scores
                    int temp = scoreArray[i];
                    scoreArray[i] = scoreArray[j];
                    scoreArray[j] = temp;

                    // switch the associated names
                    String hold = nameArray[i];
                    nameArray[i] = nameArray[j];
                    nameArray[j] = hold;

                }
            }
        }
    }


    // print the scores, high to low - testing method
    private static void printSortedScores(int N){
        for (int i = 0; i < N; i++) {
            int score = scoreArray[i];
            String name = nameArray[i];
            System.out.printf("%8s" + "\t" + "%5d" + "\n", name, score);
        }
    }

    // make name Array accessible 
    public static String[] getNameArray(){
        readLocalHighScores();
        sortHighScores();
        return nameArray;
    }

    // make score Array accessible 
    public static int[] getScoreArray(){
        readLocalHighScores();
        sortHighScores();
        return scoreArray;
    }

}