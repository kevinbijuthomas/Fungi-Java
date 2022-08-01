package board;
import java.util.ArrayList;
import cards.*;

public class Hand implements Displayable {
  private ArrayList<Card> handList = new ArrayList<Card>();

  @Override
  public void add(Card c) {
    this.handList.add(c);
  }

  @Override
  public int size() {
    return this.handList.size();
  }

  @Override
  public Card getElementAt(int i) {
    return this.handList.get(i);
  }

  @Override
  public Card removeElement(int i) {
    return this.handList.remove(i);
  }

}
