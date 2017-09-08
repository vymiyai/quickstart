package com.rhcloud.tutorials.quickstart.commands;

import org.springframework.stereotype.Component;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class ExampleCommand implements IBotCommand {

    @Override
    public String execute(String[] tokenizedMessage, MessageReceivedEvent event) {
        return "This is an example command output.";
    }

    @Override
    public String getCommandName() {
        return "!example";
    }

    @Override
    public String getCommandDescription() {
        return "Type !example to get an example command output.";
    }
}
