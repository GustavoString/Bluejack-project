import java.util.Random;
import java.util.Scanner;

public class Bluejack{

    public static void main(String[] args) {
        Scanner scan1=new Scanner(System.in);
        //creating the game deck
        CardDeck[] gamedeck=new CardDeck[40];
        gamedeck=CardDeck.generateGameDeck();
        gamedeck=CardDeck.shuffle(gamedeck);
        //creating the player deck (hand)
        CardDeck[] playerdeck=playerDeckGenerator(gamedeck);
        //removing the bottom 5 cards as they have been given to the user
        for (int i = gamedeck.length-1; i > gamedeck.length-6; i--) {
            gamedeck[i].CardType=0;
        }
        //creating bot deck
        CardDeck[] botdeck=botDeckGenerator(gamedeck);
        //removing top 5 cards in gamedeck and shifting the cards
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < gamedeck.length-1; j++) {
                gamedeck[j].CardType=gamedeck[j+1].CardType;
            }
        }
        
    }

    public static CardDeck[] playerDeckGenerator(CardDeck[] gamedeck){
        Random r1=new Random(System.currentTimeMillis());
        CardDeck[] playerdeck=new CardDeck[10];
        int j=0;
        for (int i = gamedeck.length-1; i>gamedeck.length-6  ; i--) {
            playerdeck[j].CardType=1;
            playerdeck[j].CardColour=gamedeck[i].CardColour;
            playerdeck[j].CardValue=gamedeck[i].CardValue;
            j++;
        }
        for (int i=j ;  i< playerdeck.length-2 ; i++) {
            j++;
            int signRandom=r1.nextInt(2);
            int valueRandom=r1.nextInt(1,7);
            int colourRandom=r1.nextInt(1,5);
            playerdeck[i].CardColour=colourRandom;
            playerdeck[i].CardType=1;
            if(signRandom==1) playerdeck[i].CardValue=valueRandom;
            else playerdeck[i].CardValue=-1*valueRandom;
        }
        for (int i = j; i < playerdeck.length ; i++) {
            int percentRandom=r1.nextInt(1,11);
            if(percentRandom<=2){
                int coinflip=r1.nextInt(1,3);
                if(coinflip==1) playerdeck[i].CardType=2;
                else playerdeck[i].CardType=3;
            }
            else{
                int signRandom=r1.nextInt(2);
                int valueRandom=r1.nextInt(1,7);
                int colourRandom=r1.nextInt(1,5);
                playerdeck[i].CardColour=colourRandom;
                playerdeck[i].CardType=1;
                if(signRandom==1) playerdeck[i].CardValue=valueRandom;
                else playerdeck[i].CardValue=-1*valueRandom;
            }
        }
        //randomly selecting 4 after this point
        CardDeck[] playerdeckreturn=new CardDeck[4];
        int a=10;
        int b=1;
        for (int i = 0; i < playerdeckreturn.length; i++) {
            int randomselector=r1.nextInt(a);
            playerdeckreturn[i]=playerdeck[randomselector];
            a--;
            for (int k = randomselector; k < playerdeck.length-b; k++) {
                playerdeck[k]=playerdeck[k+1];
            }
            b++;
        }
        return playerdeckreturn;
    }

    public static CardDeck[] botDeckGenerator(CardDeck[] gamedeck){
        Random r1=new Random(System.currentTimeMillis());
        CardDeck[] botdeck=new CardDeck[10];
        for (int i = 0; i<5  ; i++) {
            botdeck[i].CardType=1;
            botdeck[i].CardColour=gamedeck[i].CardColour;
            botdeck[i].CardValue=gamedeck[i].CardValue;
        }
        for (int i=5 ;  i< botdeck.length-2 ; i++) {
            int signRandom=r1.nextInt(2);
            int valueRandom=r1.nextInt(1,7);
            int colourRandom=r1.nextInt(1,5);
            botdeck[i].CardColour=colourRandom;
            botdeck[i].CardType=1;
            if(signRandom==1) botdeck[i].CardValue=valueRandom;
            else botdeck[i].CardValue=-1*valueRandom;
        }
        for (int i = botdeck.length-2; i < botdeck.length ; i++) {
            int percentRandom=r1.nextInt(1,11);
            if(percentRandom<=2){
                int coinflip=r1.nextInt(1,3);
                if(coinflip==1) botdeck[i].CardType=2;
                else botdeck[i].CardType=3;
            }
            else{
                int signRandom=r1.nextInt(2);
                int valueRandom=r1.nextInt(1,7);
                int colourRandom=r1.nextInt(1,5);
                botdeck[i].CardColour=colourRandom;
                botdeck[i].CardType=1;
                if(signRandom==1) botdeck[i].CardValue=valueRandom;
                else botdeck[i].CardValue=-1*valueRandom;
            }
        }
        CardDeck[] botdeckreturn=new CardDeck[4];
        int a=10;
        int b=1;
        for (int i = 0; i < botdeckreturn.length; i++) {
            int randomselector=r1.nextInt(a);
            botdeckreturn[i]=botdeck[randomselector];
            a--;
            for (int k = randomselector; k < botdeck.length-b; k++) {
                botdeck[k]=botdeck[k+1];
            }
            b++;
        }
        return botdeckreturn;
    }
}