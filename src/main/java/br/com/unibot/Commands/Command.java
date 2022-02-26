package br.com.unibot.Commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

public interface Command {
  public Mono<?> run(ChatInputInteractionEvent event);
}
