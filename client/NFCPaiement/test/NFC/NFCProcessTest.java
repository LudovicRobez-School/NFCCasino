package NFC;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.smartcardio.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by ludov on 12/05/2017.
 */
public class NFCProcessTest {

    public static byte[] myCmd = { 0x00, (byte) 0xA4, (byte) 0x04, 0x00, 0x08, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x45, 0x11} ;
    public static final int FILE_DEVICE_SMARTCARD = 0x310000;
    public static final int IOCTL_SMARTCARD_ACR122_ESCAPE_COMMAND = FILE_DEVICE_SMARTCARD + 3500 * 4;

    TerminalFactory factory;
    CardTerminal terminal;
    Card card;

    @Before
    public void setUp() throws Exception {
        factory = EasyMock.createMock(TerminalFactory.class);
        terminal = EasyMock.createMock(CardTerminal.class);
        card = EasyMock.createMock(Card.class);
    }

    @Test
    public void getCardTerminal() throws Exception {
        List<CardTerminal> terminals = new ArrayList<CardTerminal>();
        terminals.add(terminal);
        EasyMock.expect(factory.terminals().list()).andReturn(terminals);
        Assert.assertEquals("should return the CardTerminal", CardTerminal.class , NFCProcess.getCardTerminal(0));
    }

    @Test
    public void sendCommandAPDU() throws Exception {
        ResponseAPDU r = new ResponseAPDU(card.transmitControlCommand(IOCTL_SMARTCARD_ACR122_ESCAPE_COMMAND, myCmd));
        Map<String, String> responseAPDU = new HashMap<String, String>();
        responseAPDU.put("response",r.getBytes().toString());
        responseAPDU.put("token",new String(r.getData(), StandardCharsets.UTF_8));
        EasyMock.expect(card.getBasicChannel().transmit(new CommandAPDU(myCmd))).andReturn(r);
        Assert.assertEquals("should return the Map", responseAPDU , NFCProcess.sendCommandAPDU(terminal));
    }

}