package br.com.unibot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;

import discord4j.common.JacksonResources;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import reactor.core.publisher.Mono;

public class CommandHandler {
  private static String commandsFolderName = "commands/";

  private final long appId;
  private final RestClient restClient;

  public CommandHandler(RestClient restClient) {
    this.restClient = restClient;
    this.appId = restClient.getApplicationId().block();
  }

  public Publisher<?> executeCommand(ChatInputInteractionEvent event) {
    var command = CommandFactory.getCommand(event.getCommandName());
    if (command == null) return Mono.empty();

    return command.run(event);
  }

  protected void registerCommands() throws IOException {
    final var d4jMapper = JacksonResources.create();
    final var appService = restClient.getApplicationService();
    final var filenames = CommandHandler.getFileNames();

    var commands = new ArrayList<ApplicationCommandRequest>();
    for (var json : getCommandsJson(filenames)) {
      System.out.println(json);
      var request = d4jMapper.getObjectMapper().readValue(json, ApplicationCommandRequest.class);
      commands.add(request);
    }
    
    appService.bulkOverwriteGlobalApplicationCommand(appId, commands)
      .subscribe();
  }

  private static List<String> getFileNames() throws IOException {
    var resourcesPath = CommandHandler.class.getClassLoader().getResource(commandsFolderName).getPath();
    final var commandRootPath = Paths.get(resourcesPath);

    try {
      return Files.walk(commandRootPath)
        .filter(Files::isRegularFile)
        .map(path -> commandRootPath.relativize(path).toString())
        .collect(Collectors.toList());
    } catch (IOException err) {
      throw new IllegalArgumentException(err);
    }
  }

  private static List<String> getCommandsJson(List<String> filenames) throws IOException {
    var url = CommandHandler.class.getClassLoader().getResource(commandsFolderName);
    Objects.requireNonNull(url, commandsFolderName + " could not be found");

    var list = new ArrayList<String>();
    for (String file : filenames) {
      var resourceFileAsString = getResourceFileAsString(commandsFolderName + file);
      list.add(Objects.requireNonNull(resourceFileAsString, "Command file not found: " + file));
    }

    return list;
  }

  private static String getResourceFileAsString(String filename) throws IOException {
    try (var resourceAsStream = ClassLoader.getSystemResourceAsStream(filename)) {
      if (resourceAsStream == null)
        return null;
      try (var inputStreamReader = new InputStreamReader(resourceAsStream);
          var reader = new BufferedReader(inputStreamReader)) {

        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    }
  }
}
