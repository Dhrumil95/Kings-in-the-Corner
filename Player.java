package dpate85Proj1;


public class Player {
    public static final int MAX_SIZE = 54;
    Card []hand;
    int index;
    int score;
    Player()
    {
        index = 0;
        score = 0;
        hand = new Card[MAX_SIZE];
    }
    
    public int calcScore()
    {
        for(int i = 0; i < index; i++)
        {
            if(hand[i].rank == Card.KING)
            {
                score += 10;
            }
            else
                score += 1;
        }
        return score;
    }
    
    public void addToHand(Card c)
    {
        hand[index] = c;
        index++;
    }
    
    public int numCards()
    {
        return index;
    }
    
    public void printHand()
    {
        for(int i = 0; i < index; i++)
        {
           hand[i].print();
           System.out.print( " " );
        }
    }
    
    public boolean checkIfExists(String input)
    {
        for(int i = 0; i < index; i++)
        {
            if(input.equals(hand[i].getCard()))
                return true;
        }
        
        return false;
    }
    
    public Card getIndex(String input)
    {
        Card c = new Card();
        for(int i = 0; i < index; i++)
        {
            if(input.equals(hand[i].getCard()))
                return hand[i];
        }
        return c;
    }
    
    public boolean remove(Card c)
    {
        int loc = 0;
        boolean found = false;
        
        for(int i = 0; i < index; i++)
        {
            if(c == hand[i])
            {
               loc = i;
               found = true;
               break;
            }
        }
        
        if(found)
        {
            for(int i = loc; i < index - 1; i++)
            {
                hand[i] = hand[i+1];
            }
            index--;
        }
        return found;
    }
    
    public boolean kingExists()
    {
        for(int i = 0; i < index; i++)
        {
            if(hand[i].rank == Card.KING)
            {
                return true;
            }
        }
        return false;
    }
    
     public Card getKing()
    {
        Card c = new Card();
        
        for(int i = 0; i < index; i++)
        {
            if(hand[i].rank == Card.KING)
            {
                return hand[i];
            }
        }
        return c;
    }
     
    public Card getTop()
    {
        return hand[index-1];
    }
    
    public void removeTop()
    {
        for(int i = 0; i < index - 1; i++)
        {
            hand[i] = hand[i+1];
        }
        index--;
    }
    
    public int getSize()
    {
        return index;
    }
    
    public Card getCard(int index)
    {
        Card c = new Card();
        if(index < this.index)
            return hand[index];
        else
            return c;
    }
}
