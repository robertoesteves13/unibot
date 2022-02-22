package br.com.unibot;

import br.com.unibot.Commands.Command;
import br.com.unibot.Commands.EmbedCommand;
import br.com.unibot.Commands.ComicCommand;

public class CommandFactory {
  public static Command getCommand(String type) {
    switch (type) {
      case "embed":
        return new EmbedCommand();
      case "comic":
        return new ComicCommand();
      default:
        return null;
    }
  }
}
