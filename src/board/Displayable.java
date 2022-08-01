package board;
import cards.*;

public interface Displayable {
  void add(Card c);
  int size();
  Card getElementAt(int i);
  Card removeElement(int i);
}
