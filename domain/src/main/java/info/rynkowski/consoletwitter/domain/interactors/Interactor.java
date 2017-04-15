package info.rynkowski.consoletwitter.domain.interactors;

public interface Interactor<Result, Argument> {

  Result execute(final Argument argument);
}
