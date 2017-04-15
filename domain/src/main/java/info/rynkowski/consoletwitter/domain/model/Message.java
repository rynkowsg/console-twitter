package info.rynkowski.consoletwitter.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

@Getter
public class Message implements Comparable<Message> {

  private final String text;
  private final DateTime date;

  public Message(final Builder builder) {
    this.text = builder.text;
    this.date = builder.date;
  }

  @Override public int compareTo(final Message otherMessage) {
    return date.compareTo(otherMessage.date);
  }

  @NoArgsConstructor(access = AccessLevel.PUBLIC)
  @Setter
  @Accessors(fluent = true, chain = true)
  public static class Builder {
    private String text;
    private DateTime date;

    public Builder dateNow() {
      date = DateTime.now();
      return this;
    }
    public Message build() {
      return new Message(this);
    }
  }
}
