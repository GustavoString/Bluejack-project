import java.util.Random;
import java.util.Scanner;

public class Bluejack{

    public static void main(String[] args) {
        Random r1=new Random(System.currentTimeMillis());
        Scanner scan1=new Scanner(System.in);
        //creating the game deck
        CardDeck[] gamedeck=new CardDeck[40];
        gamedeck=CardDeck.generateGameDeck();
        gamedeck=CardDeck.shuffle(gamedeck);
        //creating the player deck (hand)
        //ge erating player deck after this point
        CardDeck[] playerdeck=new CardDeck[4];
        int j=0;
        CardDeck[] playerdeckUnchosen=new CardDeck[10];
        for (int i = gamedeck.length-1; i>gamedeck.length-6  ; i--) {
            playerdeckUnchosen[j]=gamedeck[i];
            j++;
        }
        for (int i=j ;  i< playerdeckUnchosen.length-2 ; i++) {
            j++;
            int signRandom=r1.nextInt(2);
            int valueRandom=r1.nextInt(1,7);
            int colourRandom=r1.nextInt(1,5);
            playerdeckUnchosen[i].CardColour=colourRandom;
            playerdeckUnchosen[i].CardType=1;
            if(signRandom==1) playerdeckUnchosen[i].CardValue=valueRandom;
            else playerdeckUnchosen[i].CardValue=-1*valueRandom;
        }
        for (int i = j; i < playerdeckUnchosen.length ; i++) {
            int percentRandom=r1.nextInt(1,11);
            if(percentRandom<=2){
                int coinflip=r1.nextInt(1,3);
                if(coinflip==1) playerdeckUnchosen[i].CardType=2;
                else playerdeckUnchosen[i].CardType=3;
            }
            else{
                int signRandom=r1.nextInt(2);
                int valueRandom=r1.nextInt(1,7);
                int colourRandom=r1.nextInt(1,5);
                playerdeckUnchosen[i].CardColour=colourRandom;
                playerdeckUnchosen[i].CardType=1;
                if(signRandom==1) playerdeckUnchosen[i].CardValue=valueRandom;
                else playerdeckUnchosen[i].CardValue=-1*valueRandom;
            }
        }
        int[] randomselectors=new int[4];
        for (int i = 0; i < randomselectors.length; i++) {
            randomselectors[i]=r1.nextInt(10);
            if(i>0){
                for (int k = 0; k < i; k++) {
                    while(randomselectors[k]==randomselectors[i]){
                        randomselectors[k]=r1.nextInt(10);
                    }
                }
            }
        }
        for (int i = 0; i < randomselectors.length; i++) {
            playerdeck[i]=playerdeckUnchosen[randomselectors[i]];
        }
        for (int i = 0; i < randomselectors.length; i++) {
            if(randomselectors[i]<=5){
                gamedeck[gamedeck.length-1-i].CardType=0;
            }
        }
        for (int i = 0; i < gamedeck.length-1; i++) {
            if(gamedeck[i].CardType==0) {
                gamedeck[i]=gamedeck[i+1];
            }
        }
        //IMPORTANT!! after this point you removed the top and bottom 5 cards but that is wrong as they all may not have been given to the user/bot.
        //write something that figures out which ones have been given to the user / bot and remove ONLY those.
        
        //creating bot deck
        CardDeck[] botdeck=new CardDeck[4];
        CardDeck[] botdeckUnchosen=new CardDeck[10];
        //do the same fix for bot deck aswell
        for (j = 0; j < 5; j++) {
            botdeckUnchosen[j]=gamedeck[j];
        }
        for (int i=j ;  i< botdeckUnchosen.length-2 ; i++) {
            j++;
            int signRandom=r1.nextInt(2);
            int valueRandom=r1.nextInt(1,7);
            int colourRandom=r1.nextInt(1,5);
            botdeckUnchosen[i].CardColour=colourRandom;
            botdeckUnchosen[i].CardType=1;
            if(signRandom==1) botdeckUnchosen[i].CardValue=valueRandom;
            else botdeckUnchosen[i].CardValue=-1*valueRandom;
        }
        for (int i = j; i < botdeckUnchosen.length ; i++) {
            int percentRandom=r1.nextInt(1,11);
            if(percentRandom<=2){
                int coinflip=r1.nextInt(1,3);
                if(coinflip==1) botdeckUnchosen[i].CardType=2;
                else botdeckUnchosen[i].CardType=3;
            }
            else{
                int signRandom=r1.nextInt(2);
                int valueRandom=r1.nextInt(1,7);
                int colourRandom=r1.nextInt(1,5);
                botdeckUnchosen[i].CardColour=colourRandom;
                botdeckUnchosen[i].CardType=1;
                if(signRandom==1) botdeckUnchosen[i].CardValue=valueRandom;
                else botdeckUnchosen[i].CardValue=-1*valueRandom;
            }
        }
        for (int i = 0; i < randomselectors.length; i++) {
            randomselectors[i]=-1;
        }
        for (int i = 0; i < randomselectors.length; i++) {
            randomselectors[i]=r1.nextInt(10);
            if(i>0){
                for (int k = 0; k < i; k++) {
                    while(randomselectors[k]==randomselectors[i]){
                        randomselectors[k]=r1.nextInt(10);
                    }
                }
            }
        }
        for (int i = 0; i < randomselectors.length; i++) {
            botdeck[i]=botdeckUnchosen[randomselectors[i]];
        }
        for (int i = 0; i < randomselectors.length; i++) {
            if(randomselectors[i]<=5){
                gamedeck[i].CardType=0;
            }
        }
        for (int i = 0; i < gamedeck.length-1; i++) {
            if(gamedeck[i].CardType==0) {
                gamedeck[i]=gamedeck[i+1];
            }
        }
        //finished creating player hands at this point
    }
    
    
}