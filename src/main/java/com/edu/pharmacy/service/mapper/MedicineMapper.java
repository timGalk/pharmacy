package com.edu.pharmacy.service.mapper;

import com.edu.pharmacy.repo.MedicineEntity;
import com.edu.pharmacy.service.DTO.MedicineCreateDTO;
import com.edu.pharmacy.service.DTO.MedicineDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {
    public MedicineEntity convert(MedicineDTO medicine) {
        return new MedicineEntity(medicine.getId(),
                medicine.getName(), medicine.getDescription(),
                medicine.getPrice(), medicine.getStockQuantity(), medicine.getExpirationDate());
    }

    public MedicineDTO convert(MedicineEntity medicineEntity) {
        return new MedicineDTO(
                medicineEntity.getId(),
                medicineEntity.getName(),
                medicineEntity.getDescription(),
                medicineEntity.getPrice(),
                medicineEntity.getStockQuantity(),
                medicineEntity.getExpirationDate()

        );
    }

    public MedicineEntity convert(MedicineCreateDTO medicine) {
        return MedicineEntity.builder()
                .name(medicine.getName())
                .description(medicine.getDescription())
                .price(medicine.getPrice())
                .stockQuantity(medicine.getQuantity())
                .expirationDate(medicine.getExpirationDate())
                .build();
    }

}
