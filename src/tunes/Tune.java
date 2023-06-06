package tunes;

import java.util.List;

public interface Tune {
  List<Note> getNotes();

  void addNote(Note note);

  default int getTotalDuration() {
    return getNotes()
        .stream()
        .mapToInt(Note::getDuration)
        .reduce(0, Integer::sum);
  }
}
