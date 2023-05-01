package players;

import chemicals.Carbon;
import chemicals.Hydrogen;
import chemicals.Methane;
import domain.Player;
import exchange.Exchange;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MethaneMixer extends Player {

  public MethaneMixer(int thinkingTimeInMillis, Exchange exchange) {
    super(thinkingTimeInMillis, exchange);
  }

  @Override
  public void doAction() {
    Carbon carbon = null;
    while (carbon == null) {
      Optional<Carbon> carbonPurchaseAttempt = exchange.buyCarbon();
      if (carbonPurchaseAttempt.isPresent()) {
        carbon = carbonPurchaseAttempt.get();
      }
    }
    Hydrogen[] hydrogens = new Hydrogen[2];
    int numHydrogens = 0;
    while (numHydrogens < 2) {
      Optional<Hydrogen> hydrogenPurchaseAttempt = exchange.buyHydrogen();
      if (hydrogenPurchaseAttempt.isPresent()) {
        hydrogens[numHydrogens++] = hydrogenPurchaseAttempt.get();
      }
    }
    exchange.sellMethane(new Methane(carbon, hydrogens[0], hydrogens[1]), this);
  }
}
