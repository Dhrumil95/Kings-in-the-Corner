package dpate85Proj1;


public class Card {
        public static final int KING = 13;
        public static final int QUEEN = 12;
        public static final int JACK = 11;
        public static final int TEN = 10;
        public static final int ACE = 1;
         
        int rank;
        char suit;
        boolean isBlack;
       
       
       Card()
       {
           rank = 0;
           suit = 0;
           isBlack = false;
       }
       public String getCard()
       {
           String c = new String();
           
           if(rank == TEN)
               c += 'T';
           else if(rank == ACE)
               c += 'A';
           else if(rank == JACK)
               c += 'J';
           else if(rank == QUEEN)
               c += 'Q';
           else if(rank == KING)
               c += 'K';
           else
               c += rank;
           c += suit;
           
           return c;
       }
       public void setCard(int rank, char suit, boolean isBlack)
       {
           this.rank = rank;
           this.suit = suit;
           this.isBlack = isBlack;
       }
       public void setRank(int rank)
       {
           this.rank = rank;
       }
       
       public void setSuit(char suit)
       {
           this.suit = suit;
       }
       
       public void print()
       {
           String output = new String();
           
           if(rank == KING)
           {
               output += 'K';
           }
           else if(rank == QUEEN)
           {
               output += 'Q';
           }
           else if(rank == JACK)
           {
               output += 'J';
           }
           else if(rank == ACE)
           {
               output += 'A';
           }
           else if(rank == TEN)
           {
               output += 'T';
           }
           else
               output += rank;
           
           output += suit;
           
           System.out.print(output);
       }
       
}


