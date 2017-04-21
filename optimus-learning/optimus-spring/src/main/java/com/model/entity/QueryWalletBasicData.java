package com.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class QueryWalletBasicData {
    // 钱包余额统计截止时间
    private Date gmtCreate;

    private Date statDate;

    // 钱包当日购买金额
    private BigDecimal walletPurchaseAmountDay;

    // 当日赎回债权购买金额
    private BigDecimal redemDebptPurchaseAmountDay;

    // 当日基础债权购买金额
    private BigDecimal baseDebptPurchaseAmountDay;

    // 累计基础债权购买金额
    private BigDecimal baseDebptPurchaseAmountTtl;

    // 当日赎回债权申请金额
    private BigDecimal redemDebptApplyAmountDay;

    // 当日赎回债权申请余额
    private BigDecimal redemDebptApplyBalanceDay;

    // 当日借款人还款
    private BigDecimal borrowerRepaymentToday;

    // 借款人累计还款
    private BigDecimal borrowerRepaymentTotal;

    // 支付钱包利息
    private BigDecimal payInterestWallet;

    // 钱包存量
    private BigDecimal walletStock;

    // 应付钱包利息
    private BigDecimal dealInterestWallet;

    /**
     * 返回钱包余额统计截止时间
     * @return 钱包余额统计截止时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置钱包余额统计截止时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    /**
     * 返回钱包当日购买金额
     * @return 钱包当日购买金额
     */
    public BigDecimal getWalletPurchaseAmountDay() {
        return walletPurchaseAmountDay;
    }

    /**
     * 设置钱包当日购买金额
     */
    public void setWalletPurchaseAmountDay(BigDecimal walletPurchaseAmountDay) {
        this.walletPurchaseAmountDay = walletPurchaseAmountDay;
    }

    /**
     * 返回当日赎回债权购买金额
     * @return 当日赎回债权购买金额
     */
    public BigDecimal getRedemDebptPurchaseAmountDay() {
        return redemDebptPurchaseAmountDay;
    }

    /**
     * 设置当日赎回债权购买金额
     */
    public void setRedemDebptPurchaseAmountDay(BigDecimal redemDebptPurchaseAmountDay) {
        this.redemDebptPurchaseAmountDay = redemDebptPurchaseAmountDay;
    }

    /**
     * 返回当日基础债权购买金额
     * @return 当日基础债权购买金额
     */
    public BigDecimal getBaseDebptPurchaseAmountDay() {
        return baseDebptPurchaseAmountDay;
    }

    /**
     * 设置当日基础债权购买金额
     */
    public void setBaseDebptPurchaseAmountDay(BigDecimal baseDebptPurchaseAmountDay) {
        this.baseDebptPurchaseAmountDay = baseDebptPurchaseAmountDay;
    }

    /**
     * 返回累计基础债权购买金额
     * @return 累计基础债权购买金额
     */
    public BigDecimal getBaseDebptPurchaseAmountTtl() {
        return baseDebptPurchaseAmountTtl;
    }

    /**
     * 设置累计基础债权购买金额
     */
    public void setBaseDebptPurchaseAmountTtl(BigDecimal baseDebptPurchaseAmountTtl) {
        this.baseDebptPurchaseAmountTtl = baseDebptPurchaseAmountTtl;
    }

    /**
     * 返回当日赎回债权申请金额
     * @return 当日赎回债权申请金额
     */
    public BigDecimal getRedemDebptApplyAmountDay() {
        return redemDebptApplyAmountDay;
    }

    /**
     * 设置当日赎回债权申请金额
     */
    public void setRedemDebptApplyAmountDay(BigDecimal redemDebptApplyAmountDay) {
        this.redemDebptApplyAmountDay = redemDebptApplyAmountDay;
    }

    /**
     * 返回当日赎回债权申请余额
     * @return 当日赎回债权申请余额
     */
    public BigDecimal getRedemDebptApplyBalanceDay() {
        return redemDebptApplyBalanceDay;
    }

    /**
     * 设置当日赎回债权申请余额
     */
    public void setRedemDebptApplyBalanceDay(BigDecimal redemDebptApplyBalanceDay) {
        this.redemDebptApplyBalanceDay = redemDebptApplyBalanceDay;
    }

    /**
     * 返回当日借款人还款
     * @return 当日借款人还款
     */
    public BigDecimal getBorrowerRepaymentToday() {
        return borrowerRepaymentToday;
    }

    /**
     * 设置当日借款人还款
     */
    public void setBorrowerRepaymentToday(BigDecimal borrowerRepaymentToday) {
        this.borrowerRepaymentToday = borrowerRepaymentToday;
    }

    /**
     * 返回借款人累计还款
     * @return 借款人累计还款
     */
    public BigDecimal getBorrowerRepaymentTotal() {
        return borrowerRepaymentTotal;
    }

    /**
     * 设置借款人累计还款
     */
    public void setBorrowerRepaymentTotal(BigDecimal borrowerRepaymentTotal) {
        this.borrowerRepaymentTotal = borrowerRepaymentTotal;
    }

    /**
     * 返回支付钱包利息
     * @return 支付钱包利息
     */
    public BigDecimal getPayInterestWallet() {
        return payInterestWallet;
    }

    /**
     * 设置支付钱包利息
     */
    public void setPayInterestWallet(BigDecimal payInterestWallet) {
        this.payInterestWallet = payInterestWallet;
    }

    /**
     * 返回钱包存量
     * @return 钱包存量
     */
    public BigDecimal getWalletStock() {
        return walletStock;
    }

    /**
     * 设置钱包存量
     */
    public void setWalletStock(BigDecimal walletStock) {
        this.walletStock = walletStock;
    }

    /**
     * 返回应付钱包利息
     * @return 应付钱包利息
     */
    public BigDecimal getDealInterestWallet() {
        return dealInterestWallet;
    }

    /**
     * 设置应付钱包利息
     */
    public void setDealInterestWallet(BigDecimal dealInterestWallet) {
        this.dealInterestWallet = dealInterestWallet;
    }
}