package com.company;

import javax.smartcardio.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static byte[] myCmd = { 0x00, (byte) 0xA4, (byte) 0x04, 0x00, 0x08, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x45, 0x11} ;

    public static void main(String[] args) {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
                execCmd();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error: " + e.getMessage());
            }
        }
    }

    public static void execCmd() throws CardException {
        // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        CardTerminal terminal = terminals.get(0);
        javax.swing.JOptionPane.showMessageDialog(null,"Posez le tel.");
        // establish a connection with the card
        Card card = terminal.connect("*");
        System.out.println("card: " + card);
        CardChannel channel = card.getBasicChannel();
        ResponseAPDU r = channel.transmit(new CommandAPDU(myCmd));
        System.out.println("response: " + (r.getBytes().toString()));
        // disconnect
        String token = new String(r.getData(), StandardCharsets.UTF_8);
        System.out.println("token: " + token);
        card.disconnect(false);
    }

}

