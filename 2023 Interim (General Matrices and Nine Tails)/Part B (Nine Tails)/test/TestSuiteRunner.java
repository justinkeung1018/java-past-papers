import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSuiteRunner {

  public static void main(String[] args) {

    // 1. Include PriorityQueueTest.class in the arguments of JUnitCore.runClasses
    //        once you have a runnable test in the PriorityQueueTest.java
    // 2. Include NineTailsWeightedGraphTest.class in the arguments of JUnitCore.runClasses
    //        once you have a runnable test in the NineTailsWeightedGraphTest.java
    Result result =
        JUnitCore.runClasses(
            ListArrayBasedTest.class/*,
            PriorityQueueTest.class,
            NineTailsWeightedGraphTest.class*/);
    List<Failure> failures = result.getFailures();
    if (failures.isEmpty()) {
      System.out.println("OK! No failures reported.");
    } else {
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
    }
  }
}

