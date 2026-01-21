package com.wipro.icms.main;
import java.util.ArrayList; 

import com.wipro.icms.entity.*; 
import com.wipro.icms.service.InsuranceClaimService; 
 
public class Main { 
    public static void main(String[] args) { 
 
        ArrayList<PolicyHolder> holders = new ArrayList<>(); 
        holders.add(new PolicyHolder("H001", "Sanjay Kumar", "Vehicle", 200000)); 
 
        ArrayList<Claim> claims = new ArrayList<>(); 
        ArrayList<ClaimUpdate> updates = new ArrayList<>(); 
 
        InsuranceClaimService service = new InsuranceClaimService(holders, claims, updates); 
 
        try { 
            // File a new claim 
            Claim claim1 = new Claim("CL001", "H001", "2025-06-20", 
                    "Accidental damage to front bumper", 
                    35000, 
                    "FILED"); 
            service.fileClaim(claim1); 
 
            // Add updates 
            service.addClaimUpdate(new ClaimUpdate("UP01", "CL001", "2025-06-21" ,"assigned to inspect damage.")); 
            service.updateClaimStatus("CL001", "UNDER REVIEW"); 
            service.addClaimUpdate(new ClaimUpdate("UP02", "CL001", "2025-06-22", "Inspection completed, reports submitted.")); 
            service.updateClaimStatus("CL001", "APPROVED"); 
 
            // Compute settlement 
            double settlement = service.computeSettlementAmount("CL001"); 
            System.out.println("Settlement Amount: " + settlement); 
 
            // Summary 
            System.out.println("\n--- Claim Summary ---"); 
            System.out.println(service.generateClaimSummary("CL001")); 
 
        } catch (Exception ex) { 
            System.out.println(ex); 
        } 
    } 
} 