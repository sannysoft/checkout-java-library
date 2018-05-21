package test;

import com.checkout.api.services.card.request.CardCreate;
import com.checkout.api.services.card.request.CardUpdate;
import com.checkout.api.services.charge.request.*;
import com.checkout.api.services.customer.request.BaseCustomer;
import com.checkout.api.services.customer.request.CustomerCreate;
import com.checkout.api.services.customer.request.CustomerUpdate;
import com.checkout.api.services.payouts.request.BasePayout;
import com.checkout.api.services.reporting.request.*;
import com.checkout.api.services.shared.Address;
import com.checkout.api.services.shared.Phone;
import com.checkout.api.services.shared.Product;
import com.checkout.api.services.shared.SortOrder;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.request.PaymentTokenUpdate;
import com.checkout.api.services.token.request.VisaCheckoutTokenCreate;

import java.util.*;

public class TestHelper {
    public static String secretKey = "sk_test_32b9cb39-1cd6-4f86-b750-7069a133667d";
    public static String publicKey = "pk_test_763e0a06-2eb2-4ac0-8099-65009064bded";

    private static Random randomNumber = new Random();

    private static String getRandomEmail() {
        return UUID.randomUUID().toString() + "@test.com";
    }

    private static String getRandomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char ignored : chars) {
            char c = chars[randomNumber.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    private static Map<String, String> getRandomMetadata() {
        Map<String, String> metadata = new HashMap<>();

        metadata.put("key1", getRandomString().substring(20));
        metadata.put("key2", getRandomString().substring(20));

        return metadata;
    }

    private static Address getRandomAddress() {
        Address address = new Address();

        address.addressLine1 = getRandomString();
        address.addressLine2 = getRandomString();
        address.postcode = getRandomString().substring(20);
        address.country = "US";
        address.city = getRandomString().substring(15);
        address.state = getRandomString();
        address.phone = getRandomPhone();
        return address;
    }

    private static List<Product> getRandomProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.description = getRandomString().substring(20);
        product1.name = getRandomString().substring(20);
        product1.quantity = 1;
        product1.shippingCost = 10.50;
        product1.price = 10;
        product1.sku = getRandomString().substring(25);
        product1.trackingUrl = "http://www.tracker.com";

        Product product2 = new Product();
        product2.description = getRandomString().substring(20);
        product2.name = getRandomString().substring(20);
        product1.price = 10;
        product2.quantity = 1;
        product2.shippingCost = 20.20;
        product2.sku = getRandomString().substring(25);
        product2.trackingUrl = "http://www.tracker.com";

        products.add(product1);
        products.add(product2);

        return products;
    }

    private static Phone getRandomPhone() {
        StringBuilder sb = new StringBuilder();
        Phone phone = new Phone();

        char[] chars = "1234567890".toCharArray();

        for (char ignored : chars) {
            char c = chars[randomNumber.nextInt(chars.length)];
            sb.append(c);
        }

        phone.countryCode = "44"; //Phone country code
        phone.number = sb.toString();

        return phone;
    }

    /*
     * Payment Token Helpers
     */
    public static PaymentTokenCreate getPaymentTokenCreateModel() {
        PaymentTokenCreate tokenPayload = new PaymentTokenCreate();

        tokenPayload.value = Integer.toString(randomNumber.nextInt(1000));
        tokenPayload.currency = "USD";
        tokenPayload.autoCapture = "N";
        tokenPayload.customerIp = "88.216.3.135";
        tokenPayload.description = "test";
        tokenPayload.metadata = getRandomMetadata();
        tokenPayload.products = getRandomProducts();
        tokenPayload.shippingDetails = getRandomAddress();

        return tokenPayload;
    }

    public static PaymentTokenUpdate getPaymentTokenUpdateModel() {
        PaymentTokenUpdate tokenUpdatePayload = new PaymentTokenUpdate();

        tokenUpdatePayload.trackId = "TRK123456";
        tokenUpdatePayload.metadata = getRandomMetadata();
        tokenUpdatePayload.udf1 = getRandomString().substring(20);
        tokenUpdatePayload.udf2 = getRandomString().substring(20);
        tokenUpdatePayload.udf3 = getRandomString().substring(20);
        tokenUpdatePayload.udf4 = getRandomString().substring(20);
        tokenUpdatePayload.udf5 = getRandomString().substring(20);

        return tokenUpdatePayload;
    }

    public static VisaCheckoutTokenCreate getVisaCheckoutTokenCreateModel(boolean includeBinData) {
        VisaCheckoutTokenCreate tokenPayload = new VisaCheckoutTokenCreate();
        tokenPayload.callId = "3023957850660287501";
        tokenPayload.includeBinData = includeBinData;

        return tokenPayload;
    }


    /*
     * Card Helpers
     */
    public static CardCreate getCardCreateModel() {
        CardCreate cardCreate = new CardCreate();

        cardCreate.name = getRandomString();
        cardCreate.number = "4242424242424242";
        cardCreate.expiryMonth = "06";
        cardCreate.expiryYear = "2018";
        cardCreate.cvv = "100";
        cardCreate.billingDetails = getRandomAddress();

        return cardCreate;
    }

    public static CardUpdate getCardUpdateModel() {
        CardUpdate cardUpdate = new CardUpdate();

        cardUpdate.name = getRandomString();
        cardUpdate.expiryMonth = "06";
        cardUpdate.expiryYear = "2017";
        cardUpdate.billingDetails = getRandomAddress();
        cardUpdate.defaultCard = true;

        return cardUpdate;
    }


    /*
     * Customer Helpers
     */
    public static CustomerCreate getCustomerCreateModel() throws InstantiationException, IllegalAccessException {
        CustomerCreate customerCreate = getBaseCustomerModel(CustomerCreate.class);
        customerCreate.card = getCardCreateModel();

        return customerCreate;
    }

    public static CustomerUpdate getCustomerUpdateModel() throws InstantiationException, IllegalAccessException {
        return getBaseCustomerModel(CustomerUpdate.class);
    }

    private static <T extends BaseCustomer> T getBaseCustomerModel(Class<T> customerType) throws InstantiationException, IllegalAccessException {
        T customerPayload = customerType.newInstance();
        customerPayload.name = TestHelper.getRandomString();
        customerPayload.description = TestHelper.getRandomString();
        customerPayload.email = TestHelper.getRandomEmail();
        customerPayload.phone = TestHelper.getRandomPhone();
        customerPayload.metadata = TestHelper.getRandomMetadata();

        return customerPayload;
    }


    /*
     * Charge Helpers
     */
    public static CardCharge getCardChargeModel() throws InstantiationException, IllegalAccessException {
        CardCharge cardCharge = getBaseChargeModel(CardCharge.class);

        cardCharge.transactionIndicator = "1";
        cardCharge.card = getCardCreateModel();

        return cardCharge;
    }

    public static CardCharge getCardChargeModel3ds() throws InstantiationException, IllegalAccessException {
        CardCharge cardCharge = getBaseChargeModel(CardCharge.class);

        cardCharge.transactionIndicator = "1";
        cardCharge.chargeMode = 2;
        cardCharge.card = getCardCreateModel();

        return cardCharge;
    }

    public static CardCharge getCardChargeModelAttemptN3d() throws InstantiationException, IllegalAccessException {
        CardCharge cardCharge = getBaseChargeModel(CardCharge.class);
        cardCharge.value = "100150"; // To trigger "Card not 3DS Enabled" https://docs.checkout.com/getting-started/testing-and-simulating-charges

        cardCharge.transactionIndicator = "1";
        cardCharge.chargeMode = 2;
        cardCharge.attemptN3D = true;
        cardCharge.card = getCardCreateModel();
        cardCharge.card.number = "378282246310005"; // We need to use a test card other than Visa, else normal 10000 response code will be returned
        cardCharge.card.expiryMonth = "06";
        cardCharge.card.expiryYear = "2018";
        cardCharge.card.cvv = "1000";

        return cardCharge;
    }

    public static ChargeUpdate getChargeUpdateModel() {
        ChargeUpdate chargeUpdate = new ChargeUpdate();

        chargeUpdate.trackId = "TRK123456";
        chargeUpdate.description = getRandomString().substring(20);
        chargeUpdate.metadata = getRandomMetadata();
        chargeUpdate.udf1 = getRandomString().substring(20);
        chargeUpdate.udf2 = getRandomString().substring(20);
        chargeUpdate.udf3 = getRandomString().substring(20);
        chargeUpdate.udf4 = getRandomString().substring(20);
        chargeUpdate.udf5 = getRandomString().substring(20);

        return chargeUpdate;
    }

    public static ChargeVoid getChargeVoidModel() throws InstantiationException, IllegalAccessException {
        ChargeVoid chargeVoid = getBaseChargeInfoModel(ChargeVoid.class);
        chargeVoid.products = getRandomProducts();
        return chargeVoid;
    }

    public static ChargeCapture getChargeCaptureModel() throws InstantiationException, IllegalAccessException {
        ChargeCapture chargeCapture = getBaseChargeInfoModel(ChargeCapture.class);
        chargeCapture.products = getRandomProducts();
        return chargeCapture;
    }

    public static ChargeRefund getChargeRefundModel() throws InstantiationException, IllegalAccessException {
        ChargeRefund chargeRefund = getBaseChargeInfoModel(ChargeRefund.class);
        chargeRefund.products = getRandomProducts();
        return chargeRefund;
    }

    public static CardIdCharge getCardIdChargeModel(String cardId, String customerEmail) throws InstantiationException, IllegalAccessException {
        CardIdCharge cardIdCharge = getBaseChargeModel(CardIdCharge.class);
        cardIdCharge.transactionIndicator = "1";
        cardIdCharge.cardId = cardId;
        cardIdCharge.email = customerEmail;

        return cardIdCharge;
    }

    public static CardTokenCharge getCardTokenChargeModel(String cardToken) throws InstantiationException, IllegalAccessException {
        CardTokenCharge cardTokenCharge = getBaseChargeModel(CardTokenCharge.class);
        cardTokenCharge.transactionIndicator = "1";
        cardTokenCharge.cardToken = cardToken;
        return cardTokenCharge;
    }


    public static DefaultCardCharge getDefaultCardChargeModel(String email) throws InstantiationException, IllegalAccessException {
        DefaultCardCharge defaultCardCharge = getBaseChargeModel(DefaultCardCharge.class);
        defaultCardCharge.transactionIndicator = "1";
        defaultCardCharge.email = email;

        return defaultCardCharge;
    }

    private static <T extends BaseCharge> T getBaseChargeModel(Class<T> chargeType) throws InstantiationException, IllegalAccessException {
        T baseCharge = chargeType.newInstance();

        baseCharge.autoCapTime = 0;
        baseCharge.email = getRandomEmail();
        baseCharge.currency = "usd";
        baseCharge.value = "100";
        baseCharge.autoCapture = "N";
        baseCharge.trackId = "TRK12345";
        baseCharge.customerIp = "82.23.168.254";
        baseCharge.customerName = "Test Customer";
        baseCharge.description = getRandomString().substring(20);
        baseCharge.descriptor = new BillingDescriptor();
        baseCharge.descriptor.name = "Amigo ltd.";
        baseCharge.descriptor.city = "London";
        baseCharge.metadata = getRandomMetadata();
        baseCharge.products = getRandomProducts();
        baseCharge.shippingDetails = getRandomAddress();
        baseCharge.udf1 = getRandomString().substring(20);
        baseCharge.udf2 = getRandomString().substring(20);
        baseCharge.udf3 = getRandomString().substring(20);
        baseCharge.udf4 = getRandomString().substring(20);
        baseCharge.udf5 = getRandomString().substring(20);

        return baseCharge;
    }

    private static <T extends BaseChargeInfo> T getBaseChargeInfoModel(Class<T> chargeType) throws InstantiationException, IllegalAccessException {

        T baseChargeInfo = chargeType.newInstance();
        baseChargeInfo.trackId = "TRK12345";
        baseChargeInfo.description = getRandomString().substring(20);
        baseChargeInfo.metadata = getRandomMetadata();
        baseChargeInfo.udf1 = getRandomString().substring(20);
        baseChargeInfo.udf2 = getRandomString().substring(20);
        baseChargeInfo.udf3 = getRandomString().substring(20);
        baseChargeInfo.udf4 = getRandomString().substring(20);
        baseChargeInfo.udf5 = getRandomString().substring(20);

        return baseChargeInfo;
    }

    public static TransactionQuery getQueryTransactionModel(String searchValue, Date fromDate, Date toDate, TransactionSortColumn sortColumn, SortOrder sortOrder, Integer pageSize, String pageNumber, List<TransactionFilter> filters) {
        TransactionQuery query = new TransactionQuery();

        query.fromDate = fromDate;
        query.toDate = toDate;
        query.pageSize = pageSize;
        query.pageNumber = pageNumber;
        query.sortColumn = sortColumn;
        query.sortOrder = sortOrder;
        query.search = searchValue;
        query.filters = filters;

        return query;
    }

    public static ChargebackQuery getQueryChargebackModel(String searchValue, Date fromDate, Date toDate, ChargebackSortColumn sortColumn, SortOrder sortOrder, Integer pageSize, String pageNumber, List<ChargebackFilter> filters) {
        ChargebackQuery query = new ChargebackQuery();

        query.fromDate = fromDate;
        query.toDate = toDate;
        query.pageSize = pageSize;
        query.pageNumber = pageNumber;
        query.sortColumn = sortColumn;
        query.sortOrder = sortOrder;
        query.search = searchValue;
        query.filters = filters;

        return query;
    }

    public static BasePayout getPayoutModel(String cardId) {
        BasePayout basePayout = new BasePayout();

        basePayout.destination = cardId;
        basePayout.value = 200;
        basePayout.currency = "USD";
        basePayout.firstName = "John";
        basePayout.lastName = "Doe";

        return basePayout;
    }
}
