import java.util.Scanner;
import java.io.File;

public class Hangman {
    public static void main(String args[]) {
        try {
            Scanner words   = new Scanner(new File("nounlist.txt"));
            String wordlist = "";
            int wordcount   = 0;
            while (words.hasNextLine()) {
                wordlist += words.nextLine() + " ";
                wordcount++;
            }
            String leWords[] = wordlist.split(" ");
            String guess = "";
            String theWord = leWords[(int)(Math.random()*wordcount)];
            char theSecretWord[] = new char[theWord.length()];
            for (int i=0; i<theSecretWord.length; i++) theSecretWord[i] = '_'; 
            int left       = 6;
            boolean found  = false;
            boolean keepGo = true;
            Scanner scanny = new Scanner(System.in);
            System.out.println("  _____\n  |/  |\n  |\n  |\n  |\n  |______                                      ");
            while(true) {
                System.out.print("\033[H\033[2J");
                if (!keepGo) {
                    System.out.println("\n\n  YOU ARE THE WINNER!!! \n\n");
                    System.exit(0);
                }
                System.out.println("|==================================================|\n| m    m   mm   mm   m   mmm  m    m   mm   mm   m |\n| #    #   ##   #\"m  # m\"   \" ##  ##   ##   #\"m  # |\n| #mmmm#  #  #  # #m # #   mm # ## #  #  #  # #m # |\n| #    #  #mm#  #  # # #    # # \"\" #  #mm#  #  # # |\n| #    # #    # #   ##  \"mmm\" #    # #    # #   ## |\n|==================================================|\n|    By Brian Millar   --   " + wordcount + " possible words    |\n|==================================================|\n\n  Your Word: ");
                for (char i : theSecretWord) System.out.print(i + " ");
                System.out.println("\n");
                switch(left) {
                    case 6:
                        System.out.println("  _____\n  |/  |\n  |\n  |\n  |\n  |______                                      ");
                        break;
                    case 5:
                        System.out.println("  _____\n  |/  |\n  |   O\n  |\n  |\n  |______                                  ");
                        break;
                    case 4:
                        System.out.println("  _____\n  |/  |\n  |   O\n  |   |\n  |\n  |______                              ");
                        break;
                    case 3:
                        System.out.println("  _____\n  |/  |\n  |   O\n  |   |\\\n  |\n  |______                            ");
                        break;
                    case 2:
                        System.out.println("  _____\n  |/  |\n  |   O\n  |  /|\\\n  |\n  |______                            ");
                        break;
                    case 1:
                        System.out.println("  _____\n  |/  |\n  |   O\n  |  /|\\\n  |   /\n  |______                        ");
                        break;
                    case 0:
                        System.out.println("  _____\n  |/  |    ***YOU ARE DEAD***\n  |   O    THE WORD WAS " + theWord + "\n  |  /|\\\n  |   /\\\n  |______"); //hanged
                        System.exit(1);
                        break;
                    default:
                        System.out.println("  _____\n  |/  |\n  |\n  |\n  |\n  |______                                      ");
                        break;
                }
                System.out.print("\n  Enter a letter to guess: ");
                guess = scanny.next();
                found = false;
                for (int i=0; i<theWord.length(); i++) {
                    if (guess.equalsIgnoreCase(theWord.charAt(i) + "")) {
                        theSecretWord[i] = theWord.charAt(i);
                        found = true;
                    }
                }
                if (!found) left--;
                keepGo = false;
                for (int i=0; i<theWord.length(); i++) if(!(theWord.charAt(i) == theSecretWord[i])) keepGo = true;
            }
        }
        catch(Exception e) {
            System.out.println("Failed to read from words file, exiting...");
            System.exit(1);
        }
    }
}
