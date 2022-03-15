package br.com.unibot.Components;

import discord4j.core.object.component.ActionComponent;
import discord4j.core.object.component.SelectMenu;

public class CollegeExperienceComponent implements Component {
  public ActionComponent getComponent() {
    return SelectMenu.of("college-experience",
        SelectMenu.Option.of("Entrei neste ano", "college-freshman"),
        SelectMenu.Option.of("Já estava estudando", "college-experienced"),
        SelectMenu.Option.of("Este é meu último ano", "college-veteran")
        ).withPlaceholder("Selecione uma opção");
  }
}
