package com.example.demo.service;

import com.example.demo.entity.Part;
import com.example.demo.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    PartRepository partRepository;

    @Override
    public Iterable<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        partRepository.deleteById(integer);
    }

    @Override
    public <S extends Part> S save(S s) {
        return partRepository.save(s);
    }

    @Override
    public Optional<Part> findById(Integer integer) {
        return partRepository.findById(integer);
    }

    @Override
    public int compQty() {
        int qty = 0;
        boolean found = false;
        for (Part p : findAll()) {
            if (p.isImportant()) {
                qty = p.getQty();
                found = true;
                break;
            }
        }
        if (found) {
            for (Part p : findAll()) {
                if (qty > p.getQty() && p.isImportant()) {
                    qty = p.getQty();
                }
            }
            return qty;
        } else return 0;
    }
}
