package br.com.unibot;

import br.com.unibot.Components.Component;
import br.com.unibot.Components.PeriodComponent;
import br.com.unibot.Components.CollegeExperienceComponent;

public class ComponentsFactory {
  public static Component create(String type) {
    switch (type) {
      case "period":
        return new PeriodComponent();
      case "college-experience":
        return new CollegeExperienceComponent();
      default:
        return null;
    }
  }
}
