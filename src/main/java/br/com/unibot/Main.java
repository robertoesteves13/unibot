package br.com.unibot;

public class Main {
    public static String token = System.getenv("TOKEN");
    
    public static void main(String[] args) {
        var bot = new DiscordBot(token);
        bot.run();
    }
}
