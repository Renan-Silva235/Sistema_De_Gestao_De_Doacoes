package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.repositories.FoodRepository;
import com.colaborativos_gestao_sistema_api.repositories.GarmentRepository;
import com.colaborativos_gestao_sistema_api.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    public Map<String, Long> getQuickStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("total_foods", foodRepository.countByStatus(Status.ATIVO));
        stats.put("total_garments", garmentRepository.countByStatus(Status.ATIVO));
        stats.put("total_medicines", medicineRepository.countByStatus(Status.ATIVO));

        return stats;
    }

    public Map<String, List<?>> getExpiringProducts(int days) {
        LocalDate limitDate = LocalDate.now().plusDays(days);
        Map<String, List<?>> expiring = new HashMap<>();

        expiring.put("foods", foodRepository.findByValidadeBeforeAndStatus(limitDate, Status.ATIVO));
        expiring.put("medicines", medicineRepository.findByValidadeBeforeAndStatus(limitDate, Status.ATIVO));

        return expiring;
    }
}
