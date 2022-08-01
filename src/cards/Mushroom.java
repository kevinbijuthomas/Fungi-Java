package cards;

public class Mushroom extends EdibleItem {
  protected int sticksPerMushroom;

  public void Mushroom(CardType c, String s) {
    this.type = c;
    this.cardName = s;
  }

  public int getSticksPerMushroom() {
    return this.sticksPerMushroom;
  }
}
