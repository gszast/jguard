package com.jeweleryguard.service;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;

public interface JewelryService {
    public Jewelry findJewelryByUser(User user);
    public void saveJewelry(Jewelry jewelry);
}
