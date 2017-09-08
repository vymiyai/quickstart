package com.rhcloud.tutorials.quickstart.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class HelpBotCommand implements IBotCommand {

    private List<IBotCommand> commands;

    @Autowired
    public HelpBotCommand(List<IBotCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(String[] tokenizedMessage, MessageReceivedEvent event) {
        StringBuilder response = new StringBuilder();

        response.append("```");

        for (IBotCommand command : this.commands) {
            response.append(command.getCommandDescription());
            response.append("\n");
        }

        // it seems that a component cannot inject itself.
        response.append(this.getCommandDescription());
        response.append("\n```");

        return response.toString();
    }

    @Override
    public String getCommandName() {
        return "!help";
    }

    @Override
    public String getCommandDescription() {
        return "Type !help to show this tooltip.";
    }

}
