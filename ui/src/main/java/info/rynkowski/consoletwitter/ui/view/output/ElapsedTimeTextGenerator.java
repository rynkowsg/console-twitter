package info.rynkowski.consoletwitter.ui.view.output;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class ElapsedTimeTextGenerator {

  public String generate(final DateTime startTime, final DateTime endTime) {
    final Period period = new Period(startTime, endTime);
    PeriodFormatterBuilder builder = new PeriodFormatterBuilder();
    if (period.getYears() > 0) {
      builder.appendYears().appendSuffix(" year ago", " years ago");
    }
    else if (period.getMonths() > 0) {
      builder.appendMonths().appendSuffix(" month ago", " months ago");
    }
    else if (period.getWeeks() > 0) {
      builder.appendWeeks().appendSuffix(" week ago", " weeks ago");
    }
    else if (period.getDays() > 0) {
      builder.appendDays().appendSuffix(" day ago", " days ago");
    }
    else if (period.getHours() > 0) {
      builder.appendHours().appendSuffix(" hour ago", " hours ago");
    }
    else if (period.getMinutes() > 0) {
      builder.appendMinutes().appendSuffix(" minute ago", " minutes ago");
    }
    else if (period.getSeconds() > 0) {
      builder.appendSeconds().appendSuffix(" second ago", " seconds ago");
    }
    else {
      builder.appendLiteral("a moment ago");
    }
    PeriodFormatter formatter = builder.printZeroNever().toFormatter();
    return formatter.print(period);
  }
}
