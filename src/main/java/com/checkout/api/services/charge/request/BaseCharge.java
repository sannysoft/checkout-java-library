package com.checkout.api.services.charge.request;

import com.checkout.api.services.shared.Address;
import com.checkout.api.services.shared.Product;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BaseCharge extends BaseChargeInfo {

    public static final String YES = "Y";
    public static final String NO = "N";

    public static final int THREEDS_YES = 2;
    public static final int THREEDS_NO = 1;

    public static final String TYPE_REGULAR = "1";
    public static final String TYPE_RECURRING = "2";
    public static final String TYPE_MOTO = "3";

    public String value;
    public String currency;

    public int chargeMode = THREEDS_NO;

    public String autoCapture = YES;
    public int autoCapTime;

    public String email;
    public String customerIp;
    public String customerId;
    public String customerName;

    public List<Product> products;

    public Address shippingDetails;
    public Address billingDetails;

    public String successUrl;
    public String failUrl;

    public BillingDescriptor descriptor;

    public boolean attemptN3D;
    public boolean riskCheck;

    public String transactionIndicator = TYPE_REGULAR;

    public boolean cardOnFile;
    public String previousChargeId;

    public BaseCharge() {
    }
}
