package br.com.unibot;

import br.com.unibot.Components.Component;
import br.com.unibot.Components.PeriodComponent;

public class ComponentsFactory {
  public static Component create(String type) {
    switch (type) {
      case "period":
        return new PeriodComponent();
      default:
        return null;
    }
  }
}
