package com.wipro.icms.entity;

public class ClaimUpdate {
    private String updateId;
    private String claimId;
    private String date;
    private String notes;

    public ClaimUpdate(String updateId, String claimId, String date, String notes) {
        this.updateId = updateId;
        this.claimId = claimId;
        this.date = date;
        this.notes = notes;
    }

    public String getUpdateId() {
        return updateId;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
