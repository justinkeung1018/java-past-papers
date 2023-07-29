package tunes;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSuiteRunner {
  public static void main(String[] args) {
    Result result =
            JUnitCore.runClasses(
                    tunes.Question1Tests.class,
                    tunes.Question2Tests.class,
                    tunes.Question3Tests.class,
                    tunes.Question4Tests.class,
                    tunes.Question5Tests.class,
                    tunes.Question6Tests.class);
    if (result.wasSuccessful()) {
      System.exit(0);
    }
    System.err.println("There were test failures:");
    for (Failure failure : result.getFailures()) {
      System.err.println(failure.toString());
    }
    System.exit(1);
  }
}
