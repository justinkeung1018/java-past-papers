package aeroplane;

public class EconomyClassPassenger extends NonCrewPassenger {

  public EconomyClassPassenger(String firstName, String surname, int age) {
    super(firstName, surname, age);
  }

  @Override
  public String toString() {
    return "Economy class passenger: " + super.toString();
  }
}
