package NFC;

import javax.smartcardio.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by ludov on 12/05/2017.
 */
public class NFCProcess {

    public static byte[] myCmd = { 0x00, (byte) 0xA4, (byte) 0x04, 0x00, 0x08, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x45, 0x11} ;

    public static CardTerminal getCardTerminal(int terminal) throws CardException {
        // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        return terminals.get(terminal);
    }

    public static Map<String,String> sendCommandAPDU(CardTerminal terminal)throws CardException{
        Map<String, String> responseAPDU = new HashMap<String, String>();
        Card card = terminal.connect("*");
        System.out.println("card: " + card);
        CardChannel channel = card.getBasicChannel();
        ResponseAPDU r = channel.transmit(new CommandAPDU(myCmd));
        System.out.println("response: " + (r.getBytes().toString()));
        responseAPDU.put("response",r.getBytes().toString());
        // disconnect
        String token = new String(r.getData(), StandardCharsets.UTF_8);
        System.out.println("token: " + token);
        responseAPDU.put("token",token);
        card.disconnect(false);
        return responseAPDU;
    }

}
