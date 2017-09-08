package com.rhcloud.tutorials.quickstart.commands;

import org.springframework.stereotype.Component;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class TsunCommand implements IBotCommand {

    @Override
    public String execute(String[] tokenizedMessage, MessageReceivedEvent event) {
        return "I hate you, " + event.getAuthor().mention() + "-sempai!";
    }

    @Override
    public String getCommandName() {
        return "!tsun";
    }

    @Override
    public String getCommandDescription() {
        return "Type !tsun for harsh words.";
    }
}
