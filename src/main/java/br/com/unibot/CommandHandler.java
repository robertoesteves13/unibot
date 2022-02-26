package br.com.unibot;

import br.com.unibot.Commands.EmbedCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.rest.RestClient;
import reactor.core.publisher.Mono;
import br.com.unibot.Commands.ComicCommand;

public class CommandHandler {
  private EmbedCommand embedCommand;
  private ComicCommand comicCommand;

  public CommandHandler(RestClient client) {
    embedCommand = new EmbedCommand(client);
    comicCommand = new ComicCommand(client);
  }

  public Mono<?> runCommand(ChatInputInteractionEvent event) {
    switch (event.getCommandName()) {
      case "embed":
        return embedCommand.run(event);
      case "comic":
        return comicCommand.run(event);
      default:
        return Mono.empty();
    }
  }
}
