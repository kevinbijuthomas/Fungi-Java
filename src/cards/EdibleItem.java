package cards;

public class EdibleItem extends Card {
  protected int flavourPoints;

  public void EdibleItem(CardType c, String s) {
    this.type = c;
    this.cardName = s;
  }

  public int getFlavourPoints() {
    return this.flavourPoints;
  }
}
