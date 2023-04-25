package alphatree;

import java.util.List;

public class AlphaFreq {
  private final int[] freqs;
  public static final int NUM_CHARS = 26;

  public AlphaFreq() {
    freqs = new int[NUM_CHARS];
  }

  public AlphaFreq(List<Character> cs) {
    this();
    if (cs == null) {
      return;
    }
    for (char c : cs) {
      add(c);
    }
  }

  public boolean isEmpty() {
    for (int i = 0; i < NUM_CHARS; i++) {
      if (freqs[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public int size() {
    int size = 0;
    for (int i = 0; i < NUM_CHARS; i++) {
      size += freqs[i];
    }
    return size;
  }

  public int get(char c) {
    if (c < 'a' || c > 'z') {
      return 0;
    }
    return freqs[c - 'a'];
  }

  public void add(char c) {
    if ('a' <= c && c <= 'z') {
      freqs[c - 'a']++;
    }
  }

  public void reset() {
    for (int i = 0; i < NUM_CHARS; i++) {
      freqs[i] = 0;
    }
  }
}
