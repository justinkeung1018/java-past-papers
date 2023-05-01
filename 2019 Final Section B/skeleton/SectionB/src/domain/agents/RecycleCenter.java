package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import java.util.Optional;

public class RecycleCenter extends Agent {

  public RecycleCenter(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }
  private int numRecycledBatches = 0;

  @Override
  protected void doAction() {
    Optional<PlasticGood> recycledPlasticGood = marketPlace.collectDisposedGood();
    if (recycledPlasticGood.isPresent()) {
      for (RawPlastic rawPlastic : recycledPlasticGood.get().getBasicMaterials()) {
        switch (rawPlastic.origin) {
          case NEW -> marketPlace.sellRawPlastic(new RawPlastic(RawPlastic.Origin.RECYCLED));
          case RECYCLED -> {
            if (++numRecycledBatches == 2) {
              numRecycledBatches = 0;
              marketPlace.sellRawPlastic(new RawPlastic(RawPlastic.Origin.RECYCLED));
            }
          }
        }
      }
    }
  }
}
