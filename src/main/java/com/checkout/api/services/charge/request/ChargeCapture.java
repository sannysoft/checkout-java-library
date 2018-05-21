package com.checkout.api.services.charge.request;

import com.checkout.api.services.shared.Product;

import java.util.List;

public class ChargeCapture extends BaseChargeInfo {

    public String value;
    public List<Product> products;

    public ChargeCapture() {
        super();
    }
}
