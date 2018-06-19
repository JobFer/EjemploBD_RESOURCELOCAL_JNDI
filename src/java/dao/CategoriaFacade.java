package dao;

import entities.Categoria;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.SystemException;

//Esto no da error, aunque no lo hace caso (tb admite BEAN)
//@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @EJB
    private EJBSubordinado eJBSubordinado;

    @PersistenceUnit(unitName = "EjemploBD_RESOURCELOCAL_JNDIPU")
    private EntityManagerFactory emf;

//    //Tb se permite esto
//    @PersistenceContext(unitName = "EjemploBD_RESOURCELOCAL_JNDIPU")
//    private EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void crearCategoria() throws SystemException{
        System.out.println("*************** principal ****************");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction et = em.getTransaction();
        et.begin();            
        em.persist(new Categoria("miCategoria"));
//        em.persist(new Categoria(1)); //Esto provoca una excepcion
        eJBSubordinado.subordinado();
        et.commit();
    }
    
    public CategoriaFacade() {
        super(Categoria.class);
    }
    
}
