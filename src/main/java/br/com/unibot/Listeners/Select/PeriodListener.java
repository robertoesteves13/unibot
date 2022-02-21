package br.com.unibot.Listeners.Select;

import java.util.Set;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Mono;

public class PeriodListener {
  private static String roleMorningId = "943239519480709180";
  private static String roleNightId = "943239555253956618";

  private static Set<Snowflake> rolesPeriod = Set.of(Snowflake.of(roleMorningId), Snowflake.of(roleNightId));

  public static Mono<?> listen(ComponentInteractionEvent pEvent) {
    var event = (SelectMenuInteractionEvent) pEvent;
    var idSelected = event.getValues().get(0);
    var member = event.getInteraction().getMember().get();

    for (var role : rolesPeriod) {
      if (member.getRoleIds().contains(role))
        return event.reply(
            "Você já selecionou um dos cargos acima, por favor contatar um dos admins caso foi um erro.")
            .withEphemeral(true);
    }

    if (idSelected.equals("period-morning")) {
      member.addRole(Snowflake.of(roleMorningId)).subscribe();
    } else if (idSelected.equals("period-night")) {
      member.addRole(Snowflake.of(roleNightId)).subscribe();
    }

    return event.reply("Cargo selecionado com sucesso!").withEphemeral(true);
  }
}
