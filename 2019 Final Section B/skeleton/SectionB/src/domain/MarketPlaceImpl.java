package domain;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import utils.SafeQueue;
import utils.SafeQueueCoarse;

import java.util.Optional;


public class MarketPlaceImpl implements MarketPlace {

  private final boolean DEBUG_MESSAGES = true;
  private final SafeQueue<RawPlastic> recycledRawPlastic;
  private final SafeQueue<RawPlastic> newRawPlastic;
  private final SafeQueue<PlasticGood> plasticGoods;
  private final SafeQueue<PlasticGood> disposedPlasticGoods;

  public MarketPlaceImpl() {
    recycledRawPlastic = new SafeQueueCoarse<>();
    newRawPlastic = new SafeQueueCoarse<>();
    plasticGoods = new SafeQueueCoarse<>();
    disposedPlasticGoods = new SafeQueueCoarse<>();
  }

  public void sellRawPlastic(RawPlastic plasticItem) {
    if (DEBUG_MESSAGES) {
      System.out
          .println("Thread: " + Thread.currentThread().getId() + " - Sell plastic: " + plasticItem);
    }
    switch (plasticItem.origin) {
      case NEW -> newRawPlastic.push(plasticItem);
      case RECYCLED -> recycledRawPlastic.push(plasticItem);
    }
  }

  public Optional<RawPlastic> buyRawPlastic() {
    if (recycledRawPlastic.size() == 0) {
      return newRawPlastic.pop();
    }
    return recycledRawPlastic.pop();
  }

  public void sellPlasticGood(PlasticGood good) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: " + Thread.currentThread().getId() + " - Sell good: " + good);
    }
    plasticGoods.push(good);
  }

  public Optional<PlasticGood> buyPlasticGood() {
    return plasticGoods.pop();
  }

  public void disposePlasticGood(PlasticGood good) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: " + Thread.currentThread().getId() + " - Dispose good: " + good);
    }
    disposedPlasticGoods.push(good);
  }

  public Optional<PlasticGood> collectDisposedGood() {
    return disposedPlasticGoods.pop();
  }
}
