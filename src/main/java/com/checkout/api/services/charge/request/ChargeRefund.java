package com.checkout.api.services.charge.request;

import com.checkout.api.services.shared.Product;

import java.util.List;

public class ChargeRefund extends BaseChargeInfo {
    public String value;
    public List<Product> products;

    public ChargeRefund() {
        super();
    }
}
