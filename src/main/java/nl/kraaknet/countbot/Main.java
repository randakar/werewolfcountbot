package nl.kraaknet.countbot;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

class Main {
    public static void main(String[] args) {
        setupPong(args[0]);
    }

    private static void setupPong(final String token) {
        final DiscordClient client = DiscordClient.create(token);

        client.login()
                .flatMapMany(gateway -> gateway.on(MessageCreateEvent.class))
                .map(MessageCreateEvent::getMessage)
                .filter(message -> "!ping".equals(message.getContent()))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Pong!"))
                .blockLast();
    }


}