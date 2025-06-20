package com.edu.pharmacy.mapper;

import com.edu.pharmacy.entity.MedicineEntity;
import com.edu.pharmacy.DTO.medicine.MedicineCreateDTO;
import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {
    public MedicineEntity convert(MedicineDTO medicine) {
        return new MedicineEntity(medicine.getId(),
                medicine.getName(), medicine.getDescription(),
                medicine.getPrice(), medicine.getStockQuantity(), medicine.getExpirationDate()
        , medicine.getImage());
    }

    public MedicineDTO convert(MedicineEntity medicineEntity) {
        return new MedicineDTO(
                medicineEntity.getId(),
                medicineEntity.getName(),
                medicineEntity.getDescription(),
                medicineEntity.getPrice(),
                medicineEntity.getStockQuantity(),
                medicineEntity.getExpirationDate(),
                medicineEntity.getImage()


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
