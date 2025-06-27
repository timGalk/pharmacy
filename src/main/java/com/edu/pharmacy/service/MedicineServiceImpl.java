package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.medicine.MedicineUpdateDTO;
import com.edu.pharmacy.entity.MedicineEntity;
import com.edu.pharmacy.repository.MedicineRepository;
import com.edu.pharmacy.DTO.medicine.MedicineCreateDTO;
import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import com.edu.pharmacy.mapper.MedicineMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
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
    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)) {
            throw new EntityNotFoundException("Medicine with id " + id + " not found");
        }
        medicineRepository.deleteById(id);
        log.info("Medicine with id {} successfully deleted", id);
    }

    @Override
    public MedicineDTO updateMedicine(Long id, MedicineUpdateDTO medicine) {
        MedicineEntity existingMedicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + id + " not found"));

        medicineMapper.updateEntityFromDto(medicine, existingMedicine);

        medicineRepository.save(existingMedicine);
        log.info("Medicine with id {} successfully updated", id);

        return medicineMapper.convert(existingMedicine);
    }



    @Override
    @Transactional
    public MedicineDTO createMedicine(MedicineCreateDTO medicine) {
        MedicineEntity medicineEntity = medicineMapper.convert(medicine);
        medicineRepository.save(medicineEntity);
        return medicineMapper.convert(medicineEntity);

    }
}
