package com.example.asus.nfccasino;

import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class MyHostApduService extends HostApduService {

	private int messageCounter = 0;
	String token = "TEST";

	@Override
	public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
		if (selectAidApdu(apdu)) {
			Log.i("HCEDEMO", "Application selected");
			return getWelcomeMessage();
		}
		else {
			Log.i("HCEDEMO", "Received: " + new String(apdu));
			return getNextMessage();
		}
	}

	private byte[] getWelcomeMessage() {
		byte[] tabRep = {token.getBytes()[0], token.getBytes()[1], token.getBytes()[2], token.getBytes()[3], (byte)0x90, (byte)0x00};
        return tabRep;
	}

	private byte[] getNextMessage() {
		return ("Message from android: " + messageCounter++).getBytes();
	}

	private boolean selectAidApdu(byte[] apdu) {
		return apdu.length >= 2 && apdu[0] == (byte)0 && apdu[1] == (byte)0xA4;
	}

	@Override
	public void onDeactivated(int reason) {
		Log.i("HCEDEMO", "Deactivated: " + reason);
	}
}