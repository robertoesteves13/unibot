package br.com.unibot.Listeners.Select;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Mono;

public class InterestsListener {
  private static Snowflake roleProgrammingId = Snowflake.of("953393515403030538");
  private static Snowflake roleWebDevId = Snowflake.of("953393567517270016");
  private static Snowflake roleInfrastructureId = Snowflake.of("953393567517270016");
  private static Snowflake roleMathematicsId = Snowflake.of("953393686425763850");

  public static Mono<?> listen(ComponentInteractionEvent pEvent) {
    var event = (SelectMenuInteractionEvent) pEvent;
    var idSelected = event.getValues().get(0);
    var member = event.getInteraction().getMember().get();
    var memberRoleIds = member.getRoleIds();

    if (idSelected.equals("interests-programming")) {
      if (memberRoleIds.contains(roleProgrammingId))
        member.removeRole(roleProgrammingId);
      else
        member.addRole(roleProgrammingId).subscribe();
    } else if (idSelected.equals("interests-webdev")) {
      if (memberRoleIds.contains(roleWebDevId))
        member.removeRole(roleWebDevId);
      else
        member.addRole(roleWebDevId).subscribe();
    } else if (idSelected.equals("interests-infrastructure")) {
      if (memberRoleIds.contains(roleInfrastructureId))
        member.removeRole(roleInfrastructureId);
      else
        member.addRole(roleInfrastructureId).subscribe();
    } else if (idSelected.equals("interests-mathematics")) {
      if (memberRoleIds.contains(roleMathematicsId))
        member.removeRole(roleMathematicsId);
      else
        member.addRole(roleMathematicsId).subscribe();
    }

    return event.reply("Cargo selecionado com sucesso!").withEphemeral(true);
  }
}
