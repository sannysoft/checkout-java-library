package com.checkout.api.services.reporting.request;

import com.checkout.api.services.shared.BasePagination;
import com.checkout.api.services.shared.SortOrder;

import java.util.List;

public class TransactionQuery extends BasePagination {
    public String search;
    public List<TransactionFilter> filters;
    public TransactionSortColumn sortColumn;
    public SortOrder sortOrder;
}
