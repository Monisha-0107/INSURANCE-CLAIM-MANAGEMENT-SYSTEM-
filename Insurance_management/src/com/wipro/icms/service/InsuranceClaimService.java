package com.wipro.icms.service;

import java.util.ArrayList;
import com.wipro.icms.entity.*;
import com.wipro.icms.util.*;

public class InsuranceClaimService {

    private ArrayList<PolicyHolder> holders;
    private ArrayList<Claim> claims;
    private ArrayList<ClaimUpdate> updates;

    public InsuranceClaimService(ArrayList<PolicyHolder> holders,
                                 ArrayList<Claim> claims,
                                 ArrayList<ClaimUpdate> updates) {
        this.holders = holders;
        this.claims = claims;
        this.updates = updates;
    }

    public void fileClaim(Claim claim) {
        claims.add(claim);
    }

    public void addClaimUpdate(ClaimUpdate update) throws ClaimNotFoundException {
        findClaim(update.getClaimId());
        updates.add(update);
    }

    public void updateClaimStatus(String claimId, String status)
            throws ClaimNotFoundException, InvalidClaimOperationException {

        Claim claim = findClaim(claimId);

        if ("CLOSED".equalsIgnoreCase(claim.getStatus())) {
            throw new InvalidClaimOperationException("Cannot modify a closed claim");
        }

        claim.setStatus(status);
    }

    public double computeSettlementAmount(String claimId) throws ClaimNotFoundException {
        Claim claim = findClaim(claimId);
        return claim.getClaimAmount() * 0.80; // 80% settlement
    }

    public String generateClaimSummary(String claimId) throws ClaimNotFoundException {
        Claim claim = findClaim(claimId);

        StringBuilder sb = new StringBuilder();
        sb.append("Claim ID: ").append(claim.getClaimId()).append("\n");
        sb.append("Status: ").append(claim.getStatus()).append("\n");
        sb.append("Description: ").append(claim.getDescription()).append("\n");
        sb.append("Updates:\n");

        for (ClaimUpdate u : updates) {
            if (u.getClaimId().equals(claimId)) {
                sb.append(" - ").append(u.getNotes()).append("\n");
            }
        }
        return sb.toString();
    }

    private Claim findClaim(String claimId) throws ClaimNotFoundException {
        for (Claim c : claims) {
            if (c.getClaimId().equals(claimId)) {
                return c;
            }
        }
        throw new ClaimNotFoundException("Claim not found: " + claimId);
    }
}
