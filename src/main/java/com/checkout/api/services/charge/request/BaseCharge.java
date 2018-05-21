package com.checkout.api.services.charge.request;

import com.checkout.api.services.shared.Address;
import com.checkout.api.services.shared.Product;

import java.util.List;

public class BaseCharge extends BaseChargeInfo {

    public static final String YES = "y";
    public static final String NO = "n";

    public static final int THREEDS = 2;
    public static final int NON_THREEDS = 1;

    public String value;
    public String currency;
    public int chargeMode;
    public String autoCapture;
    public int autoCapTime;
    public String customerIp;
    public String email;
    public String customerId;
    public String customerName;
    public List<Product> products;
    public Address shippingDetails;
    public String redirectUrl;
    public BillingDescriptor descriptor;
    public boolean attemptN3D;
    public boolean riskCheck;
    public String transactionIndicator;
    public boolean cardOnFile;
    public String previousChargeId;

    public BaseCharge() {
        autoCapture = YES;
        chargeMode = NON_THREEDS; //Default mode is no 3D
    }
}
