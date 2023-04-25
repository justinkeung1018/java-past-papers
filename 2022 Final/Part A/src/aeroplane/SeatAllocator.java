package aeroplane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SeatAllocator {

  private final Map<Seat, Passenger> allocation;

  private static final String CREW = "crew";
  private static final String BUSINESS = "business";
  private static final String ECONOMY = "economy";

  public static final int FIRST_ROW = 1;
  public static final int LAST_ROW = 50;

  public static final char FIRST_LETTER = 'A';
  public static final char LAST_LETTER = 'F';

  private static final int CREW_FIRST_ROW = FIRST_ROW;
  private static final int CREW_LAST_ROW = CREW_FIRST_ROW;

  private static final int BUSINESS_FIRST_ROW = 2;
  private static final int BUSINESS_LAST_ROW = 15;

  private static final int ECONOMY_FIRST_ROW = 16;
  private static final int ECONOMY_LAST_ROW = LAST_ROW;

  public static final Set<Integer> EMERGENCY_EXIT_ROWS = Set.of(1, 10, 30);
  
  public SeatAllocator() {
    allocation = new HashMap<Seat, Passenger>();
  }

  @Override
  public String toString() {
    final List<Seat> allocatedSeatsSortedByName = allocation.keySet()
            .stream()
            .distinct()
            .sorted((item1, item2) -> item1.toString().compareTo(item2.toString()))
            .collect(Collectors.toList());
    final StringBuilder result = new StringBuilder();
    for (var seat : allocatedSeatsSortedByName) {
      result.append(seat)
              .append(" -> ")
              .append(allocation.get(seat))
              .append("\n");
    }
    return result.toString();
  }
  
  private void allocateInRange(Passenger passenger,
      Seat first, Seat last) throws AllocationNotPossibleException {

    Seat candidate = first;
    while (true) {
      if (!allocation.containsKey(candidate)) {
        if (!candidate.isEmergencyExit() || passenger.isAdult()) {
          allocation.put(candidate, passenger);
          return;
        }
      }
      if (candidate.equals(last)) {
        throw new AllocationNotPossibleException();
      }
      candidate = candidate.next();
    }
  }

  private static String readStringValue(BufferedReader br) throws MalformedDataException,
          IOException {
    String result = br.readLine();
    if (result == null) {
      throw new MalformedDataException();
    }
    return result;
  }

  private static int readIntValue(BufferedReader br)
      throws MalformedDataException, IOException {
    try {
      return Integer.parseInt(readStringValue(br));
    } catch (NumberFormatException e) {
      throw new MalformedDataException();
    }
  }

  private static Luxury readLuxuryValue(BufferedReader br)
      throws MalformedDataException, IOException {
    try {
      return Luxury.valueOf(readStringValue(br));
    } catch (IllegalArgumentException e) {
      throw new MalformedDataException();
    }
  }

  public void allocate(String filename) throws IOException, AllocationNotPossibleException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = br.readLine()) != null) {
      try {
        switch (line) {
          case CREW -> allocateCrew(br);
          case BUSINESS -> allocateBusiness(br);
          case ECONOMY -> allocateEconomy(br);
          default -> throw new MalformedDataException();
        }
      } catch (MalformedDataException e) {
        throw new RuntimeException("Malformed line of input: " + line);
      }
    }
    
  }
  
  private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException,
          AllocationNotPossibleException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    Passenger crewMember = new CrewMember(firstName, lastName);
    Seat crewFirstSeat = new Seat(CREW_FIRST_ROW, FIRST_LETTER);
    Seat crewLastSeat = new Seat(CREW_LAST_ROW, LAST_LETTER);
    allocateInRange(crewMember, crewFirstSeat, crewLastSeat);
  }

  private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException,
          AllocationNotPossibleException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    int age = readIntValue(br);
    Luxury luxury = readLuxuryValue(br);
    Passenger businessClassPassenger =
        new BusinessClassPassenger(firstName, lastName, age, luxury);
    Seat businessFirstSeat = new Seat(BUSINESS_FIRST_ROW, FIRST_LETTER);
    Seat businessLastSeat = new Seat(BUSINESS_LAST_ROW, LAST_LETTER);
    allocateInRange(businessClassPassenger, businessFirstSeat, businessLastSeat);
  }

  private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException,
          AllocationNotPossibleException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    int age = readIntValue(br);
    Passenger economyClassPassenger = new EconomyClassPassenger(firstName, lastName, age);
    Seat economyFirstSeat = new Seat(ECONOMY_FIRST_ROW, FIRST_LETTER);
    Seat economyLastSeat = new Seat(ECONOMY_LAST_ROW, LAST_LETTER);
    allocateInRange(economyClassPassenger, economyFirstSeat, economyLastSeat);
  }

  public static int countAdults(Set<? extends Passenger> passengers) {
    return (int) passengers.stream().filter(Passenger::isAdult).count();
  }
}
