package br.com.unibot.Commands;

import java.util.Set;

import br.com.unibot.ComponentsFactory;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.component.ActionRow;
import reactor.core.publisher.Mono;

public class EmbedCommand implements Command {
  private String name;

  public EmbedCommand() {
    this.name = "embed";
  }

  public String getName() {
    return this.name;
  }

  public Mono<?> run(ChatInputInteractionEvent event) {
    var member = event.getInteraction().getMember().get();
    if (!member.getRoleIds().contains(Snowflake.of("943240128523030628"))) {
      return event.reply("Você não tem permissão para executar este comando!")
        .withEphemeral(true);
    }

    var typeOption = event.getOption("type")
        .flatMap(ApplicationCommandInteractionOption::getValue)
        .map(ApplicationCommandInteractionOptionValue::asString)
        .orElse("");

    var component = ComponentsFactory.create(typeOption);
    return event.reply("Selecione o período:").withComponents(ActionRow.of(component.getComponent()));
  }
}
