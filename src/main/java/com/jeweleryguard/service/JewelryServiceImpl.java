package com.jeweleryguard.service;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import com.jeweleryguard.repository.JewelryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jewelryService")
public class JewelryServiceImpl implements JewelryService{

    @Autowired
    private JewelryRepository jewelryRepository;

    @Override
    public Jewelry findJewelryByUser(User user) {
        return jewelryRepository.findByUser(user);
    }

    @Override
    public void saveJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
    }

}
