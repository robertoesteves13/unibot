package br.com.unibot;

import br.com.unibot.Listeners.Select.CollegeExperienceListener;
import br.com.unibot.Listeners.Select.PeriodListener;
import br.com.unibot.Listeners.Select.CourseListener;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import reactor.core.publisher.Mono;

public class ListenerFactory {
  public static Mono<?> handleEvent(ComponentInteractionEvent event) {
    switch (event.getCustomId()) {
      case "period":
        return PeriodListener.listen(event);
      case "college-experience":
        return CollegeExperienceListener.listen(event);
      case "course":
        return CourseListener.listen(event);
      default:
        return Mono.empty();
    }
  }
}
