package tunes;

import java.util.List;

public class TransposedTune implements Tune {
  private final Tune targetTune;
  private final int pitchOffset;

  public TransposedTune(Tune targetTune, int pitchOffset) {
    this.targetTune = targetTune;
    this.pitchOffset = pitchOffset;
  }

  @Override
  public List<Note> getNotes() {
    return targetTune
        .getNotes()
        .stream()
        .map(note -> shift(note, pitchOffset))
        .toList();
  }

  @Override
  public void addNote(Note note) {
    targetTune.addNote(shift(note, -pitchOffset));
  }

  private Note shift(Note note, int offset) {
    if (offset > 0 && note.hasNoteAbove()) {
      return shift(note.noteAbove(), offset - 1);
    } else if (offset < 0 && note.hasNoteBelow()) {
      return shift(note.noteBelow(), offset + 1);
    } else {
      return note;
    }
  }
}
