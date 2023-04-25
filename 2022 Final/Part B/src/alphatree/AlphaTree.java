package alphatree;

import java.util.HashSet;
import java.util.Set;

public final class AlphaTree {
  private final Set<Character> chars;
  private int freq;
  private AlphaTree lt;
  private AlphaTree rt;

  public AlphaTree() {
    chars = new HashSet<>();
    freq = 0;
  }

  public AlphaTree(char c, int weight) {
    this();
    chars.add(c);
    freq = weight;
  }

  public AlphaTree(AlphaTree lt, AlphaTree rt) {
    this();
    this.lt = lt;
    this.rt = rt;
    if (lt != null) {
      freq += lt.freq();
      chars.addAll(lt.chars());
    }
    if (rt != null) {
      freq += rt.freq();
      chars.addAll(rt.chars());
    }
  }

  public boolean isEmpty() {
    return chars.isEmpty();
  }

  public boolean isSingleton() {
    return chars.size() == 1;
  }

  public AlphaTree left() {
    return lt;
  }

  public AlphaTree right() {
    return rt;
  }

  public Set<Character> chars() {
    return chars;
  }

  public int freq() {
    return freq;
  }
}
