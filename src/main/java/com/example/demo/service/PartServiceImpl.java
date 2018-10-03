package com.example.demo.service;

import com.example.demo.entity.Part;
import com.example.demo.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    PartRepository partRepository;
    @Autowired
    DataSource datasource;

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

    @Override
    public void populateDB() throws SQLException {
        final Connection connection = datasource.getConnection();
        try {
            ScriptUtils.executeSqlScript(connection, new EncodedResource(new ClassPathResource("schema.sql"), StandardCharsets.UTF_8));
            ScriptUtils.executeSqlScript(connection, new EncodedResource(new ClassPathResource("data.sql"), StandardCharsets.UTF_8));
        } finally {
            connection.close();
        }
    }
}
