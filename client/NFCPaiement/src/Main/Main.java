package Main;

/**
 * Created by rl613611 on 28/02/2017.
 */
import javax.smartcardio.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static byte[] myCmd = { 0x00, (byte) 0xA4,  0x00, (byte) 0x04, 0x08, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x45, 0x00, 0x00} ;

    public static void main(String[] args) throws InterruptedException, CardException {
            // show the list of available terminals
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);
            // get the first terminal
            CardTerminal terminal = terminals.get(0);
            // establish a connection with the card
            Card card = terminal.connect("T=0");
            System.out.println("card: " + card);
            CardChannel channel = card.getBasicChannel();
            ResponseAPDU r = channel.transmit(new CommandAPDU(myCmd));
            System.out.println("response: " + (r.getBytes().toString()));
            // disconnect
            String token = new String(r.getData(), StandardCharsets.UTF_8);
            System.out.println("token: "+ token);
            card.disconnect(false);
    }

}
