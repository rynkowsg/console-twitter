package info.rynkowski.consoletwitter.ui.view.input;

public interface CommandProcessor {

  enum Result { SUCCESS, FAILED }

  Result process(final String commandText);
}
