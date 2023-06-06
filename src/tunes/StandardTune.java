package tunes;

import java.util.ArrayList;
import java.util.List;

public class StandardTune implements Tune {
  private final List<Note> notes;

  public StandardTune() {
    notes = new ArrayList<>();
  }

  @Override
  public List<Note> getNotes() {
    return new ArrayList<>(notes);
  }

  @Override
  public void addNote(Note note) {
    notes.add(note);
  }
}
