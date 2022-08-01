package board;
import cards.*;
import java.util.ArrayList;

public class Board {
  private static CardPile forestCardsPile;
  private static CardList forest;
  private static ArrayList<Card> decayPile;

  public static void initialisePiles() {
    forestCardsPile = new CardPile();
    forest = new CardList();
    decayPile = new ArrayList<Card>();
  }

  public static void setUpCards() {
    // HONEY FUNGUS
    for (int i = 0; i < 10; i++) {
      forestCardsPile.addCard(new HoneyFungus(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new HoneyFungus(CardType.NIGHTMUSHROOM));

    // Tree Ear
    for (int i = 0; i < 8; i++) {
      forestCardsPile.addCard(new TreeEar(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new TreeEar(CardType.NIGHTMUSHROOM));

    // Lawyer's Wig
    for (int i = 0; i < 6; i++) {
      forestCardsPile.addCard(new LawyersWig(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new LawyersWig(CardType.NIGHTMUSHROOM));

    // Shiitake
    for (int i = 0; i < 5; i++) {
      forestCardsPile.addCard(new Shiitake(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new Shiitake(CardType.NIGHTMUSHROOM));

    // Hen of Woods
    for (int i = 0; i < 5; i++) {
      forestCardsPile.addCard(new HenOfWoods(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new HenOfWoods(CardType.NIGHTMUSHROOM));

    // Birch Bolete
    for (int i = 0; i < 4; i++) {
      forestCardsPile.addCard(new BirchBolete(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new BirchBolete(CardType.NIGHTMUSHROOM));

    // Porcini
    for (int i = 0; i < 4; i++) {
      forestCardsPile.addCard(new Porcini(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new Porcini(CardType.NIGHTMUSHROOM));

    // Chanterelle
    for (int i = 0; i < 4; i++) {
      forestCardsPile.addCard(new Chanterelle(CardType.DAYMUSHROOM));
    }
    forestCardsPile.addCard(new Chanterelle(CardType.NIGHTMUSHROOM));

    // Morel
    for (int i = 0; i < 3; i++) {
      forestCardsPile.addCard(new Morel(CardType.DAYMUSHROOM));
    }

    // Butter
    forestCardsPile.addCard(new Butter());
    forestCardsPile.addCard(new Butter());
    forestCardsPile.addCard(new Butter());
    // Cider
    forestCardsPile.addCard(new Cider());
    forestCardsPile.addCard(new Cider());
    forestCardsPile.addCard(new Cider());

    // Pan
    for (int i = 0; i < 11; i++) {
      forestCardsPile.addCard(new Pan());
    }

    // Basket
    forestCardsPile.addCard(new Basket());
    forestCardsPile.addCard(new Basket());
    forestCardsPile.addCard(new Basket());
    forestCardsPile.addCard(new Basket());
    forestCardsPile.addCard(new Basket());
  }

  public static CardPile getForestCardsPile() {
    return forestCardsPile;
  }

  public static CardList getForest() {
    return forest;
  }

  public static ArrayList<Card> getDecayPile() {
    return decayPile;
  }

  public static void updateDecayPile() {
    if (decayPile.size() == 4) {
      decayPile.clear();
    }
    decayPile.add(forest.removeCardAt(1));
  }
}
