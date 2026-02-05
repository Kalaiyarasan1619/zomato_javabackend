package com.zomato.earnings.demo.controller;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zomato.earnings.demo.model.DailyEarnings;
import com.zomato.earnings.demo.repository.DailyEarningRepository;

@RestController
@RequestMapping("/daily-earnings")
public class DailyEarningController {

    private final DailyEarningRepository repository;

    private static final String[] MONTH_NAMES = {
        "January","February","March","April","May","June",
        "July","August","September","October","November","December"
    };

    public DailyEarningController(DailyEarningRepository repository) {
        this.repository = repository;
    }

    /* =========================
       POST – INSERT DAILY EARNING
       ========================= */
    @PostMapping
    public ResponseEntity<?> createDailyEarning(@RequestBody Map<String, Object> body) {
        try {
            String isoDate = (String) body.get("isoDate");

            if (isoDate == null || !isoDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Valid isoDate required (YYYY-MM-DD)"));
            }

            LocalDate date = LocalDate.parse(isoDate);

            DailyEarnings row = new DailyEarnings();
            row.setEntryDate(date);
            row.setEntryMonth(MONTH_NAMES[date.getMonthValue() - 1]);
            row.setEntryYear((short) date.getYear());

            row.setPetrolCost(getDecimal(body, "petrolCost"));
            row.setCashOnDelivery(getDecimal(body, "cashOnDelivery"));
            row.setCashDeposit(getDecimal(body, "cashDeposit"));
            row.setOtherCash(getDecimal(body, "otherCash"));
            row.setDailyWithDrawAmount(getDecimal(body, "dailyWithDrawAmount"));
            row.setTotalEarnings(getDecimal(body, "totalEarnings"));
            row.setOtherType((String) body.get("otherType"));


            DailyEarnings saved = repository.save(row);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Insert failed"));
        }
    }

    /* =========================
       GET – DISTINCT OTHER TYPES
       ========================= */
    @GetMapping("/other-types")
    public ResponseEntity<?> getOtherTypes() {
        try {
            List<String> result = repository.findDistinctOtherTypes();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "DB error"));
        }
    }

    /* =========================
       HELPER METHOD
       ========================= */
    private BigDecimal getDecimal(Map<String, Object> body, String key) {
        Object value = body.get(key);
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value.toString());
    }
}

