package com.checkout.api.services.customer.request;

import com.checkout.api.services.shared.Phone;

import java.util.Map;

public class BaseCustomer {

    public String name;
    public String email;
    public Phone phone;
    public String description;
    public Map<String, String> metadata;
}
