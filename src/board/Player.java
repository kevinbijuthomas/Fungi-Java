package board;
import java.util.ArrayList;
import cards.*;

public class Player {
  private Hand h = new Hand();;
  private Display d = new Display();;
  private int score = 0;
  private int handlimit = 8;
  private int sticks = 0;

  public Player() {
    this.d.add(new Pan());
  }

  public int getScore() {
    return this.score;
  }

  public int getHandLimit() {
    return this.handlimit;
  }

  public int getStickNumber() {
    return this.sticks;
  }

  public void addSticks(int i) {
    this.sticks = this.sticks + i;
    for(int j = 0; j < i; j++){
      d.add(new Stick());
    }
  }

  public void removeSticks(int i) {
    this.sticks = this.sticks - i;
    for (int j = 0; j < i; j++) {
      for (int k = 0; k < d.size(); k++) {
        if (d.getElementAt(k).getType() == CardType.STICK) {
              d.removeElement(k);
              break;
        }
      }
    }
  }

  public Hand getHand() {
    return this.h;
  }

  public Display getDisplay() {
    return this.d;
  }

  public void addCardtoHand(Card c) {
    if (c.getType() == CardType.BASKET) {
      this.d.add(c);
      this.handlimit += 2;
    } else {
      this.h.add(c);
    }
  }

  public void addCardtoDisplay(Card c) {
    this.d.add(c);
  }




  // FIVE ACTIONS

  //ACTION 1
  public boolean takeCardFromTheForest(int i) {
    // valid number from forest
    if (i > 0 && i < 9) {
      // check handlimit
      if ((h.size()) + 1 > handlimit) {
        return false;
      } else if (i == 1 || i == 2) { // feet cards
        if (Board.getForest().getElementAt(8 - i).getType() == CardType.BASKET) {
          d.add(Board.getForest().getElementAt(8 - i));
          handlimit = handlimit + 2;
        } else {
          h.add(Board.getForest().getElementAt(8 - i));
        }
        Board.getForest().removeCardAt(i);
        return true;
      } else { // sticks checking if available
          if (sticks < (i - 2)) {
            return false;
          } else {
            if (Board.getForest().getElementAt(8 - i).getType() == CardType.BASKET) {
              d.add(Board.getForest().getElementAt(8 - i));
              handlimit = handlimit + 2;
            } else {
              h.add(Board.getForest().getElementAt(8 - i));
            }
            Board.getForest().removeCardAt(i);
            this.removeSticks(i - 2);
            return true;
          }
      }
    }
    return false;
  }

  //ACTION 2
  public boolean takeFromDecay() {
    if (Board.getDecayPile().size() == 0) { // empty decay pile
      return false;
    }
    int numberBasketsDisplay = 0;
    int numberBasketsDecay = 0;
    for (int i = 0; i < d.size(); i++) {
      if (d.getElementAt(i).getType() == CardType.BASKET) {
        numberBasketsDisplay += 1;
      }
    }
    for (int i = 0; i < Board.getDecayPile().size(); i++) { // count number of baskets
      if (Board.getDecayPile().get(i).getType() == CardType.BASKET) {
        numberBasketsDecay += 1;
      }
    }
    int temp = handlimit + (numberBasketsDecay * 2);
    if ((h.size() + Board.getDecayPile().size() - numberBasketsDecay) <= temp) {
      for (int i = 0; i < Board.getDecayPile().size(); i++) {
        if (Board.getDecayPile().get(i).getType() == CardType.BASKET) {
          d.add(Board.getDecayPile().get(i));
        } else {
          h.add(Board.getDecayPile().get(i));
        }
      }
      handlimit = temp;
      return true;
    }
    return false;
  }

  //ACTION 3
  public boolean cookMushrooms(ArrayList<Card> l) {
    boolean panAvailableInDisplay = false;
    boolean panAvailableInList = false;
    // check if pan in display
    for (int i = 0; i < d.size(); i++) {
      if (d.getElementAt(i).getType() == CardType.PAN) {
        panAvailableInDisplay = true;
      }
    }
    // check if pan in list
    for (int i = 0; i < l.size(); i++) {
      if (l.get(i).getType() == CardType.PAN) {
        panAvailableInList = true;
      }
    }
    // if no pan in list or display then invalid
    if (!(panAvailableInList || panAvailableInDisplay)) {
      return false;
    }
    // get name of mushroom and its score (not pan, cider or butter). if there is a basket or stick then invalid
    String name = "";
    int scorePerMushroom = 0;
    for (int i = 0; i < l.size(); i++) {
      if (l.get(i).getType() == CardType.BASKET || l.get(i).getType() == CardType.STICK) {
        return false;
      }
      if (l.get(i).getType() == CardType.DAYMUSHROOM || l.get(i).getType() == CardType.NIGHTMUSHROOM) {
        name = l.get(i).getName();
        scorePerMushroom = ((EdibleItem) l.get(i)).getFlavourPoints();
        break;
      }
    }
    // if no mushrooms in list then invalid
    if (name.equals("")) {
      return false;
    }
    // make sure all mushrooms are same name and count number of mushrooms and count number of butters and ciders
    int count = 0;
    int butterNumber = 0;
    int ciderNumber = 0;
    for (int i = 0; i < l.size(); i++) {
      if (l.get(i).getType() == CardType.DAYMUSHROOM) {
        if (!(l.get(i).getName().equals(name))) {
          return false;
        } else {
          count += 1;
        }
      }
      if (l.get(i).getType() == CardType.NIGHTMUSHROOM) {
        if (!(l.get(i).getName().equals(name))) {
          return false;
        } else {
          count += 2;
        }
      }
      if (l.get(i).getType() == CardType.BUTTER) {
        butterNumber += 1;
      }
      if (l.get(i).getType() == CardType.CIDER) {
        ciderNumber += 1;
      }
    }
    // make sure number of mushrooms is at least 3
    if (count < 3) {
      return false;
    }
    // check if for each butter and cider there is appropriate number of mushrooms
    int mushroomsRequired = (butterNumber * 4) + (ciderNumber * 5);
    if (count < mushroomsRequired) {
      return false;
    }
    // calculate score earned
    boolean isValid = true;
    score = score + (count * scorePerMushroom) + (butterNumber * 3) + (ciderNumber * 5);
    // if pan in display then remove that one pan
    int removedPan = 0;
    for (int i = 0; i < d.size(); i++) {
      if (d.getElementAt(i).getType() == CardType.PAN) {
        d.removeElement(i);
        removedPan += 1;
        break;
      }
    }
    // if pan in hand, remove it. remove the number of ciders, butters and mushrooms from hand.
    int removedMushrooms = 0;
    int removedButters = 0;
    int removedCiders = 0;
    // System.out.println("COUNTS:");
    // System.out.println(removedMushrooms);
    // System.out.println(removedButters);
    // System.out.println(removedCiders);
    for (int i = 0; i < h.size(); i++) {
      // if pan in hand, remove it.
      if (h.getElementAt(i).getType() == CardType.PAN) {
        if (removedPan == 0) {
          h.removeElement(i);
          removedPan += 1;
        }
      }
      // remove mushrooms
      if (h.getElementAt(i).getType() == CardType.DAYMUSHROOM) {
        if (removedMushrooms < count) {
          h.removeElement(i);
          removedMushrooms += 1;
        }
      }
      if (h.getElementAt(i).getType() == CardType.NIGHTMUSHROOM) {
        if (removedMushrooms < count) {
          h.removeElement(i);
          removedMushrooms += 2;
        }
      }
      // remove butters
      if (h.getElementAt(i).getType() == CardType.BUTTER) {
        if (removedButters < butterNumber) {
          h.removeElement(i);
          removedButters += 1;
        }
      }
      // remove ciders
      if (h.getElementAt(i).getType() == CardType.CIDER) {
        if (removedCiders < ciderNumber) {
          h.removeElement(i);
          removedCiders += 1;
        }
      }
    }
    // System.out.println("REMOVED:");
    // System.out.println(removedMushrooms);
    // System.out.println(removedButters);
    // System.out.println(removedCiders);
    if (isValid) {
      return true;
    }
    return false;
  }

  //ACTION 4
  public boolean sellMushrooms(String s, int num) {
    if(num < 2){
      return false;
    }
    s = s.replaceAll("[^A-Za-z]+", "").toLowerCase();
    int mushroomCount = 0;
    int toAddSticks = 0;
    int sold = 0;
    for (int i = 0; i < h.size(); i++) {
      if (h.getElementAt(i).getName().equals(s)) {
        if (h.getElementAt(i).getType() == CardType.DAYMUSHROOM) {
          toAddSticks += ((Mushroom) h.getElementAt(i)).getSticksPerMushroom();
          mushroomCount = mushroomCount + 1;
        } else if (h.getElementAt(i).getType() == CardType.NIGHTMUSHROOM) {
          mushroomCount += 2;
          toAddSticks += ((Mushroom) h.getElementAt(i)).getSticksPerMushroom() * 2;
        }
      }
    }
    if(mushroomCount < num){
      return false;
    }
    this.addSticks(toAddSticks);
    for (int i = 0; i < h.size(); i++) {
      if (sold < mushroomCount) {
        if (h.getElementAt(i).getName().equals(s)) {
          if (h.getElementAt(i).getType() == CardType.DAYMUSHROOM) {
            sold += 1;
            h.removeElement(i);
            return true;
          } else if (h.getElementAt(i).getType() == CardType.NIGHTMUSHROOM) {
            sold += 2;
            h.removeElement(i);
            return true;
          }
        }
      }
    }
    return false;
  }

  //ACTION 5
  public boolean putPanDown() {
    for (int i = 0; i < h.size(); i++) {
      if (h.getElementAt(i).getType() == CardType.PAN) {
        d.add(h.getElementAt(i));
        h.removeElement(i);
        return true;
      }
    }
    return false;
  }
}
