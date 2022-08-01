package board;
import java.util.ArrayList;
import cards.*;

public class Display implements Displayable {
  private ArrayList<Card> displayList = new ArrayList<Card>();

  @Override
  public void add(Card c) {
    this.displayList.add(c);
  }

  @Override
  public int size() {
    return this.displayList.size();
  }

  @Override
  public Card getElementAt(int i) {
    return this.displayList.get(i);
  }

  @Override
  public Card removeElement(int i) {
    return this.displayList.remove(i);
  }

}
