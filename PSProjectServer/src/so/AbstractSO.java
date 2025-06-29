/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author Aleksa
 */
public abstract class AbstractSO {
     protected final Repository repository;

    public AbstractSO() {
        this.repository = new DbRepositoryGeneric();
    }
     
     public void execute(Object param, String key) throws Exception {
        try {
            precondition(param);
            startTransaction();
            executeOperation(param, key);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija!");
        } catch (Exception exception) {
            System.out.println("Greska kod cuvanja!");
    exception.printStackTrace(); 
    rollbackTransaction();
    throw exception; 
        }
    }
     
     
     protected abstract void precondition(Object param) throws Exception;

    protected abstract void executeOperation(Object param, String key) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    protected void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    protected void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }
    
    protected void cancelConnection() throws Exception {
    
    ((DbRepository) repository).disconnect();
    }
}
