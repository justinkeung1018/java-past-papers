package players;

import chemicals.Carbon;
import domain.Player;
import exchange.Exchange;

public class CarbonSupplier extends Player {

  public CarbonSupplier(int thinkingTimeInMillis, Exchange exchange) {
    super(thinkingTimeInMillis, exchange);
  }

  @Override
  public void doAction() {
    exchange.sellCarbon(new Carbon(), this);
  }
}
