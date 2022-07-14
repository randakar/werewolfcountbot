class Main {
    public static void main(String[] args) {
        setupPong(args[0]);
    }

    private void setupPong(final String token) {
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