package aeroplane;

public class BusinessClassPassenger extends NonCrewPassenger {
  private final Luxury luxury;

  public BusinessClassPassenger(String firstName, String surname, int age, Luxury luxury) {
    super(firstName, surname, age);
    this.luxury = luxury;
  }

  @Override
  public String toString() {
    return "Business class passenger: " + super.toString() + ", likes " + luxury;
  }
}
