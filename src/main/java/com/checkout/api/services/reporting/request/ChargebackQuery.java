package com.checkout.api.services.reporting.request;

import com.checkout.api.services.shared.BasePagination;
import com.checkout.api.services.shared.SortOrder;

import java.util.List;

public class ChargebackQuery extends BasePagination {
    public String search;
    public List<ChargebackFilter> filters;
    public ChargebackSortColumn sortColumn;
    public SortOrder sortOrder;
}
