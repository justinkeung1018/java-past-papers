package generalmatrices;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public final class Matrix<T> {
  private final List<T> elements;
  private final int order;

  public Matrix(List<T> elements) {
    if (elements.isEmpty()) {
      throw new IllegalArgumentException("List must be non-empty.");
    }
    if (!isPerfectSquare(elements.size())) {
      throw new IllegalArgumentException("List size must be a perfect square.");
    }
    this.elements = elements;
    order = (int) Math.sqrt(elements.size());
  }

  public T get(int row, int col) {
    int index = row * order + col;
    return elements.get(index);
  }

  public int getOrder() {
    return order;
  }

  public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
    List<T> sums = new ArrayList<>();
    for (int row = 0; row < order; row++) {
      for (int col = 0; col < order; col++) {
        T thisElement = get(row, col);
        T otherElement = other.get(row, col);
        sums.add(elementSum.apply(thisElement, otherElement));
      }
    }
    return new Matrix<>(sums);
  }

  public Matrix<T> product(
      Matrix<T> other, BinaryOperator<T> elementSum, BinaryOperator<T> elementProduct) {
    List<T> products = new ArrayList<>();
    for (int row = 0; row < order; row++) {
      for (int col = 0; col < order; col++) {
        List<T> individualProducts = new ArrayList<>();
        for (int offset = 0; offset < order; offset++) {
          T thisElement = get(row, offset);
          T otherElement = other.get(offset, col);
          individualProducts.add(elementProduct.apply(thisElement, otherElement));
        }
        T product = individualProducts.stream().reduce(elementSum::apply).get();
        products.add(product);
      }
    }
    return new Matrix<>(products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int row = 0; row < order; row++) {
      sb.append("[");
      for (int col = 0; col < order; col++) {
        sb.append(get(row, col).toString());
        if (col != order - 1) {
          sb.append(" ");
        }
      }
      sb.append("]");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Matrix<?> thatMatrix)) {
      return false;
    }
    if (order != thatMatrix.order) {
      return false;
    }
    for (int row = 0; row < order; row++) {
      for (int col = 0; col < order; col++) {
        T thisElement = get(row, col);
        if (!thisElement.equals(thatMatrix.get(row, col))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(order) + elements.hashCode();
  }

  private boolean isPerfectSquare(int k) {
    double squareRoot = Math.sqrt(k);
    return Math.floor(squareRoot) == squareRoot;
  }
}
