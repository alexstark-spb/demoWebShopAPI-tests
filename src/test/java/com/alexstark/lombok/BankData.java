package com.alexstark.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankData {

    @JsonProperty ("design_url")
    private String designUrl;
    @JsonProperty ("transactions_total_amount")
    private String transactionsTotalAmount;
    @JsonProperty ("tariff_avg_month_balance")
    private String tariffAvgMonthBalance;
    private String type;
    @JsonProperty ("closing_date")
    private String closingDate;
    @JsonProperty ("partial_withdraw_available")
    private String partialWithdrawAvailable;
    @JsonProperty ("refill_available")
    private String refillAvailable;
    @JsonProperty ("blocked_amount")
    private String blockedAmount;
    @JsonProperty ("scheme_id")
    private String schemeId;
    private String pan;
    @JsonProperty ("account_id")
    private String accountId;
    @JsonProperty ("title_small")
    private String titleSmall;
    private String title;
    private String balance;
    private String currency;
    private String isSalary;
}