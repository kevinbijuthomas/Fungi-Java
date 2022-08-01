package cards;

public class Card {
  protected CardType type;
  protected String cardName;

  public void Card(CardType c, String s) {
    this.type = c;
    this.cardName = s;
  }

  public CardType getType() {
    return this.type;
  }

  public String getName() {
    return this.cardName;
  }
}
