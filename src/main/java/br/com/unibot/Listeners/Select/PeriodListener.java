package br.com.unibot.Listeners.Select;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Mono;

public class PeriodListener {
  public static Mono<?> listen(ComponentInteractionEvent event) {
    var selectEvent = (SelectMenuInteractionEvent) event;
    var idSelected = selectEvent.getValues().get(0);
    var member = selectEvent.getInteraction().getMember().get();

    if (idSelected.equals("period-morning")) {
      System.out.println("Morning selected");
      member.addRole(Snowflake.of("943239519480709180")).subscribe();
    } else if (idSelected.equals("period-night")) {
      System.out.println("Night selected");
      member.addRole(Snowflake.of("943239555253956618")).subscribe();
    }

    return selectEvent.reply("Período selecionado: " + idSelected).withEphemeral(true);
  }
}
