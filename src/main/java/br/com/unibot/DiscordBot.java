package br.com.unibot;

import java.io.IOException;

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
    try {
      commandHandler.registerCommands();
    } catch (IOException e) {
      System.out.println(e);
    }

    gateway.on(SelectMenuInteractionEvent.class, event -> {
      return ListenerFactory.handleEvent(event);
    }).subscribe();

    gateway.on(ChatInputInteractionEvent.class, event -> {
      //TODO: Refactor commandHandler
      return commandHandler.executeCommand(event);
    }).blockLast();
  }
}
