package com.dwl.rep.pojo;

import java.io.Serializable;

public class ReportDetail implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private String id;

    private String repId;

    private String headerId;

    private String secHeaderId;

    private String isSum;

    private String type;
    
    /**
     * 顺序
     */
    private Integer orders;

    
    private HeaderInfo headerInfo;
    
    private HeaderInfo secHeaderInfo;
    
    
    public HeaderInfo getSecHeaderInfo() {
		return secHeaderInfo;
	}

	public void setSecHeaderInfo(HeaderInfo secHeaderInfo) {
		this.secHeaderInfo = secHeaderInfo;
	}

	public HeaderInfo getHeaderInfo() {
		return headerInfo;
	}

	public void setHeaderInfo(HeaderInfo headerInfo) {
		this.headerInfo = headerInfo;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getSecHeaderId() {
        return secHeaderId;
    }

    public void setSecHeaderId(String secHeaderId) {
        this.secHeaderId = secHeaderId;
    }

    public String getIsSum() {
        return isSum;
    }

    public void setIsSum(String isSum) {
        this.isSum = isSum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
    
    
}