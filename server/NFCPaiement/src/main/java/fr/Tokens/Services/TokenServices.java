package fr.Tokens.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 21/02/2017.
 */
public interface TokenServices {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    Response addToken(String EncodedInfos);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getToken(String EncodedInfos);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteToken(String EncodedInfos);
}
