package br.com.unibot;

import br.com.unibot.Commands.Command;
import br.com.unibot.Commands.EmbedCommand;

public class CommandFactory {
  public static Command getCommand(String type) {
    switch (type) {
      case "embed":
        return new EmbedCommand();
      default:
        return null;
    }
  }
}
