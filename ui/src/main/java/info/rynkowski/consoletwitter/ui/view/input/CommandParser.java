package info.rynkowski.consoletwitter.ui.view.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

  public CommandParser() {
  }

  public Boolean isMatching(final String pattern, final String commandText) {
    return commandText.matches(pattern);
  }

  public List<String> captureValues(final String pattern, final String commandText) {
    if (!isMatching(pattern, commandText)) {
      return Collections.emptyList();
    }

    final Pattern p = Pattern.compile(pattern);
    final Matcher m = p.matcher(commandText);
    if (!m.find()) {
      return Collections.emptyList();
    }

    List<String> matches = new ArrayList<>();
    for (int i = 1; i <= m.groupCount(); ++i) {
      matches.add(m.group(i));
    }
    return matches;
  }
}
