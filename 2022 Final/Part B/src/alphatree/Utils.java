package alphatree;

import java.util.AbstractList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

  public static AlphaTree newAlphaTree(List<Character> chars) {
    AlphaFreq freqs = new AlphaFreq(chars);
    AlphaTreeQueue pq = new AlphaTreeQueue(freqs);
    while (pq.size() > 1) {
      AlphaTree first = pq.poll();
      AlphaTree second = pq.poll();
      pq.add(new AlphaTree(first, second));
    }
    return pq.poll();
  }

  public static AlphaTree newAlphaTree(String string) {
    return newAlphaTree(new AbstractList<Character>() {
      @Override
      public Character get(int index) {
        return string.charAt(index);
      }

      @Override
      public int size() {
        return string.length();
      }
    });
  }

  public static Set<Character> toSet(String s) {
    return s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
  }

  public static <A> Set<A> singleton(A x) {
    Set<A> xs = new HashSet<>();
    xs.add(x);
    return xs;
  }

  public static <A> boolean isSingleton(Set<A> xs) {
    return xs.size() == 1;
  }

  public static <A> A getElem(Set<A> xs) {
    assert isSingleton(xs);
    return xs.iterator().next();
  }

}
