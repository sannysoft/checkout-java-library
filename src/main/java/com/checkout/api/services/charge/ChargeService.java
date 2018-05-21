package com.checkout.api.services.charge;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.charge.request.*;
import com.checkout.api.services.charge.response.*;
import com.checkout.api.services.charge.response.Void;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

public class ChargeService extends BaseService {

    public Response<Charge> verifyCharge(String paymentToken) throws IOException, JsonSyntaxException {

        String url = String.format(ApiUrls.CHARGE, paymentToken);

        return httpClient.getRequest(url, AppSettings.secretKey, Charge.class);
    }

    public Response<Charge> chargeWithCard(CardCharge payload) throws IOException, JsonSyntaxException {

        return httpClient.postRequest(ApiUrls.CARD_CHARGE, AppSettings.secretKey, gson.toJson(payload), Charge.class);
    }

    public Response<Charge> chargeWithCardId(CardIdCharge payload) throws IOException, JsonSyntaxException {

        return httpClient.postRequest(ApiUrls.CARD_CHARGE, AppSettings.secretKey, gson.toJson(payload), Charge.class);
    }

    public Response<Charge> chargeWithCardToken(CardTokenCharge payload) throws IOException, JsonSyntaxException {

        return httpClient.postRequest(ApiUrls.CARD_TOKEN_CHARGE, AppSettings.secretKey, gson.toJson(payload), Charge.class);
    }

    public Response<Charge> chargeWithDefaultCustomerCard(DefaultCardCharge payload) throws IOException, JsonSyntaxException {

        return httpClient.postRequest(ApiUrls.DEFAULT_CARD_CHARGE, AppSettings.secretKey, gson.toJson(payload), Charge.class);
    }

    public Response<Charge> getCharge(String chargeId) throws IOException, JsonSyntaxException {

        String url = String.format(ApiUrls.CHARGE, chargeId);

        return httpClient.getRequest(url, AppSettings.secretKey, Charge.class);
    }

    public Response<OkResponse> updateCharge(String chargeId, ChargeUpdate payload) throws IOException, JsonSyntaxException {

        String url = String.format(ApiUrls.CHARGE, chargeId);

        return httpClient.putRequest(url, AppSettings.secretKey, gson.toJson(payload), OkResponse.class);
    }

    public Response<Void> voidCharge(String chargeId, ChargeVoid payload) throws IOException, JsonSyntaxException {
        String url = String.format(ApiUrls.VOID_CHARGE, chargeId);

        return httpClient.postRequest(url, AppSettings.secretKey, gson.toJson(payload), Void.class);
    }

    public Response<Capture> captureCharge(String chargeId, ChargeCapture payload) throws IOException, JsonSyntaxException {
        String url = String.format(ApiUrls.CAPTURE_CHARGE, chargeId);

        return httpClient.postRequest(url, AppSettings.secretKey, gson.toJson(payload), Capture.class);
    }

    public Response<Refund> refundRequest(String chargeId, ChargeRefund payload) throws IOException, JsonSyntaxException {
        String url = String.format(ApiUrls.REFUND_CHARGE, chargeId);

        return httpClient.postRequest(url, AppSettings.secretKey, gson.toJson(payload), Refund.class);
    }

    public Response<ChargeHistory> getChargeHistory(String chargeId) throws IOException, JsonSyntaxException {
        String url = String.format(ApiUrls.CHARGE_HISTORY, chargeId);
        return httpClient.getRequest(url, AppSettings.secretKey, ChargeHistory.class);
    }

}
