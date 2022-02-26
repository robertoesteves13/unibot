package br.com.unibot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;

public class DiscordBot {
  private DiscordClient client;
  private GatewayDiscordClient gateway;

  public DiscordBot(String token) {
    client = DiscordClient.create(token);
    gateway = client.login().block();
  }

  public void run() {
    var commandHandler = new CommandHandler(gateway.getRestClient());

    gateway.on(SelectMenuInteractionEvent.class, event -> {
      return ListenerFactory.handleEvent(event);
    }).subscribe();

    gateway.on(ChatInputInteractionEvent.class, event -> {
      return commandHandler.runCommand(event);
    }).blockLast();
  }
}
