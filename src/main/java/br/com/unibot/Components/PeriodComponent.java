package br.com.unibot.Components;

import discord4j.core.object.component.ActionComponent;
import discord4j.core.object.component.SelectMenu;

public class PeriodComponent implements Component {

  public ActionComponent getComponent() {
    return SelectMenu.of("period",
        SelectMenu.Option.of("Matutino", "period-morning"),
        SelectMenu.Option.of("Noturno", "period-night")
        ).withPlaceholder("Qual o perído que você estuda?");
  }
}
