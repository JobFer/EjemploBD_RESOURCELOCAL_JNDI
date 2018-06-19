package dao;

import entities.Categoria;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
//Esto no da error, aunque no lo hace caso (tb admite BEAN)
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class EJBSubordinado {

    @PersistenceUnit(unitName = "EjemploBD_RESOURCELOCAL_JNDIPU")
    private EntityManagerFactory emf;
        
//  Esto tampoco da error, aunque no lo hace caso (excepto NEVER, que sí es atendida)
//    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
//    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
//    @TransactionAttribute(value = TransactionAttributeType.NEVER)    //Tb se puede poner en la clase
    public void subordinado() throws SystemException {
        System.out.println("*************** subordinado ****************");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(new Categoria("miCategoriaSubordinada"));
//        em.persist(new Categoria(1));
        et.commit();
    }
}
