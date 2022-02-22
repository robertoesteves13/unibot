package br.com.unibot.Listeners.Select;

import java.util.Set;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Mono;

public class CollegeExperienceListener {
  private static Snowflake roleFreshmanId = Snowflake.of("943239519480709180");
  private static Snowflake roleExperiencedId = Snowflake.of("945615524925169665");
  private static Snowflake roleVeteranId = Snowflake.of("943239555253956618");

  private static Set<Snowflake> rolesExperience = Set.of(roleFreshmanId, roleExperiencedId, roleVeteranId);

  public static Mono<?> listen(ComponentInteractionEvent pEvent) {
    var event = (SelectMenuInteractionEvent) pEvent;
    var idSelected = event.getValues().get(0);
    var member = event.getInteraction().getMember().get();

    for (var role : rolesExperience) {
      if (member.getRoleIds().contains(role))
        return event.reply(
            "Você já selecionou um dos cargos acima, por favor contatar um dos admins caso foi um erro.")
            .withEphemeral(true);
    }

    if (idSelected.equals("college-freshman")) {
      member.addRole(roleFreshmanId).subscribe();
    } else if (idSelected.equals("college-experienced")) {
      member.addRole(roleExperiencedId).subscribe();
    } else if (idSelected.equals("college-veteran")) {
      member.addRole(roleVeteranId).subscribe();
    }

    return event.reply("Cargo selecionado com sucesso!").withEphemeral(true);
  }
}
