package com.revature.cra.util.Interfaces;

import com.revature.cra.util.exceptions.InvalidInputException;

public interface Serviceable<O> {
    O[] findAll();
    O create(O newObject) throws InvalidInputException;
    O findById(int number);
}
