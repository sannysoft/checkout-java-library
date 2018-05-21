package com.checkout.api.services.charge.request;

import com.checkout.api.services.card.request.CardCreate;

public class CardCharge extends BaseCharge {
    public CardCreate card;

    public CardCharge() {
        super();
    }
}
