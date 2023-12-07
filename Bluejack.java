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
        CardDeck[] playertable=new CardDeck[9];
        for (int i = 0; i < playertable.length; i++) {
            playertable[i].CardType=0;
        }
        CardDeck[] bottable=new CardDeck[9];
        for (int i = 0; i < bottable.length; i++) {
            bottable[i].CardType=0;
        }
        int PlayerWins=0;
        int BotWins=0;
        for(int TurnCounter=0; TurnCounter<3; TurnCounter++){
            //player goes first for all 3 turns
            //first turn
            
            //second turn
            //third turn
        }
        if(PlayerWins>BotWins){
            //player wins here
        }
        else if(PlayerWins<BotWins){
            //bot wins here
        }
        else{
            //both sides draw draw here
        }
    }
    
    public static void gamePrinter(CardDeck[] playerdeck, CardDeck[] botdeck, CardDeck[] bottable, CardDeck[] playertable){
        //printing computer hand after this point
        System.out.print("Computer Hand:    ");
        for (int i = 0; i < botdeck.length; i++) {
            if(botdeck[i].CardType!=0) System.out.print("X ");
            else System.out.print("O ");
        }
        System.out.println();
        //printing bot table after this point
        System.out.print("Computer Board:   ");
        boolean isEmpty=true;
        int amountOfCardsOnTable=0;
        for (int i = 0; i < bottable.length; i++) {
            if(bottable[i].CardType!=0){
                isEmpty=false;
                amountOfCardsOnTable++;
            }
        }
        if(isEmpty){
            System.out.print("Empty");
        }
        else{
            for (int i = 0; i < amountOfCardsOnTable; i++) {
                switch(bottable[i].CardColour){
                    case 1:
                        if(bottable[i].CardValue>0) System.out.print("(B)+"+bottable[i].CardValue+" ");
                        else System.out.print("(B)"+bottable[i].CardValue+" ");
                        break;
                    case 2:
                        if(bottable[i].CardValue>0) System.out.print("(G)+"+bottable[i].CardValue+" ");
                        else System.out.print("(G)"+bottable[i].CardValue+" ");
                        break;
                    case 3:
                        if(bottable[i].CardValue>0) System.out.print("(R)+"+bottable[i].CardValue+" ");
                        else System.out.print("(R)"+bottable[i].CardValue+" ");
                        break;
                    case 4:
                        if(bottable[i].CardValue>0) System.out.print("(Y)+"+bottable[i].CardValue+" ");
                        else System.out.print("(Y)"+bottable[i].CardValue+" ");
                        break;
                    default:
                    System.out.println("Something went wrong at the bottable cardcolour switch.");
                    break;
                }
            }
        }
        System.out.println();
        //printing player table after this point
        System.out.print("Player Board:   ");
        isEmpty=true;
        amountOfCardsOnTable=0;
        for (int i = 0; i < playertable.length; i++) {
            if(playertable[i].CardType!=0){
                isEmpty=false;
                amountOfCardsOnTable++;
            }
        }
        if(isEmpty){
            System.out.print("Empty");
        }
        else{
            for (int i = 0; i < amountOfCardsOnTable; i++) {
                switch(playertable[i].CardColour){
                    case 1:
                        if(playertable[i].CardValue>0) System.out.print("(B)+"+playertable[i].CardValue+" ");
                        else System.out.print("(B)"+playertable[i].CardValue+" ");
                        break;
                    case 2:
                        if(playertable[i].CardValue>0) System.out.print("(G)+"+playertable[i].CardValue+" ");
                        else System.out.print("(G)"+playertable[i].CardValue+" ");
                        break;
                    case 3:
                        if(playertable[i].CardValue>0) System.out.print("(R)+"+playertable[i].CardValue+" ");
                        else System.out.print("(R)"+playertable[i].CardValue+" ");
                        break;
                    case 4:
                        if(playertable[i].CardValue>0) System.out.print("(Y)+"+playertable[i].CardValue+" ");
                        else System.out.print("(Y)"+playertable[i].CardValue+" ");
                        break;
                    default:
                        System.out.println("Something went wrong at the playertable cardcolour switch.");
                        break;
                }
            }
        }
        System.out.println();
        //printing player hand after this point
        for (int i = 0; i < playerdeck.length; i++) {
            switch(playerdeck[i].CardType){
                case 1:
                    switch(playerdeck[i].CardColour){
                        case 1:
                            if(playerdeck[i].CardValue>0) System.out.print("(B)+"+playerdeck[i].CardValue+" ");
                            else System.out.print("(B)"+playerdeck[i].CardValue+" ");
                            break;
                        case 2:
                            if(playerdeck[i].CardValue>0) System.out.print("(G)+"+playerdeck[i].CardValue+" ");
                            else System.out.print("(G)"+playerdeck[i].CardValue+" ");
                            break;
                        case 3:
                            if(playerdeck[i].CardValue>0) System.out.print("(R)+"+playerdeck[i].CardValue+" ");
                            else System.out.print("(R)"+playerdeck[i].CardValue+" ");
                            break;
                        case 4:
                            if(playerdeck[i].CardValue>0) System.out.print("(Y)+"+playerdeck[i].CardValue+" ");
                            else System.out.print("(Y)"+playerdeck[i].CardValue+" ");
                            break;
                        default:
                            System.out.println("Something went wrong at the playerdeck cardcolour switch for the player hand printer.");
                            break;
                    }
                    break;
                case 2:
                    System.out.print("+/- ");
                    break;
                case 3:
                    System.out.print("x2 ");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Something went wrong at the player hand playerdeck card type switch");
                    break;
            }
        }
    }
}