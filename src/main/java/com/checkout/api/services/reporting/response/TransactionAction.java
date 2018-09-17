package com.checkout.api.services.reporting.response;

import java.util.Date;
import java.util.List;

public class TransactionAction {

    public String type;
    public String id;
    public Date processed_on;
    public String response_code;
    public String response_description;
    public List<BreakdownItem> breakdown;

}