package com.edu.pharmacy.service;

import com.edu.pharmacy.entity.MedicineEntity;
import com.edu.pharmacy.repository.MedicineRepository;
import com.edu.pharmacy.DTO.medicine.MedicineCreateDTO;
import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import com.edu.pharmacy.mapper.MedicineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    public final MedicineMapper medicineMapper;

    @Override
    @Transactional
    public MedicineDTO getMedicine(Long id) {

        MedicineEntity medicineEntity = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine wasn't found"));
        return medicineMapper.convert(medicineEntity);

    }

    @Override
    @Transactional
    public List<MedicineDTO> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(medicineMapper::convert)
                .toList();
    }

    @Override
    @Transactional
    public MedicineDTO createMedicine(MedicineCreateDTO medicine) {
        MedicineEntity medicineEntity = medicineMapper.convert(medicine);
        medicineRepository.save(medicineEntity);
        return medicineMapper.convert(medicineEntity);

    }
}
