package com.checkout.api.services.charge.request;

import com.checkout.api.services.card.request.CardCreate;

public class CardCharge extends BaseCharge {
    public CardCreate card;

    public String successUrl;
    public String failUrl;

    public CardCharge() {
        super();
    }
}
