package players;

import chemicals.Hydrogen;
import domain.Player;
import exchange.Exchange;

public class HydrogenSupplier extends Player {

  public HydrogenSupplier(int thinkingTimeInMillis, Exchange exchange) {
    super(thinkingTimeInMillis, exchange);
  }

  @Override
  public void doAction() {
    exchange.sellHydrogen(new Hydrogen(), this);
  }
}
