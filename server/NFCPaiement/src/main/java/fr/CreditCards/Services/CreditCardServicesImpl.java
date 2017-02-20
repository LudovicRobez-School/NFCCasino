package fr.CreditCards.Services;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.awt.*;

/**
 * Created by rl613611 on 07/02/2017.
 */
public class CreditCardServicesImpl implements CreditCardServices{

    @Override
    public Response getAllCreditCards(@PathParam("EncodedInfos") String EncodedInfos) {
        return null;
    }

    @Override
    public Response addCreditCard(String EncodedInfos) {
        return null;
    }

    @Override
    public Response getCreditCard(String EncodedInfos) {
        return null;
    }

    @Override
    public Response deleteCreditCard(String EncodedInfos) {
        return null;
    }
}
