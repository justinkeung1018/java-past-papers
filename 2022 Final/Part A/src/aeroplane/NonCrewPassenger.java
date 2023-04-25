package aeroplane;

public abstract class NonCrewPassenger extends Passenger {
  private int age;

  public NonCrewPassenger(String firstName, String surname, int age) {
    super(firstName, surname);
    if (age < 0) {
      throw new IllegalArgumentException("Age must be non-negative");
    }
    this.age = age;
  }

  @Override
  public boolean isAdult() {
    return age >= 18;
  }

  @Override
  public String toString() {
    return super.toString() + ", age " + age;
  }
}
