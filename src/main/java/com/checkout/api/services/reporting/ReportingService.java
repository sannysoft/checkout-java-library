package com.checkout.api.services.reporting;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.reporting.request.ChargebackQuery;
import com.checkout.api.services.reporting.request.StatementTransactionsQuery;
import com.checkout.api.services.reporting.request.TransactionQuery;
import com.checkout.api.services.reporting.response.ChargebackList;
import com.checkout.api.services.reporting.response.StatementTransactionList;
import com.checkout.api.services.reporting.response.TransactionList;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.checkout.helpers.DateTimeHelper;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

public class ReportingService extends BaseService {

    public ReportingService() {
        super();
    }

    public Response<StatementTransactionList> queryStatementTransactions(StatementTransactionsQuery requestModel) throws IOException, JsonSyntaxException {
        String apiUrl = String.format(
                ApiUrls.REPORTING_STATEMENT_TRANSACTIONS,
                DateTimeHelper.FormatAsUtcDate(requestModel.startDate),
                DateTimeHelper.FormatAsUtcDate(requestModel.endDate)
        );
        return httpClient.getRequest(apiUrl, AppSettings.secretKey, StatementTransactionList.class);
    }

    public Response<TransactionList> queryTransaction(TransactionQuery requestModel) throws IOException, JsonSyntaxException {
        String apiUrl = ApiUrls.REPORTING_TRANSACTIONS;
        return httpClient.postRequest(apiUrl, AppSettings.secretKey, gson.toJson(requestModel), TransactionList.class);
    }

    public Response<ChargebackList> queryChargeback(ChargebackQuery requestModel) throws IOException, JsonSyntaxException {
        String apiUrl = ApiUrls.REPORTING_CHARGEBACKS;
        return httpClient.postRequest(apiUrl, AppSettings.secretKey, gson.toJson(requestModel), ChargebackList.class);
    }
}
