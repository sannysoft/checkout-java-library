package com.checkout.api.services.charge.response;

import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.charge.request.BaseCharge;
import com.sun.istack.internal.Nullable;

public class Charge extends BaseCharge {
    public String id;
    public String liveMode;
    public String created;
    public Card card;
    public String responseMessage;
    public String responseAdvancedInfo;
    public String responseCode;
    public String status;
    public boolean isCascaded;
    public String authCode;

    @Nullable
    public String redirectUrl;

    /*
     * 3D Secure charge verification specific fields
     */
    @Nullable
    public String enrolled;

    @Nullable
    public String signatureValid;

    @Nullable
    public String authenticationResponse;

    @Nullable
    public String eci;

    @Nullable
    public String cavv;

    @Nullable
    public String xid;
}
