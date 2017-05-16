import NFC.NFCProcess;
import Token.TokenProcess;

import javax.smartcardio.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;


public class Main {


    public static void main(String[] args){
        try {
            CardTerminal terminal = NFCProcess.getCardTerminal(0);
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    JOptionPane.showMessageDialog(null, "Voulez-vous faire un paiement?");
                    Map<String, String> responseAPDU = NFCProcess.sendCommandAPDU(terminal);
                    TokenProcess.requete(responseAPDU.get("token"));
                    JOptionPane.showMessageDialog(null, "Paiement Accepté!");
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("error: " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Token Invalide");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hors Service");
        }
    }
}

