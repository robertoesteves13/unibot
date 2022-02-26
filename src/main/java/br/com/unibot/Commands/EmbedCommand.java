package br.com.unibot.Commands;

import br.com.unibot.ComponentsFactory;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.component.ActionRow;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import reactor.core.publisher.Mono;

public class EmbedCommand implements Command {
  public final String name = "embed";

  public EmbedCommand(RestClient client) {
    long appId = client.getApplicationId().block();
    var command = this.build(appId);
    
    client.getApplicationService()
      .createGlobalApplicationCommand(appId, command)
      .subscribe();
  } 

  private ApplicationCommandRequest build(long appId) {
    return ApplicationCommandRequest.builder()
        .name(name)
        .description("Generate embed (admin only)")
        .addOption(ApplicationCommandOptionData.builder()
            .name("label")
            .description("The text that will appear above the embed")
            .type(ApplicationCommandOption.Type.STRING.getValue())
            .required(true)
            .build())
        .addOption(ApplicationCommandOptionData.builder()
            .name("type")
            .description("The pre-programmed embed type")
            .type(ApplicationCommandOption.Type.STRING.getValue())
            .required(true)
            .build())
        .build();
  }

  public Mono<?> run(ChatInputInteractionEvent event) {
    var member = event.getInteraction().getMember().get();
    if (!member.getRoleIds().contains(Snowflake.of("943240128523030628"))) {
      return event.reply("Você não tem permissão para executar este comando!")
          .withEphemeral(true);
    }
    var labelOption = event.getOption("label")
        .flatMap(ApplicationCommandInteractionOption::getValue)
        .map(ApplicationCommandInteractionOptionValue::asString)
        .orElse("");

    var typeOption = event.getOption("type")
        .flatMap(ApplicationCommandInteractionOption::getValue)
        .map(ApplicationCommandInteractionOptionValue::asString)
        .orElse("");

    var component = ComponentsFactory.create(typeOption);
    return event.reply(labelOption).withComponents(ActionRow.of(component.getComponent()));
  }
}
