package br.com.unibot.Commands;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unibot.Models.Comic;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

public class ComicCommand implements Command {
  private String name;

  private Comic comic;
  private int comicAmount = 2584;

  public ComicCommand() {
    this.name = "comic";
  }

  public String getName() {
    return this.name;
  }

  public Mono<?> run(ChatInputInteractionEvent event) {
    var random = Math.round(Math.random() * comicAmount);
    var urlString = "https://xkcd.com/" + random + "/info.0.json";

    try {
      var endpoint = new URL(urlString);
      var connection = (HttpsURLConnection) endpoint.openConnection();
      connection.setRequestProperty("accept", "application/json");

      var responseStream = connection.getInputStream();

      var mapper = new ObjectMapper();
      this.comic = mapper.readValue(responseStream, Comic.class);
    } catch (Exception err) {
      System.out.println(err);
      return event.reply("Erro ao fazer a requisição HTTP da comic").withEphemeral(true);
    }

    var embed = EmbedCreateSpec.builder()
        .color(Color.DEEP_SEA)
        .title(comic.safe_title)
        .url("https://xkcd.com/" + random)
        .description(comic.alt)
        .author("xkcd", "https://xkcd.com/", "")
        .image(comic.img)
        .build();

    return event.reply().withEmbeds(embed);
  }
}
