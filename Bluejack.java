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
        for (int i = 0; i < playerdeck.length; i++) {
            playerdeck[i]=new CardDeck();
        }
        int j=0;
        CardDeck[] playerdeckUnchosen=new CardDeck[10];
        for (int i = 0; i < playerdeckUnchosen.length; i++) {
            playerdeckUnchosen[i]=new CardDeck();
        }
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
        //selecting 4 cards at random from playerdeckunchosen
        for(int u = 0; u < 4; u++){
            int randomSelector=r1.nextInt(10-u);
            playerdeck[u]=playerdeckUnchosen[randomSelector];
        
            //shift the remaining cards in playerdeckUnchosen
            for(int i=randomSelector; i<playerdeckUnchosen.length-1; i++){
                playerdeckUnchosen[i]=playerdeckUnchosen[i+1];
            }
        }
        //shifting the cards in gamedeck if they are empty to avoid empty spaces in the deck
        for (int i = 0; i < gamedeck.length-1; i++) {
            if(gamedeck[i].CardType==0){
                gamedeck[i].CardType=gamedeck[i+1].CardType;
                gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                gamedeck[i+1].CardType=0;
            }
        }
        //creating bot deck
        CardDeck[] botdeck=new CardDeck[4];
        for (int i = 0; i < botdeck.length; i++) {
            botdeck[i]=new CardDeck();
        }
        CardDeck[] botdeckUnchosen=new CardDeck[10];
        for (int i = 0; i < botdeckUnchosen.length; i++) {
            botdeckUnchosen[i]=new CardDeck();
        }
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
                int signRandom=r1.nextInt(1,3);
                int valueRandom=r1.nextInt(1,7);
                int colourRandom=r1.nextInt(1,5);
                botdeckUnchosen[i].CardColour=colourRandom;
                botdeckUnchosen[i].CardType=1;
                if(signRandom==1) botdeckUnchosen[i].CardValue=valueRandom;
                else botdeckUnchosen[i].CardValue=-1*valueRandom;
            }
        }
        //selecting 4 cards at random from botdeckUnchosen
        for(int u = 0; u < 4; u++){
            int randomSelector=r1.nextInt(10-u);
            botdeck[u]=botdeckUnchosen[randomSelector];
        
            //shift the remaining cards in botdeckUnchosen
            for(int i=randomSelector; i<botdeckUnchosen.length-1; i++){
                botdeckUnchosen[i]=botdeckUnchosen[i+1];
            }
        }
        for (int l = 0; l < gamedeck.length; l++) {
            for (int i = 0; i < gamedeck.length-1; i++) {
            if(gamedeck[i].CardType==0){
                gamedeck[i].CardType=gamedeck[i+1].CardType;
                gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                gamedeck[i+1].CardType=0;
            }
        }
        }
        //finished creating player hands at this point
        CardDeck[] playertable=new CardDeck[9];
        for (int i = 0; i < playertable.length; i++) {
            playertable[i]=new CardDeck();
        }
        for (int i = 0; i < playertable.length; i++) {
            playertable[i].CardType=0;
        }
        CardDeck[] bottable=new CardDeck[9];
        for (int i = 0; i < bottable.length; i++) {
            bottable[i]=new CardDeck();
        }
        for (int i = 0; i < bottable.length; i++) {
            bottable[i].CardType=0;
        }
        int PlayerWins=0;
        int BotWins=0;
        //placing first card on player table
        int cardsOnPlayerTable=0;
        playertable[cardsOnPlayerTable].CardColour=gamedeck[0].CardColour;
        playertable[cardsOnPlayerTable].CardType=gamedeck[0].CardType;
        playertable[cardsOnPlayerTable].CardValue=gamedeck[0].CardValue;
        cardsOnPlayerTable++;
        gamedeck[0].CardType=0;
        for (int l = 0; l < gamedeck.length; l++) {
            for (int i = 0; i < gamedeck.length-1; i++) {
                if(gamedeck[i].CardType==0){
                    gamedeck[i].CardType=gamedeck[i+1].CardType;
                    gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                    gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                    gamedeck[i+1].CardType=0;
                }
            }
        }
        //placing first card on bot table
        int cardsOnBotTable=0;
        bottable[cardsOnBotTable].CardColour=gamedeck[0].CardColour;
        bottable[cardsOnBotTable].CardType=gamedeck[0].CardType;
        bottable[cardsOnBotTable].CardValue=gamedeck[0].CardValue;
        cardsOnBotTable++;
        gamedeck[0].CardType=0;
        for (int l = 0; l < gamedeck.length; l++) {
            for (int i = 0; i < gamedeck.length-1; i++) {
                if(gamedeck[i].CardType==0){
                    gamedeck[i].CardType=gamedeck[i+1].CardType;
                    gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                    gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                    gamedeck[i+1].CardType=0;
                }
            }
        }
        for(int RoundCounter=0; RoundCounter<3; RoundCounter++){
            int PlayerSum=0;
            int BotSum=0;
            //player goes first for all 3 turns
            boolean isEnded=false;
            boolean BotStand=false;
            while(!(isEnded)){
                gamePrinter(playerdeck, botdeck, bottable, playertable);
                for (int i = 0; i < playertable.length; i++) {
                    PlayerSum=PlayerSum+playertable[i].CardValue;
                }
                if(PlayerSum>20){
                    System.out.println("Bust! Computer wins this round.");
                    BotWins++;
                    break;
                }
                System.out.print("\nTo end your turn enter 10.\nTo stand enter 0.\nTo pull a card from the deck to your board enter -1.\nOr to play one of the cards in your hand enter 1, 2, 3 or 4:");
                int playerinput=scan1.nextInt();
                if(playerinput==0){
                    isEnded=true;
                }
                else if(playerinput==-1){
                    playertable[cardsOnPlayerTable]=gamedeck[0];
                    cardsOnPlayerTable++;
                    gamedeck[0].CardType=0;
                    for (int l = 0; l < gamedeck.length; l++) {
                        for (int i = 0; i < gamedeck.length-1; i++) {
                            if(gamedeck[i].CardType==0){
                                gamedeck[i].CardType=gamedeck[i+1].CardType;
                                gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                                gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                                gamedeck[i+1].CardType=0;
                            }
                        }
                    }
                }
                else if(playerinput==10){
                    //IMPORTANT!!! add in usage of x2 and +/- cards too. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    boolean EndTurn=false;
                    if(BotStand) EndTurn=true;
                    //bot should play here but the player gets to play again once the bot is done as the player didn't stand.
                    //if the bot chooses to stand here don't forget to make BotStand=true
                    while(!EndTurn){
                        for (int i = 0; i < bottable.length; i++) {
                            BotSum=BotSum+bottable[i].CardValue;
                        }
                        int MinCardValue=1000;
                        int MinCardIndex=-1;
                        int MaxCardValue=-1000;
                        int MaxCardIndex=-1;
                        for (int i = 0; i < botdeck.length; i++) {
                            if(botdeck[i].CardType==1){
                                if(botdeck[i].CardValue<MinCardValue){
                                    MinCardValue=botdeck[i].CardValue;
                                    MinCardIndex=i;
                                }
                                if(botdeck[i].CardValue>MaxCardValue){
                                    MaxCardValue=botdeck[i].CardValue;
                                    MaxCardIndex=i;
                                }
                            }
                        }
                        if(BotSum==20){
                            EndTurn=true;
                            BotStand=true;
                            break;
                        }
                        boolean is20=false;
                        int TwentyCardIndex=-1;
                        //checking to see if any cards make the stack 20 in total.
                        for (int i = 0; i < botdeck.length; i++) {
                            if(botdeck[i].CardType==1&&botdeck[i].CardValue+BotSum==20){
                                TwentyCardIndex=i;
                                is20=true;
                                break;
                            }
                        }
                        if(is20){
                            //making the stack 20 as there is a card that makes it 20.
                            bottable[cardsOnBotTable].CardColour=botdeck[TwentyCardIndex].CardColour;
                            bottable[cardsOnBotTable].CardType=botdeck[TwentyCardIndex].CardType;
                            bottable[cardsOnBotTable].CardValue=botdeck[TwentyCardIndex].CardValue;
                            cardsOnBotTable++;
                            botdeck[TwentyCardIndex].CardType=0;
                            BotStand=true;
                            EndTurn=true;
                        }
                        else if(BotSum<=10){
                            //pull a card from the gamedeck here then see if it's possible for it to be made 20.
                            
                        }
                        else if(MinCardValue<0&&BotSum+MinCardValue<=10&&MinCardIndex!=-1){
                            //if there is a possibility of the sum going over 20 when a card is pulled but it can be corrected by playing a signed card.
                            //pulling a card from gamedeck
                            bottable[cardsOnBotTable].CardColour=gamedeck[0].CardColour;
                            bottable[cardsOnBotTable].CardType=gamedeck[0].CardType;
                            bottable[cardsOnBotTable].CardValue=gamedeck[0].CardValue;
                            cardsOnBotTable++;
                            gamedeck[0].CardType=0;
                            for (int l = 0; l < gamedeck.length; l++) {
                                for (int i = 0; i < gamedeck.length-1; i++) {
                                    if(gamedeck[i].CardType==0){
                                        gamedeck[i].CardType=gamedeck[i+1].CardType;
                                        gamedeck[i].CardColour=gamedeck[i+1].CardColour;
                                        gamedeck[i].CardValue=gamedeck[i+1].CardValue;
                                        gamedeck[i+1].CardType=0;
                                    }
                                }
                            }
                            BotSum=BotSum+bottable[cardsOnBotTable-1].CardValue;
                            if(BotSum==20){
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                            //checking to see if the sum is over 20 and playing the (-) value minimum card if it is
                            if(BotSum>20){
                                bottable[cardsOnBotTable].CardColour=botdeck[MinCardIndex].CardColour;
                                bottable[cardsOnBotTable].CardType=botdeck[MinCardIndex].CardType;
                                bottable[cardsOnBotTable].CardValue=botdeck[MinCardIndex].CardValue;
                                cardsOnBotTable++;
                                botdeck[MinCardIndex].CardType=0;
                                EndTurn=true;
                                break;
                            }
                            else{
                                //checking to see if any cards make the stack 20 in total.
                                for (int i = 0; i < botdeck.length; i++) {
                                    if(botdeck[i].CardType==1&&botdeck[i].CardValue+BotSum==20){
                                        TwentyCardIndex=i;
                                        is20=true;
                                        break;
                                    }
                                }
                                if(is20){
                                    //making the stack 20 as there is a card that makes it 20.
                                    bottable[cardsOnBotTable].CardColour=botdeck[TwentyCardIndex].CardColour;
                                    bottable[cardsOnBotTable].CardType=botdeck[TwentyCardIndex].CardType;
                                    bottable[cardsOnBotTable].CardValue=botdeck[TwentyCardIndex].CardValue;
                                    cardsOnBotTable++;
                                    botdeck[TwentyCardIndex].CardType=0;
                                    BotStand=true;
                                    EndTurn=true;
                                }
                                else{
                                    EndTurn=true;
                                    break;
                                }
                            }
                        }
                        else{
                            //check to see if there are any x2 or +/- cards and if anything can be done with them.
                            //if not stand.

                        }
                    }
                }
                else if(playerinput==1||playerinput==2||playerinput==3||playerinput==4){
                    switch(playerdeck[playerinput-1].CardType){
                        case 1:
                            playertable[cardsOnPlayerTable].CardColour=playerdeck[playerinput-1].CardColour;
                            playertable[cardsOnPlayerTable].CardType=playerdeck[playerinput-1].CardType;
                            playertable[cardsOnPlayerTable].CardValue=playerdeck[playerinput-1].CardValue;
                            cardsOnPlayerTable++;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 2:
                            playertable[cardsOnPlayerTable].CardValue=playertable[cardsOnPlayerTable].CardValue*-1;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 3:
                            playertable[cardsOnPlayerTable].CardValue=playertable[cardsOnPlayerTable].CardValue*2;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 0:
                            System.out.println("You should know better than playing a card that doesn't exist.");
                            break;
                        default:
                            System.out.println("Something went wrong at the playerdeck switch.");
                            break;
                    }
                }
                else System.out.println("Invalid input.");
            }
            if(isEnded&&!(BotStand)){
                //bot plays here
                //bot only plays here if the player chooses to stand and the bot doesn't choose to stand.

            }
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
        for (int i = 0; i < playertable.length; i++) {
            if(playertable[i].CardType!=0){
                isEmpty=false;
            }
        }
        if(isEmpty){
            System.out.print("Empty");
        }
        else{
            for (int i = 0; i < playertable.length; i++) {
                if(playertable[i].CardType!=0){
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
        }
        System.out.println();
        //printing player hand after this point
        System.out.print("Player hand:  ");
        for (int i = 0; i < playerdeck.length; i++) {
            switch(playerdeck[i].CardType){
                case 1:
                    switch(playerdeck[i].CardColour){
                        case 1:
                            if(playerdeck[i].CardValue>0) System.out.print((i+1)+": (B)+"+playerdeck[i].CardValue+" ");
                            else System.out.print((i+1)+": (B)"+playerdeck[i].CardValue+" ");
                            break;
                        case 2:
                            if(playerdeck[i].CardValue>0) System.out.print((i+1)+": (G)+"+playerdeck[i].CardValue+" ");
                            else System.out.print((i+1)+": (G)"+playerdeck[i].CardValue+" ");
                            break;
                        case 3:
                            if(playerdeck[i].CardValue>0) System.out.print((i+1)+": (R)+"+playerdeck[i].CardValue+" ");
                            else System.out.print((i+1)+": (R)"+playerdeck[i].CardValue+" ");
                            break;
                        case 4:
                            if(playerdeck[i].CardValue>0) System.out.print((i+1)+": (Y)+"+playerdeck[i].CardValue+" ");
                            else System.out.print((i+1)+": (Y)"+playerdeck[i].CardValue+" ");
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
        System.out.println();
    }
}