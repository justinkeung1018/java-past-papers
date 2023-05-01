package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Manufacturer extends Agent {

  private final int neededRawPlasticBatches;

  public Manufacturer(int neededRawPlasticBatches, int thinkingTimeInMillis,
      MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
    if (neededRawPlasticBatches < 1) {
      throw new IllegalArgumentException("Needs at least one plastic batch");
    }
    this.neededRawPlasticBatches = neededRawPlasticBatches;
  }

  @Override
  protected void doAction() {
    Set<RawPlastic> rawPlasticBatches = new HashSet<>();
    while (rawPlasticBatches.size() < neededRawPlasticBatches) {
      Optional<RawPlastic> rawPlasticBatch = marketPlace.buyRawPlastic();
      if (rawPlasticBatch.isPresent()) {
        rawPlasticBatches.add(rawPlasticBatch.get());
      } else {
        think();
      }
    }
    marketPlace.sellPlasticGood(new PlasticGood(rawPlasticBatches));
  }
}
