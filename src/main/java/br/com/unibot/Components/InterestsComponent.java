package br.com.unibot.Components;

import discord4j.core.object.component.ActionComponent;
import discord4j.core.object.component.SelectMenu;

public class InterestsComponent implements Component {

  public ActionComponent getComponent() {
    return SelectMenu.of("course",
        SelectMenu.Option.of("Programação", "interests-programming"),
        SelectMenu.Option.of("Desenvolvimento Web", "interests-webdev"),
        SelectMenu.Option.of("Infraestrutura", "interests-infrastructure"),
        SelectMenu.Option.of("Matemática", "interests-mathematics")
        ).withPlaceholder("Selecione uma opção");
  }
}
