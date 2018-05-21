package com.checkout.api.services.charge.request;

import com.checkout.api.services.shared.Product;

import java.util.List;

public class ChargeVoid extends BaseChargeInfo {
    public List<Product> products;

    public ChargeVoid() {
        super();
    }
}
