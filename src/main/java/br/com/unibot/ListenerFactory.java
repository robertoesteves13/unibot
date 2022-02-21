package br.com.unibot;

import br.com.unibot.Listeners.Select.PeriodListener;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import reactor.core.publisher.Mono;

public class ListenerFactory {
  public static Mono<?> handleEvent(ComponentInteractionEvent event) {
    switch (event.getCustomId()) {
      case "period":
        return PeriodListener.listen(event);
      default:
        return Mono.empty();
    }
  }
}
