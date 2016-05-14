/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Gecmis;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Test;
import entity.Kullanici;
import entity.controller.exceptions.NonexistentEntityException;
import entity.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author celal
 */
public class GecmisJpaController implements Serializable {

    public void create(Gecmis gecmis) throws RollbackFailureException, Exception {
    }
}
