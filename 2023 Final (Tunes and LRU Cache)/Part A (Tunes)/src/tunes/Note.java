package tunes;

public final class Note {
  private final int pitch;
  private final int duration;

  private static final int MIN_PITCH = 0;
  private static final int MAX_PITCH = 200;

  private static final int MIN_DURATION = 1;
  private static final int MAX_DURATION = 64;

  private static final int NUM_NOTES_PER_OCTAVE = 12;

  public Note(int pitch, int duration) {
    if (pitch < MIN_PITCH || pitch > MAX_PITCH) {
      throw new IllegalArgumentException("Pitch is out of bounds.");
    }
    if (duration < MIN_DURATION || duration > MAX_DURATION) {
      throw new IllegalArgumentException("Duration is out of bounds.");
    }
    this.pitch = pitch;
    this.duration = duration;
  }

  public boolean hasNoteAbove() {
    return pitch < MAX_PITCH;
  }

  public boolean hasNoteBelow() {
    return pitch > MIN_PITCH;
  }

  public Note noteAbove() {
    // PRE: There is a note above
    return new Note(pitch + 1, duration);
  }

  public Note noteBelow() {
    // PRE: There is a note below
    return new Note(pitch - 1, duration);
  }

  public int getDuration() {
    return duration;
  }

  @Override
  public String toString() {
    int value = pitch % NUM_NOTES_PER_OCTAVE;
    String valueName = switch (value) {
      case 0 -> "C";
      case 1 -> "C#";
      case 2 -> "D";
      case 3 -> "D#";
      case 4 -> "E";
      case 5 -> "F";
      case 6 -> "F#";
      case 7 -> "G";
      case 8 -> "G#";
      case 9 -> "A";
      case 10 -> "A#";
      case 11 -> "B";
      default -> throw new RuntimeException(
          "Value out of bounds; check NUM_NOTES_PER_OCTAVE definition.");
    };
    int octave = pitch / NUM_NOTES_PER_OCTAVE;
    StringBuilder sb = new StringBuilder();
    sb.append(valueName);
    sb.append(octave);
    sb.append("(");
    sb.append(duration);
    sb.append(")");
    return sb.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Note thatNote)) {
      return false;
    }
    return thatNote.pitch == pitch && thatNote.duration == duration;
  }

  @Override
  public int hashCode() {
    return pitch * duration;
  }
}