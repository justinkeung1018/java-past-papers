package players;

import chemicals.Methane;
import domain.Player;
import exchange.Exchange;
import java.util.Optional;

public class Chemist extends Player {

  public Chemist(int thinkingTimeInMillis, Exchange exchange) {
    super(thinkingTimeInMillis, exchange);
  }

  @Override
  public void doAction() {
    Optional<Methane> methanePurchaseAttempt = exchange.buyMethane();
    if (methanePurchaseAttempt.isPresent()) {
      think();
    } else {
      return;
    }
  }
}
