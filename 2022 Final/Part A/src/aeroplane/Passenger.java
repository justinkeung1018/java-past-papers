package aeroplane;

public abstract class Passenger {

  private final String firstName;
  private final String surname;

  public Passenger(String firstName, String surname) {
    this.firstName = firstName;
    this.surname = surname;
  }

  abstract public boolean isAdult();

  @Override
  public String toString() {
    return firstName + " " + surname;
  }
}
