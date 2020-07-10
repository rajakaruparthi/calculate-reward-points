package com.charter.assessment.serviceiImpl;

import com.charter.assessment.request.PurchaseDetails;
import com.charter.assessment.service.CalculatePoint;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class CalculatePointsImpl implements CalculatePoint {

    @Override
    public HashMap<String, Float> calculatePoints() {

        HashMap<String, List<PurchaseDetails>> customerDetailsMap = constructDetails();

        HashMap<String, Float> finalMap = new HashMap<>();

        LocalDate currentDate = LocalDate.now();

        LocalDate threeMonthsAgo = LocalDate.now().plusMonths(-3);

        for (String key: customerDetailsMap.keySet()) {
            List<PurchaseDetails> purchaseDetailsList = customerDetailsMap.get(key);

            AtomicReference<Float> sum = new AtomicReference<>((float) 0);

            purchaseDetailsList.stream().forEach( each -> {
                if(each.getDate().isBefore(threeMonthsAgo)){
                    float points = calPoints(each.getAmount());
                    sum.set(sum.get() + points);
                }
            });
            finalMap.put(key, sum.get());
            sum.set((float) 0);
        }

        return (finalMap);
    }

    private float calPoints(float amount){
        if(amount < 50) {
            return 0;
        } else if (amount > 50 && amount < 100) {
            amount = amount -50;
            return amount;
        } else {
            amount = amount - 100;
            return (amount*2 + 50);
        }
    }

    private HashMap<String, List<PurchaseDetails>> constructDetails() {

        HashMap<String, List<PurchaseDetails>> customerPurchaseDetails = new HashMap<>();

        List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
        PurchaseDetails p = null;

        for (int i = 1; i< 200; i++) {
            p = new PurchaseDetails();
            p.setAmount(10 + i*5);
            p.setDate(LocalDate.of(  2020, i%7+1, i%9+1));
            purchaseDetailsList.add(p);
        }

        customerPurchaseDetails.put("cust1", purchaseDetailsList);

        purchaseDetailsList = new ArrayList<>();
        for (int i = 1; i< 200; i++) {
            p = new PurchaseDetails();
            p.setAmount(10 + i*10);
            p.setDate(LocalDate.of(2020, i%4+1,i%27+1));
            purchaseDetailsList.add(p);
        }

        customerPurchaseDetails.put("cust2", purchaseDetailsList);

        purchaseDetailsList = new ArrayList<>();
        for (int i = 1; i< 200; i++) {
            p = new PurchaseDetails();
            p.setAmount(10 + i*15);
            p.setDate(LocalDate.of(2020, i%5+1,i%28+1));
            purchaseDetailsList.add(p);
        }

        customerPurchaseDetails.put("cust3", purchaseDetailsList);

        purchaseDetailsList = new ArrayList<>();
        for (int i = 1; i< 200; i++) {
            p = new PurchaseDetails();
            p.setAmount(10 + i*20);
            p.setDate(LocalDate.of(2020, i%4+1,i%29+1));
            purchaseDetailsList.add(p);
        }

        customerPurchaseDetails.put("cust4", purchaseDetailsList);

        return customerPurchaseDetails;
    }
}
