package aeroplane;

import java.util.NoSuchElementException;
import java.util.Set;

public final class Seat {

  private final int row;
  private final char letter;

  public Seat(int row, char letter) {
    if (row < SeatAllocator.FIRST_ROW || row > SeatAllocator.LAST_ROW) {
      throw new IllegalArgumentException("Row out of range");
    }
    if (letter < SeatAllocator.FIRST_LETTER || letter > SeatAllocator.LAST_LETTER) {
      throw new IllegalArgumentException("Letter out of range");
    }
    this.row = row;
    this.letter = letter;
  }

  public boolean isEmergencyExit() {
    return SeatAllocator.EMERGENCY_EXIT_ROWS.contains(row);
  }

  public boolean hasNext() {
    return !(row == SeatAllocator.LAST_ROW && letter == SeatAllocator.LAST_LETTER);
  }

  public Seat next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No next seat available");
    }
    int nextRow = letter == SeatAllocator.LAST_LETTER ? row + 1 : row;
    int numSeatsPerRow = SeatAllocator.LAST_LETTER - SeatAllocator.FIRST_LETTER + 1;
    char nextLetter = (char) (SeatAllocator.FIRST_LETTER +
        (letter - SeatAllocator.FIRST_LETTER + 1) % numSeatsPerRow);
    return new Seat(nextRow, nextLetter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (row < 10) {
      sb.append('0');
    }
    sb.append(row);
    sb.append(letter);
    return sb.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Seat thatSeat)) {
      return false;
    }
    return row == thatSeat.row && letter == thatSeat.letter;
  }

  @Override
  public int hashCode() {
    return row * SeatAllocator.LAST_ROW + letter;
  }
}

