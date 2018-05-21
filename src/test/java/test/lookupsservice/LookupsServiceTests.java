package test.lookupsservice;

import com.checkout.APIClient;
import com.checkout.api.services.lookups.response.BinLookup;
import com.checkout.api.services.lookups.response.BinLookupWithCardToken;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;
import org.junit.Before;
import org.junit.Test;
import test.TestHelper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class LookupsServiceTests {

    private APIClient ckoClient;

    @Before
    public void setUp() throws Exception {
        ckoClient = new APIClient(TestHelper.secretKey, true);
    }

    @Test
    public void binLookupWithCardToken() throws JsonSyntaxException, IOException {
        String cardToken = "card_tok_04426630-559B-4FB1-8355-823FDAC27CDB";// set card token for bin lookup

        Response<BinLookupWithCardToken> binLookupResponse = ckoClient.lookupsService.binLookupWithCardToken(cardToken);
        BinLookupWithCardToken binLookup = binLookupResponse.model;

        assertFalse(binLookupResponse.hasError);
        assertEquals(200, binLookupResponse.httpStatus);
        assertEquals("454347", binLookup.bin);
        assertEquals("STATE BANK OF MAURITIUS, LTD.", binLookup.issuer);
        assertEquals("Mauritius", binLookup.issuerCountry);
        assertEquals("MU", binLookup.issuerCountryIso2);
        assertEquals("Visa", binLookup.scheme);
        assertEquals("Credit", binLookup.type);
        assertEquals("Consumer", binLookup.category);
        assertEquals("F", binLookup.productId);
        assertEquals("Visa Classic", binLookup.productType);
    }

    @Test
    public void binLookup() throws JsonSyntaxException, IOException {
        String binNumber = "454347"; // set bin

        Response<BinLookup> binLookupResponse = ckoClient.lookupsService.binLookup(binNumber);
        BinLookup binLookup = binLookupResponse.model;

        assertFalse(binLookupResponse.hasError);
        assertEquals(200, binLookupResponse.httpStatus);
        assertEquals("454347", binLookup.bin);
        assertEquals("MU", binLookup.issuerCountryISO2);
        assertEquals("Mauritius", binLookup.countryName);
        assertEquals("STATE BANK OF MAURITIUS, LTD.", binLookup.bankName);
        assertEquals("Visa", binLookup.cardScheme);
        assertEquals("Credit", binLookup.cardType);
        assertEquals("Consumer", binLookup.cardCategory);
    }
}
