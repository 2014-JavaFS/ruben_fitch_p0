package com.revature.cra.util.Interfaces;

public interface Crudable<O> extends Serviceable<O> {
    boolean update(O updatedObject);
    boolean delete(O removedObject);
}
