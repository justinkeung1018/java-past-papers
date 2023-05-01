package exchange;

import chemicals.Carbon;
import chemicals.Hydrogen;
import chemicals.Methane;
import datastructures.Stock;
import datastructures.StockImplCoarseGrained;
import domain.Player;

import java.util.Optional;

public class ExchangeImpl implements Exchange {
  private final Stock<Hydrogen> hydrogenStocks;
  private final Stock<Carbon> carbonStocks;
  private final Stock<Methane> methaneStocks;

  public ExchangeImpl() {
    hydrogenStocks = new StockImplCoarseGrained<>();
    carbonStocks = new StockImplCoarseGrained<>();
    methaneStocks = new StockImplCoarseGrained<>();
  }

  @Override
  public void sellHydrogen(Hydrogen item, Player player) {
    hydrogenStocks.push(item, player);
  }

  @Override
  public Optional<Hydrogen> buyHydrogen() {
    return hydrogenStocks.pop();
  }

  @Override
  public void sellCarbon(Carbon item, Player player) {
    carbonStocks.push(item, player);
  }

  @Override
  public Optional<Carbon> buyCarbon() {
    return carbonStocks.pop();
  }

  @Override
  public void sellMethane(Methane item, Player player) {
    methaneStocks.push(item, player);
  }

  @Override
  public Optional<Methane> buyMethane() {
    return methaneStocks.pop();
  }
}
