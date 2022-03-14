package br.com.unibot.Components;

import discord4j.core.object.component.ActionComponent;
import discord4j.core.object.component.SelectMenu;

public class CourseComponent implements Component {

  public ActionComponent getComponent() {
    return SelectMenu.of("course",
        SelectMenu.Option.of("Ciências da computação", "course-cs"),
        SelectMenu.Option.of("Sistemas de informação", "course-si"),
        SelectMenu.Option.of("Sou convidado", "course-guest")
        ).withPlaceholder("");
  }
}
