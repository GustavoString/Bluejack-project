import java.util.Random;

public class CardDeck {
    int CardType;
    //signed=1, flip=2, double=3, no card=0
    int CardColour;
    //blue=1, green=2, red=3, yellow=4
    int CardValue;
    //only used for signed cards

    public CardDeck(int type){
        //constructor for flip and double cards
        CardType=type;
    }

    public CardDeck(int type, int colour){
        CardType=type;
        CardColour=colour;
    }

    public CardDeck(int type, int colour, int value){
        //constructor for signed cards
        CardType=type;
        CardColour=colour;
        CardValue=value;
    }

    public static CardDeck[] shuffle(CardDeck[] unshuffled) {
        //function for shuffling decks
        Random r1=new Random(System.currentTimeMillis());
        CardDeck[] shuffled=new CardDeck[unshuffled.length];
        for (int i = 0; i < unshuffled.length; i++) {
            int rand=r1.nextInt(unshuffled.length-i);
            shuffled[i]=unshuffled[rand];
            for (int j = rand; j < unshuffled.length-(i+1); j++) {
                unshuffled[j]=unshuffled[j+1];
            }
        }
        return shuffled;
    }

    public static CardDeck[] generateGameDeck() {
        CardDeck[] GameDeck=new CardDeck[40];
        for (int i = 0; i < GameDeck.length; i++) {
            GameDeck[i].CardType=1;
            if(i<10){
                GameDeck[i].CardColour=1;
                GameDeck[i].CardValue=i+1;
            }
            else if(i<20){
                GameDeck[i].CardColour=2;
                GameDeck[i].CardValue=i-9;
            }
            else if(i<30){
                GameDeck[i].CardColour=3;
                GameDeck[i].CardValue=i-19;
            }
            else{
                GameDeck[i].CardColour=4;
                GameDeck[i].CardValue=i-29;
            }
        }
        return GameDeck;
    }
}
