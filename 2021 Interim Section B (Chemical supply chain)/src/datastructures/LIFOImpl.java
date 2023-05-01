package datastructures;

import java.util.Optional;
import java.util.Stack;

public class LIFOImpl<E> implements LIFO<E> {

  private final Stack<E> stack;

  public LIFOImpl() {
    stack = new Stack<>();
  }

  @Override
  public void push(E item) {
    stack.push(item);
  }

  @Override
  public Optional<E> pop() {
    if (stack.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(stack.pop());
  }

  @Override
  public int size() {
    return stack.size();
  }
}
