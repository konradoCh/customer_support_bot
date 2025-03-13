package main.java.com.ffc.bot.responses;

import org.glassfish.jersey.model.internal.RankedComparator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static main.java.com.ffc.bot.responses.CallBackData.*;

public class TextStrategy implements Strategy{
    @Override
    public SendMessage getResponse(Update update) {

        String unknownOption = "We are sorry byt the command you sent could not be interpreted. Put /help for list of available commands";

        String chatId = update.getMessage().getChatId().toString();

        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(unknownOption);

        if (update.getMessage().hasText()) {

            String textReceived = update.getMessage().getText().trim();

            if (textReceived.equalsIgnoreCase("/start")) {

                response.setText("Welcome to ABC, please select the option you are looking for.");

                //First create the keyboard
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>(); // tworzenie przycisków

                //Then we create the buttons: row
                List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

                InlineKeyboardButton orderStatus = new InlineKeyboardButton();

                orderStatus.setText("Check Order Status");
                orderStatus.setCallbackData(ORDER_STATUS.toString());

                InlineKeyboardButton informationButton = new InlineKeyboardButton();
                informationButton.setText("Get general information");
                informationButton.setCallbackData(MORE_INFORMATION.toString());

                InlineKeyboardButton contactHumanSupportButton = new InlineKeyboardButton();
                contactHumanSupportButton.setText("Contact human support");
                contactHumanSupportButton.setCallbackData(CONTACT_HUMAN.toString());

                buttonsRow.add(orderStatus);
                buttonsRow.add(informationButton);
                buttonsRow.add(contactHumanSupportButton);

                keyboard.add(buttonsRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(keyboard);

                response.setReplyMarkup(inlineKeyboardMarkup);

                return response;
            }
        }

        return null;
    }
}
