package board;
import java.util.ArrayList;
import cards.*;

public class CardList {
  private ArrayList<Card> cList = new ArrayList<Card>();

  public void CardList() {}

  public void add(Card c) {
    this.cList.add(0, c);
  }

  public int size() {
    return this.cList.size();
  }

  public Card getElementAt(int i) {
    return this.cList.get(i);
  }

  public Card removeCardAt(int i) {
    return this.cList.remove(8 - i);
  }
}
