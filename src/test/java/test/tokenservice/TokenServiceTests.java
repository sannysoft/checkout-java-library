package test.tokenservice;

import com.checkout.APIClient;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.request.PaymentTokenUpdate;
import com.checkout.api.services.token.request.VisaCheckoutTokenCreate;
import com.checkout.api.services.token.response.PaymentToken;
import com.checkout.api.services.token.response.VisaCheckoutToken;
import org.junit.Before;
import org.junit.Test;
import test.TestHelper;

import java.io.IOException;

import static org.junit.Assert.*;

public class TokenServiceTests {

    private APIClient ckoClient;
    private APIClient ckoPkClient;

    @Before
    public void setUp() throws Exception {
        ckoClient = new APIClient(TestHelper.secretKey, true);
        ckoPkClient = new APIClient(TestHelper.secretKey, TestHelper.publicKey, true);
    }

    @Test
    public void CreatePaymentTokenTest() throws IOException {
        PaymentTokenCreate payload = TestHelper.getPaymentTokenCreateModel();

        Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(payload);

        assertFalse(tokenResponse.hasError);
        assertEquals(200, tokenResponse.httpStatus);
        assertNotNull(tokenResponse.model.id);
    }

    @Test
    public void UpdatePaymentTokenTest() throws IOException {
        Response<PaymentToken> tokenCreateResponse = ckoClient.tokenService.createPaymentToken(TestHelper.getPaymentTokenCreateModel());

        PaymentTokenUpdate payload = TestHelper.getPaymentTokenUpdateModel();

        Response<OkResponse> tokenUpdateResponse = ckoClient.tokenService.updatePaymentToken(tokenCreateResponse.model.id, payload);

        assertFalse(tokenUpdateResponse.hasError);
        assertEquals(200, tokenUpdateResponse.httpStatus);
        assertEquals(tokenUpdateResponse.model.message, "ok");
    }

    @Test
    public void CreateVisaCheckoutToken_WithoutBinData() throws IOException {
        VisaCheckoutTokenCreate payload = TestHelper.getVisaCheckoutTokenCreateModel(false);

        Response<VisaCheckoutToken> tokenResponse = ckoPkClient.tokenService.createVisaCheckoutToken(payload);

        assertFalse(tokenResponse.hasError);
        assertEquals(200, tokenResponse.httpStatus);
        assertNotNull(tokenResponse.model.id);
        assertNull(tokenResponse.model.binData);
    }

    @Test
    public void CreateVisaCheckoutToken_WithBinData() throws IOException {
        VisaCheckoutTokenCreate payload = TestHelper.getVisaCheckoutTokenCreateModel(true);

        Response<VisaCheckoutToken> tokenResponse = ckoPkClient.tokenService.createVisaCheckoutToken(payload);

        assertFalse(tokenResponse.hasError);
        assertEquals(200, tokenResponse.httpStatus);
        assertNotNull(tokenResponse.model.id);
        assertNotNull(tokenResponse.model.binData);
    }
}
