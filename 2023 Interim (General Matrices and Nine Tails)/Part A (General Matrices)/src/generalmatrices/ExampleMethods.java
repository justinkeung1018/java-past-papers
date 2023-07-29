package generalmatrices;

import java.util.List;
import java.util.function.BinaryOperator;

public class ExampleMethods {

  public static Matrix<Matrix<Integer>> multiplyNestedMatrices(Matrix<Matrix<Integer>> first,
      Matrix<Matrix<Integer>> second) {
    BinaryOperator<Matrix<Integer>> intMatrixSum = (a, b) -> a.sum(b, Integer::sum);
    BinaryOperator<Matrix<Integer>> intMatrixProduct =
        (a, b) -> a.product(b, Integer::sum, (m, n) -> m * n);
    return first.product(second, intMatrixSum, intMatrixProduct);
  }

  public static Matrix<Pair> multiplyPairMatrices(List<Matrix<Pair>> matrices) {
    BinaryOperator<Pair> pairSum =
        (a, b) -> new Pair(a.getCoordX() + b.getCoordX(), a.getCoordY() + b.getCoordY());
    BinaryOperator<Pair> pairProduct =
        (a, b) -> new Pair(a.getCoordX() * b.getCoordX(), a.getCoordY() * b.getCoordY());
    return matrices.stream().reduce((a, b) -> a.product(b, pairSum, pairProduct)).get();
  }

}
