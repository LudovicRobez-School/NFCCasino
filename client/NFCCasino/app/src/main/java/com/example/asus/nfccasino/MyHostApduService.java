package com.example.asus.nfccasino;

import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;


/**
 * @author Gregory Vesic
 * @version 02/05/2017
 */
public class MyHostApduService extends HostApduService {

	private int messageCounter = 0;
	private String token = "value";

	/**
	 * Méthode getToken
	 * @return
	 */
	public String getToken() {return token;}

	/**
	 * Méthode setToken
	 * @param token
	 */
	public void setToken(String token) {this.token = token;}


	/**
	 * Méthode processCommandApdu
	 * @param apdu
	 * @param extras
	 * @return
	 */
	@Override
	public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
		if (selectAidApdu(apdu)) {
			Log.i("HCEDEMO", "Application selected");
			Log.i("token", token);
			return getWelcomeMessage();
		}
		else {
			Log.i("HCEDEMO", "Received: " + new String(apdu));
			return getNextMessage();
		}
	}

	/**
	 * Méthode getWelcomeMessage
	 * @return
	 */
	private byte[] getWelcomeMessage() {
		byte[] tabRep = {token.getBytes()[0], token.getBytes()[1], token.getBytes()[2], token.getBytes()[3], token.getBytes()[4], (byte)0x90, (byte)0x00};
        return tabRep;
	}

	/**
	 * Méthode getNextMessage
	 * @return
	 */
	private byte[] getNextMessage() {
		return ("Message from android: " + messageCounter++).getBytes();
	}

	/**
	 * Méthode selectAidApdu
	 * @param apdu
	 * @return
	 */
	private boolean selectAidApdu(byte[] apdu) {
		return apdu.length >= 2 && apdu[0] == (byte)0 && apdu[1] == (byte)0xA4;
	}

	/**
	 * Méthode onDeactivated
	 * @param reason
	 */
	@Override
	public void onDeactivated(int reason) {
		Log.i("HCEDEMO", "Deactivated: " + reason);
	}
}