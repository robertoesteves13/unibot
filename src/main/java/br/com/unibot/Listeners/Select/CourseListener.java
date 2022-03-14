package br.com.unibot.Listeners.Select;

import java.util.Set;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Mono;

public class CourseListener {
  public static Snowflake roleCSId = Snowflake.of("953012011506216990");
  public static Snowflake roleSIId = Snowflake.of("951542969658408960");
  public static Snowflake roleGuestId = Snowflake.of("953017544413937725");

  private static Set<Snowflake> rolesCourses = Set.of(roleCSId, roleSIId);

  public static Mono<?> listen(ComponentInteractionEvent pEvent) {
    var event = (SelectMenuInteractionEvent) pEvent;
    var idSelected = event.getValues().get(0);
    var member = event.getInteraction().getMember().get();

    for (var role : rolesCourses) {
      if (member.getRoleIds().contains(role))
        return event.reply(
            "Você já selecionou um dos cargos acima, por favor contatar um dos admins caso foi um erro.")
            .withEphemeral(true);
    }

    if (idSelected.equals("course-cs")) {
      member.addRole(roleCSId).subscribe();
    } else if (idSelected.equals("course-si")) {
      member.addRole(roleSIId).subscribe();
    } else if (idSelected.equals("course-guest")) {
      member.addRole(roleGuestId).subscribe();
    }

    return event.reply("Cargo selecionado com sucesso!").withEphemeral(true);
  }
}
