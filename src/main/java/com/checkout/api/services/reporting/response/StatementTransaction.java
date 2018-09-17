package com.checkout.api.services.reporting.response;

import java.util.Date;
import java.util.List;

public class StatementTransaction {
    public String id;
    public Date requested_on;
    public String reference;
    public String processing_currency;
    public String payout_currency;
    public String channel_name;
    public String payment_method;
    public String card_type;
    public String card_category;
    public String issuer_country;
    public String merchant_country;
    public List<TransactionAction> actions;
}
