package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Optional<ProductImage> findByFoodId(Long foodId);
    Optional<ProductImage> findByMedicineId(Long medicineId);
    Optional<ProductImage> findByGarmentId(Long garmentId);
}