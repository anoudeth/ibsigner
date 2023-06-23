package org.ib.signer;

public class ReqParam {
    private String merchantId;
    private String mobileNo;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    // generate output to json string for selected properties without String.format
    @Override
    public String toString() {
        return "{\"merchantId\":\"" + merchantId + "\", \"mobileNo\":\"" + mobileNo + "\" }";
    }
}
