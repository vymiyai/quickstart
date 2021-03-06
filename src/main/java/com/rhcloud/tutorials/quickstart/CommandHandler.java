package com.rhcloud.tutorials.quickstart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rhcloud.tutorials.quickstart.commands.IBotCommand;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

@Component
public class CommandHandler {

    private HashMap<String, IBotCommand> basicCommands;

    @Autowired
    public void setBasicCommands(List<IBotCommand> injectedBasicCommands) {
        // instantiate the basic commands.
        this.basicCommands = new HashMap<String, IBotCommand>();

        // compile hashmap from command list.
        for (IBotCommand command : injectedBasicCommands)
            this.basicCommands.put(command.getCommandName(), command);
    }

    public List<String> getCommandPrefixes() {
        List<String> prefixes = new ArrayList<String>();
        prefixes.addAll(basicCommands.keySet());
        return prefixes;
    }

    public String[] tokenize(String messageString) {

        return messageString.split(" ");
    }

    private void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        String messageString = event.getMessage().getContent();
        String[] tokenizedMessage = this.tokenize(messageString);
        String commandToken = tokenizedMessage[0].toLowerCase();

        if (this.basicCommands.containsKey(commandToken)) {
            IBotCommand command = this.basicCommands.get(commandToken);
            String response = command.execute(tokenizedMessage, event);
            this.sendMessage(event.getChannel(), response);
        }

        // do nothing if there is no command match.
    }

    @EventSubscriber
    public void onUser(UserJoinEvent event) {
        String response = "Pathetic " + event.getUser().mention() + "-sempai <3";

        this.sendMessage(event.getGuild().getGeneralChannel(), response);
    }

}
