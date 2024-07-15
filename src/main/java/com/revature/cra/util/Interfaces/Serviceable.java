package com.revature.cra.util.Interfaces;

import com.revature.cra.util.exceptions.InvalidInputException;

import java.util.List;

public interface Serviceable<O> {
    List<O> findAll();
    O create(O newObject) throws InvalidInputException;
    O findById(int number);
}
