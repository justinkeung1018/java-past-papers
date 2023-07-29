package tunes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class Question5Tests {
  /*
  private List<Note> simpleTuneNotes() {
    return List.of(new Note(1, 2), new Note(10, 4), new Note(20, 8), new Note(40, 12));
  }

  private List<Note> longerTuneNotes() {
    final List<Note> result = new ArrayList<>(List.of(new Note(1, 2), new Note(10, 4),
            new Note(20, 8), new Note(40, 12)));
    result.addAll(List.of(new Note(50, 2), new Note(60, 4), new Note(70, 8),
            new Note(80, 12)));
    return result;
  }

  private Tune simpleTune() {
    Tune result = new StandardTune();
    simpleTuneNotes().forEach(result::addNote);
    return result;
  }

  @Test
  public void testTransposeUp() {
    Tune originalTune = simpleTune();
    Tune transposedUp = new TransposedTune(originalTune, 2);
    assertEquals(simpleTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(3, 2), new Note(12, 4), new Note(22, 8), new Note(42, 12)),
            transposedUp.getNotes());
  }

  @Test
  public void testTransposeDown() {
    Tune originalTune = simpleTune();
    Tune transposedDown = new TransposedTune(originalTune, -1);
    assertEquals(simpleTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(0, 2), new Note(9, 4), new Note(19, 8), new Note(39, 12)),
            transposedDown.getNotes());
  }

  @Test
  public void testTransposeUpThenDown() {
    Tune originalTune = simpleTune();
    Tune transposedUp = new TransposedTune(originalTune, 1);
    Tune transposedDown = new TransposedTune(transposedUp, -1);
    assertEquals(simpleTuneNotes(), originalTune.getNotes());
    assertEquals(simpleTuneNotes(), transposedDown.getNotes());
  }

  @Test
  public void testTransposeUpTooFar() {
    Tune originalTune = simpleTune();
    Tune transposedUp = new TransposedTune(originalTune, 190);
    assertEquals(simpleTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(191, 2), new Note(200, 4), new Note(200, 8),
            new Note(200, 12)), transposedUp.getNotes());
  }

  @Test
  public void testTransposeDownTooFar() {
    Tune originalTune = simpleTune();
    Tune transposedDown = new TransposedTune(originalTune, -20);
    assertEquals(simpleTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(0, 2), new Note(0, 4), new Note(0, 8),
            new Note(20, 12)), transposedDown.getNotes());
  }

  @Test
  public void testAddNotesAllOk1() {
    Tune originalTune = simpleTune();
    Tune transposedUp = new TransposedTune(originalTune, 2);
    transposedUp.addNote(new Note(52, 2));
    transposedUp.addNote(new Note(62, 4));
    transposedUp.addNote(new Note(72, 8));
    transposedUp.addNote(new Note(82, 12));
    assertEquals(longerTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(3, 2), new Note(12, 4), new Note(22, 8), new Note(42, 12),
            new Note(52, 2), new Note(62, 4), new Note(72, 8), new Note(82, 12)),
            transposedUp.getNotes());
  }

  @Test
  public void testAddNotesAllOk2() {
    Tune originalTune = simpleTune();
    Tune transposedDown = new TransposedTune(originalTune, -1);
    transposedDown.addNote(new Note(49, 2));
    transposedDown.addNote(new Note(59, 4));
    transposedDown.addNote(new Note(69, 8));
    transposedDown.addNote(new Note(79, 12));
    assertEquals(longerTuneNotes(), originalTune.getNotes());
    assertEquals(List.of(new Note(0, 2), new Note(9, 4), new Note(19, 8), new Note(39, 12),
            new Note(49, 2), new Note(59, 4), new Note(69, 8), new Note(79, 12)
    ), transposedDown.getNotes());
  }

  @Test
  public void testAddNotesSomeTooHigh() {
    Tune originalTune = simpleTune();
    Tune transposedDown = new TransposedTune(originalTune, -10);
    transposedDown.addNote(new Note(189, 2));
    transposedDown.addNote(new Note(190, 4));
    transposedDown.addNote(new Note(191, 8));
    transposedDown.addNote(new Note(192, 12));
    assertEquals(List.of(
            new Note(1, 2), new Note(10, 4), new Note(20, 8), new Note(40, 12),
            new Note(199, 2), new Note(200, 4), new Note(200, 8), new Note(200, 12)
    ), originalTune.getNotes());
    assertEquals(List.of(
            new Note(0, 2), new Note(0, 4), new Note(10, 8), new Note(30, 12),
            new Note(189, 2), new Note(190, 4), new Note(190, 8), new Note(190, 12)
    ), transposedDown.getNotes());
  }

  @Test
  public void testAddNotesSomeTooLow() {
    Tune originalTune = simpleTune();
    Tune transposedUp = new TransposedTune(originalTune, 10);
    transposedUp.addNote(new Note(11, 2));
    transposedUp.addNote(new Note(10, 4));
    transposedUp.addNote(new Note(9, 8));
    transposedUp.addNote(new Note(8, 12));
    assertEquals(List.of(
            new Note(1, 2), new Note(10, 4), new Note(20, 8), new Note(40, 12),
            new Note(1, 2), new Note(0, 4), new Note(0, 8), new Note(0, 12)
    ), originalTune.getNotes());
    assertEquals(List.of(
            new Note(11, 2), new Note(20, 4), new Note(30, 8), new Note(50, 12),
            new Note(11, 2), new Note(10, 4), new Note(10, 8), new Note(10, 12)
    ), transposedUp.getNotes());
  }
  */
}
