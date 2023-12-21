import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class Bluejack{

    public static void main(String[] args) {
        Random r1=new Random(System.currentTimeMillis());
        Scanner scan1=new Scanner(System.in);
        System.out.print("Please enter your name:");
        String PlayerName=scan1.nextLine();
        //creating the game deck
        CardDeck[] gamedeck=new CardDeck[40];
        gamedeck=CardDeck.generateGameDeck();
        gamedeck=CardDeck.shuffle(gamedeck);
        //creating the player deck (hand)
        //generating player deck after this point
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
            playerdeckUnchosen[j].CardColour=gamedeck[i].CardColour;
            playerdeckUnchosen[j].CardType=gamedeck[i].CardType;
            playerdeckUnchosen[j].CardValue=gamedeck[i].CardValue;
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
        //selecting 4 cards at random from playerdeckUnchosen
        int p=0;
        for(int u = 0; u < 4; u++){
            int randomSelector=r1.nextInt(10-u);
            playerdeck[u].CardColour=playerdeckUnchosen[randomSelector].CardColour;
            playerdeck[u].CardType=playerdeckUnchosen[randomSelector].CardType;
            playerdeck[u].CardValue=playerdeckUnchosen[randomSelector].CardValue;

            if(randomSelector<=5-p){
                gamedeck[gamedeck.length-randomSelector-1].CardType=0;
                p++;
            }
        
            //shift the remaining cards in playerdeckUnchosen
            for(int i=randomSelector; i<playerdeckUnchosen.length-1; i++){
                playerdeckUnchosen[i]=playerdeckUnchosen[i+1];
            }
        }
        //shifting the cards in gamedeck if they are empty to avoid empty spaces in the deck
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
            botdeckUnchosen[j].CardColour=gamedeck[j].CardColour;
            botdeckUnchosen[j].CardType=gamedeck[j].CardType;
            botdeckUnchosen[j].CardValue=gamedeck[j].CardValue;
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
        p=0;
        for(int u = 0; u < 4; u++){
            int randomSelector=r1.nextInt(10-u);
            botdeck[u].CardColour=botdeckUnchosen[randomSelector].CardColour;
            botdeck[u].CardType=botdeckUnchosen[randomSelector].CardType;
            botdeck[u].CardValue=botdeckUnchosen[randomSelector].CardValue;

            if(randomSelector<=5-p){
                gamedeck[randomSelector].CardType=0;
                p++;
            }
        
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
        CardDeck[] bottable=new CardDeck[9];
        for (int i = 0; i < bottable.length; i++) {
            bottable[i]=new CardDeck();
        }
        int PlayerWins=0;
        int BotWins=0;
        for(int RoundCounter=0; RoundCounter<3; RoundCounter++){
            //placing first card on player table
            for (int i = 0; i < playertable.length; i++) {
                playertable[i].CardType=0;
            }
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
            for (int i = 0; i < bottable.length; i++) {
                bottable[i].CardType=0;
            }
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
            System.out.println("ROUND "+(RoundCounter+1)+":\n-----------------------------------------------");
            int PlayerSum=0;
            int BotSum=0;
            //player goes first for all 3 turns
            boolean isEnded=false;
            boolean BotStand=false;
            while(!(isEnded)){
                //!!!!!At the end of the player turn add something that checks if the player reached exactly 20 ONLY using blue cards and make the player autowin if this is the case.
                gamePrinter(playerdeck, botdeck, bottable, playertable);
                for (int i = 0; i < playertable.length; i++) {
                    PlayerSum=PlayerSum+playertable[i].CardValue;
                }
                System.out.print("\nTo end your turn enter 10.\nTo stand enter 0.\nTo pull a card from the deck to your board enter -1.\nOr to play one of the cards in your hand enter 1, 2, 3 or 4:");
                int playerinput=scan1.nextInt();
                System.out.println("\n\n");
                if(playerinput==0){
                    isEnded=true;
                    break;
                }
                else if(playerinput==-1){
                    playertable[cardsOnPlayerTable].CardType=gamedeck[0].CardType;
                    playertable[cardsOnPlayerTable].CardColour=gamedeck[0].CardColour;
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
                }
                else if(playerinput==10){
                    //add the all blue card checker below
                    if(isAllBlueAnd20(playertable)){
                        //player autowins game here
                        System.out.println("Player Wins!");
                        gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 1);
                        scan1.close();
                        return;
                    }
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
                        boolean IsDoubleCard=false;
                        boolean IsFlipCard=false;
                        int FlipCardIndex=-1;
                        int DoubleCardIndex=-1;
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
                            if(botdeck[i].CardType==2){
                                IsFlipCard=true;
                                FlipCardIndex=i;
                            }
                            if(botdeck[i].CardType==3){
                                IsDoubleCard=true;
                                DoubleCardIndex=i;
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
                        BotSum=sumCalculator(bottable);
                        if(is20){
                            //making the stack 20 as there is a card that makes it 20.
                            bottable[cardsOnBotTable].CardColour=botdeck[TwentyCardIndex].CardColour;
                            bottable[cardsOnBotTable].CardType=botdeck[TwentyCardIndex].CardType;
                            bottable[cardsOnBotTable].CardValue=botdeck[TwentyCardIndex].CardValue;
                            cardsOnBotTable++;
                            botdeck[TwentyCardIndex].CardType=0;
                            BotStand=true;
                            EndTurn=true;
                            break;
                        }
                        else if(IsDoubleCard&&bottable[cardsOnBotTable-1].CardValue+BotSum==20){
                            //checking to see if there is a double card and if there is, if it can make the total 20.
                            bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*2;
                            BotSum=sumCalculator(bottable);
                            botdeck[DoubleCardIndex].CardType=0;
                            BotStand=true;
                            EndTurn=true;
                            break;
                        }
                        else if(BotSum<=12){
                            //pull a card from the gamedeck here then see if it's possible for it to be made 20.
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
                            BotSum=sumCalculator(bottable);
                            if(BotSum==20){
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                        }
                        boolean isPlayableCard=false;
                        BotSum=sumCalculator(bottable);
                        int PlayableCardValue=0;
                        int PlayableCardIndex=0;
                        for (int i = 0; i < botdeck.length; i++) {
                            if(botdeck[i].CardType==1){
                                if(botdeck[i].CardValue>PlayableCardValue&&botdeck[i].CardValue+BotSum<=20){
                                    isPlayableCard=true;
                                    PlayableCardValue=botdeck[i].CardValue;
                                    PlayableCardIndex=i;
                                }
                            }
                        }
                        if(isPlayableCard&&BotSum>=12){
                            bottable[cardsOnBotTable].CardColour=botdeck[PlayableCardIndex].CardColour;
                            bottable[cardsOnBotTable].CardType=botdeck[PlayableCardIndex].CardType;
                            bottable[cardsOnBotTable].CardValue=botdeck[PlayableCardIndex].CardValue;
                            cardsOnBotTable++;
                            botdeck[PlayableCardIndex].CardType=0;
                            EndTurn=true;
                            BotSum=sumCalculator(bottable);
                            if(BotSum<=20||BotSum>=18){
                                BotStand=true;
                            }
                            break;
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
                            //check to see if there are any +/- cards and if anything can be done with them.
                            //if not stand.
                            if(IsFlipCard){
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
                                BotSum=sumCalculator(bottable);
                                if(BotSum==20){
                                    BotStand=true;
                                    EndTurn=true;
                                    break;
                                }
                                else if(BotSum>20){
                                    bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*-1;
                                    botdeck[FlipCardIndex].CardType=0;
                                    BotSum=BotSum+bottable[cardsOnBotTable-1].CardValue;
                                    BotStand=true;
                                    EndTurn=true;
                                    break;
                                }
                            }
                            else{
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                        }
                    }
                }
                else if(playerinput==1||playerinput==2||playerinput==3||playerinput==4){
                    //player plays one of the cards in their hand here
                    switch(playerdeck[playerinput-1].CardType){
                        case 1:
                            playertable[cardsOnPlayerTable].CardColour=playerdeck[playerinput-1].CardColour;
                            playertable[cardsOnPlayerTable].CardType=playerdeck[playerinput-1].CardType;
                            playertable[cardsOnPlayerTable].CardValue=playerdeck[playerinput-1].CardValue;
                            cardsOnPlayerTable++;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 2:
                            playertable[cardsOnPlayerTable-1].CardValue=playertable[cardsOnPlayerTable-1].CardValue*-1;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 3:
                            playertable[cardsOnPlayerTable-1].CardValue=playertable[cardsOnPlayerTable-1].CardValue*2;
                            playerdeck[playerinput-1].CardType=0;
                            break;
                        case 0:
                            System.out.println("You should know better than playing a card that doesn't exist.");
                            break;
                        default:
                            System.out.println("Something went wrong at the playerdeck switch.");
                            break;
                    }
                    gamePrinter(playerdeck, botdeck, bottable, playertable);
                    System.out.print("To stand enter 0, to end your turn enter 1:");
                    playerinput=scan1.nextInt();
                    if(playerinput==1){
                        //bot plays here but player gets to play again as the player only ended the turn and did not stand.
                        if(isAllBlueAnd20(playertable)){
                            //player autowins game here
                            System.out.println("Player Wins!");
                            gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 1);
                            scan1.close();
                            return;
                        }
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
                            boolean IsDoubleCard=false;
                            boolean IsFlipCard=false;
                            int FlipCardIndex=-1;
                            int DoubleCardIndex=-1;
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
                                if(botdeck[i].CardType==2){
                                    IsFlipCard=true;
                                    FlipCardIndex=i;
                                }
                                if(botdeck[i].CardType==3){
                                    IsDoubleCard=true;
                                    DoubleCardIndex=i;
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
                            BotSum=sumCalculator(bottable);
                            if(is20){
                                //making the stack 20 as there is a card that makes it 20.
                                bottable[cardsOnBotTable].CardColour=botdeck[TwentyCardIndex].CardColour;
                                bottable[cardsOnBotTable].CardType=botdeck[TwentyCardIndex].CardType;
                                bottable[cardsOnBotTable].CardValue=botdeck[TwentyCardIndex].CardValue;
                                cardsOnBotTable++;
                                botdeck[TwentyCardIndex].CardType=0;
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                            else if(IsDoubleCard&&bottable[cardsOnBotTable-1].CardValue+BotSum==20){
                                //checking to see if there is a double card and if there is, if it can make the total 20.
                                bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*2;
                                BotSum=sumCalculator(bottable);
                                botdeck[DoubleCardIndex].CardType=0;
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                            else if(BotSum<=12){
                                //pull a card from the gamedeck here then see if it's possible for it to be made 20.
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
                                BotSum=sumCalculator(bottable);
                                if(BotSum==20){
                                    BotStand=true;
                                    EndTurn=true;
                                    break;
                                }
                            }
                            boolean isPlayableCard=false;
                            BotSum=sumCalculator(bottable);
                            int PlayableCardValue=0;
                            int PlayableCardIndex=0;
                            for (int i = 0; i < botdeck.length; i++) {
                                if(botdeck[i].CardType==1){
                                    if(botdeck[i].CardValue>PlayableCardValue&&botdeck[i].CardValue+BotSum<=20){
                                        isPlayableCard=true;
                                        PlayableCardValue=botdeck[i].CardValue;
                                        PlayableCardIndex=i;
                                    }
                                }
                            }
                            if(isPlayableCard&&BotSum>=12){
                                bottable[cardsOnBotTable].CardColour=botdeck[PlayableCardIndex].CardColour;
                                bottable[cardsOnBotTable].CardType=botdeck[PlayableCardIndex].CardType;
                                bottable[cardsOnBotTable].CardValue=botdeck[PlayableCardIndex].CardValue;
                                cardsOnBotTable++;
                                botdeck[PlayableCardIndex].CardType=0;
                                EndTurn=true;
                                BotSum=sumCalculator(bottable);
                                if(BotSum<=20||BotSum>=18){
                                    BotStand=true;
                                }
                                break;
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
                                //check to see if there are any +/- cards and if anything can be done with them.
                                //if not stand.
                                if(IsFlipCard){
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
                                    BotSum=sumCalculator(bottable);
                                    if(BotSum==20){
                                        BotStand=true;
                                        EndTurn=true;
                                        break;
                                    }
                                    else if(BotSum>20){
                                        bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*-1;
                                        botdeck[FlipCardIndex].CardType=0;
                                        BotSum=BotSum+bottable[cardsOnBotTable-1].CardValue;
                                        BotStand=true;
                                        EndTurn=true;
                                        break;
                                    }
                                }
                                else{
                                    BotStand=true;
                                    EndTurn=true;
                                    break;
                                }
                            }
                        }
                        gamePrinter(playerdeck, botdeck, bottable, playertable);
                    }
                    else if(playerinput==0){
                        isEnded=true;
                        break;
                    }
                    else{
                        System.out.println("Invalid Input.");
                        scan1.close();
                        return;
                    }
                }
                else System.out.println("Invalid input.");
            }
            if(is20(playertable)&&!isAllBlueAnd20(playertable)&&!is20(bottable)){
                //player is at exactly 20 so wins this round
                //does nothing here as the player wins are added to somewhere after the following else if.
            }
            else if(isEnded&&!(BotStand)){
                //add the all blue checker below
                if(isAllBlueAnd20(playertable)){
                    //player autowins game here
                    System.out.println("Player Wins!");
                    gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 1);
                    scan1.close();
                    return;
                }
                //bot plays here
                //bot only plays here if the player chooses to stand and the bot doesn't choose to stand.
                boolean EndTurn=false;
                if(BotStand) EndTurn=true;
                //bot should play here but the player gets to play again once the bot is done as the player didn't stand.              
                //if the bot chooses to stand here don't forget to make BotStand=true
                    while(!EndTurn){
                        gamePrinter(playerdeck, botdeck, bottable, playertable);
                        for (int i = 0; i < bottable.length; i++) {
                            BotSum=BotSum+bottable[i].CardValue;
                        }
                        int MinCardValue=1000;
                        int MinCardIndex=-1;
                        int MaxCardValue=-1000;
                        int MaxCardIndex=-1;
                        boolean IsDoubleCard=false;
                        boolean IsFlipCard=false;
                        int FlipCardIndex=-1;
                        int DoubleCardIndex=-1;
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
                            if(botdeck[i].CardType==2){
                                IsFlipCard=true;
                                FlipCardIndex=i;
                            }
                            if(botdeck[i].CardType==3){
                                IsDoubleCard=true;
                                DoubleCardIndex=i;
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
                        BotSum=sumCalculator(bottable);
                        if(is20){
                            //making the stack 20 as there is a card that makes it 20.
                            bottable[cardsOnBotTable].CardColour=botdeck[TwentyCardIndex].CardColour;
                            bottable[cardsOnBotTable].CardType=botdeck[TwentyCardIndex].CardType;
                            bottable[cardsOnBotTable].CardValue=botdeck[TwentyCardIndex].CardValue;
                            cardsOnBotTable++;
                            botdeck[TwentyCardIndex].CardType=0;
                            BotStand=true;
                            EndTurn=true;
                            break;
                        }
                        else if(IsDoubleCard&&bottable[cardsOnBotTable-1].CardValue+BotSum==20){
                            //checking to see if there is a double card and if there is, if it can make the total 20.
                            bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*2;
                            BotSum=sumCalculator(bottable);
                            botdeck[DoubleCardIndex].CardType=0;
                            BotStand=true;
                            EndTurn=true;
                            break;
                        }
                        else if(BotSum<=12){
                            //pull a card from the gamedeck here then see if it's possible for it to be made 20.
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
                            BotSum=sumCalculator(bottable);
                            if(BotSum==20){
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                        }
                        //!!!!!!here add something that checks if there is a card in the bot hand that doesn't make it 20 but doesn't go over 20 when played.
                        boolean isPlayableCard=false;
                        BotSum=sumCalculator(bottable);
                        int PlayableCardValue=0;
                        int PlayableCardIndex=0;
                        for (int i = 0; i < botdeck.length; i++) {
                            if(botdeck[i].CardType==1){
                                if(botdeck[i].CardValue>PlayableCardValue&&botdeck[i].CardValue+BotSum<=20){
                                    isPlayableCard=true;
                                    PlayableCardValue=botdeck[i].CardValue;
                                    PlayableCardIndex=i;
                                }
                            }
                        }
                        if(isPlayableCard&&BotSum>=12){
                            bottable[cardsOnBotTable].CardColour=botdeck[PlayableCardIndex].CardColour;
                            bottable[cardsOnBotTable].CardType=botdeck[PlayableCardIndex].CardType;
                            bottable[cardsOnBotTable].CardValue=botdeck[PlayableCardIndex].CardValue;
                            cardsOnBotTable++;
                            botdeck[PlayableCardIndex].CardType=0;
                            EndTurn=true;
                            BotStand=true;
                            break;
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
                            BotSum=sumCalculator(bottable);
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
                        //check to see if there are any +/- cards and if anything can be done with them.
                        //if not stand.
                        if(IsFlipCard){
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
                            else if(BotSum>20){
                                bottable[cardsOnBotTable-1].CardValue=bottable[cardsOnBotTable-1].CardValue*-1;
                                botdeck[FlipCardIndex].CardType=0;
                                BotSum=BotSum+bottable[cardsOnBotTable-1].CardValue;
                                BotStand=true;
                                EndTurn=true;
                                break;
                            }
                        }
                        else{
                            BotStand=true;
                            EndTurn=true;
                            break;
                        }
                    }
                }
            }
            gamePrinter(playerdeck, botdeck, bottable, playertable);
            if(isAllBlueAnd20(bottable)&&!isAllBlueAnd20(playertable)){
                //bot autowins game here
                System.out.println("Bot Wins!");
                gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 2);
                scan1.close();
                return;
            }
            else if(isAllBlueAnd20(playertable)&&!isAllBlueAnd20(bottable)){
                //player auto wins here
                System.out.println("Player Wins!");
                gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 1);
                scan1.close();
                return;
            }
            PlayerSum=sumCalculator(playertable);
            BotSum=sumCalculator(bottable);
            if(BotSum==20&&PlayerSum==20){
                //draw
                System.out.println("Draw this round.");
            }
            else if(BotSum==20&&PlayerSum!=20){
                BotWins++;
                System.out.println("Bot wins this round.");
            }
            else if(BotSum!=20&&PlayerSum==20){
                PlayerWins++;
                System.out.println("Player wins this round.");
            }
            else if(BotSum>20&&PlayerSum<=20){
                PlayerWins++;
                System.out.println("Player wins this round.");
            }
            else if(PlayerSum>20&&BotSum<=20){
                BotWins++;
                System.out.println("Bot wins this round.");
            }
            else if(BotSum<20&&PlayerSum<20){
                BotSum=20-BotSum;
                PlayerSum=20-PlayerSum;
                if(PlayerSum<BotSum){
                    PlayerWins++;
                    System.out.println("Player wins this round.");
                }
                else if(PlayerSum>BotSum){
                    BotWins++;
                    System.out.println("Bot wins this round.");
                }
                else{
                    //draw
                    System.out.println("Draw this round.");
                }
            }
            else{
                //both are over 20 so draw
                System.out.println("Draw this round.");
            }
        }
        if(PlayerWins>BotWins){
            //player wins here
            System.out.println("Player Wins!");
            gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 0);
        }
        else if(PlayerWins<BotWins){
            //bot wins here
            System.out.println("Bot wins!");
            gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 0);
        }
        else{
            //both sides draw here
            System.out.println("Draw!");
            gameHistoryRecorder(PlayerWins, BotWins, PlayerName, 0);
        }
        scan1.close();
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
                    System.out.print((i+1)+": +/- ");
                    break;
                case 3:
                    System.out.print((i+1)+": x2 ");
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

    public static boolean isAllBlueAnd20(CardDeck[] table){
        int amountOfCardsOnTable=0;
        for (int i = 0; i < table.length; i++) {
            if(table[i].CardType!=0){
                amountOfCardsOnTable++;
            }
        }
        boolean isNotBlue=false;
        int sum=0;
        for (int i = 0; i < amountOfCardsOnTable; i++) {
            sum=sum+table[i].CardValue;
            if(table[i].CardColour!=1){
                isNotBlue=true;
            }
        }
        if(sum==20&&!isNotBlue){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean is20(CardDeck[] table){
        int sum=0;
        for (int i = 0; i < table.length; i++) {
            if(table[i].CardType==1){
                sum=sum+table[i].CardValue;
            }
        }
        if(sum==20){
            return true;
        }
        else{
            return false;
        }
    }

    public static int sumCalculator(CardDeck[] table){
        int sum=0;
        for (int i = 0; i < table.length; i++) {
            if(table[i].CardType==1){
                sum=sum+table[i].CardValue;
            }
        }
        return sum;
    }

    public static void gameHistoryRecorder(int PlayerWins, int BotWins, String PlayerName, int Bluejack){
        String record;
        String date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        if (Bluejack==1){
            record=PlayerName+": Bluejack (made a total of 20 by only using blue cards) - Computer: "+BotWins+", "+date;
        }
        else if(Bluejack==0){
            record=PlayerName+": "+PlayerWins+" - Computer: "+BotWins+", "+date;
        }
        else if(Bluejack==2){
            record=PlayerName+": "+PlayerWins+" - Computer: Bluejack (made a total of 20 by only using blue cards), "+date;
        } 
        else{
            System.out.println("Invalid Bluejack value. Please enter 0, 1, or 2.");
            return;
        }

        try {
            Scanner reader=new Scanner(Paths.get("GameHistory.txt"));
            String[] history=new String[10];
            int count=0;
            while (reader.hasNextLine()&&count<10){
                history[count++]=reader.nextLine();
            }
            reader.close();

            FileWriter fw=new FileWriter("GameHistory.txt");
            Formatter formatter=new Formatter(fw);

            int start;
            if (count>=10) {
                start=1;
            } else {
                start=0;
            }

            for (int i = start; i < count; i++) {
                formatter.format("%s\n", history[i]);
            }
            formatter.format("%s\n", record);
            formatter.close();
        } catch (Exception e) {
            System.out.println("An error occurred: "+ e);
        }
    }
}