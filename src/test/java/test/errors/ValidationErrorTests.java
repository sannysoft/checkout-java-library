package test.errors;

import com.checkout.APIClient;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.response.PaymentToken;
import com.google.gson.JsonSyntaxException;
import org.junit.Before;
import org.junit.Test;
import test.TestHelper;

import java.io.IOException;

import static org.junit.Assert.*;

public class ValidationErrorTests {

	private APIClient ckoClient;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
	}

	@Test
	public void testCreatePaymentTokenRequest_InvalidCurrency() throws JsonSyntaxException, IOException  {
		PaymentTokenCreate tokenCreatePayload=new PaymentTokenCreate();
		tokenCreatePayload.value="6";
		tokenCreatePayload.currency="kde";

		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(tokenCreatePayload);
		assertTrue(tokenResponse.hasError);
		assertTrue(tokenResponse.error.message.contains("currency"));
		assertNotNull(tokenResponse.error.eventId);
	}
	
	@Test
	public void testCreatePaymentTokenRequest_ValidationError() throws JsonSyntaxException, IOException   {
		PaymentTokenCreate tokenCreatePayload=new PaymentTokenCreate();
		tokenCreatePayload.currency="usd";
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(tokenCreatePayload);
		assertTrue(tokenResponse.hasError);
		assertEquals("70000", tokenResponse.error.errorCode);
		assertTrue(tokenResponse.error.message.contains("Validation"));
		assertNotNull(tokenResponse.error.errors);
		assertNotNull(tokenResponse.error.errorMessageCodes);
	}
	
}
