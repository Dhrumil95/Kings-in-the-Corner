package dpate85Proj1;


public class CardPile {
    private static final int MAX_SIZE = 13;

    Card []arr;
    boolean anyCard;
    int index;
    
    CardPile(boolean flag)
    {
        arr = new Card[13];
        this.anyCard = flag;
        index = 0;
    }
    
    public int getSize()
    {
        return index;
    }
    
    public Card getTop()
    {
        return arr[index-1];
    }
    
    public void removeTop()
    {
        for(int i = 0; i < index - 1; i++)
        {
            arr[i] = arr[i+1];
        }
        index--;
    }
    
    public boolean addCard(Card c)
    {
        if(index != 0)
        {
            if(isPlaceable(c,arr[index-1]) && index != MAX_SIZE)
            {

                arr[index] = c;
                index++;
                return true;
            }
        }
        else
        {
            if(anyCard == true || c.rank == Card.KING)
            {
                arr[index] = c;
                index++;
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isPlaceable(Card a, Card b)
    {
        if(a.rank == b.rank-1 && a.isBlack != b.isBlack)
            return true;
        else
            return false;
    }
    
    public void print()
    {
        for(int i = 0; i < index; i++)
        {
           arr[i].print();
           System.out.print( " " );
        }
    }
}
