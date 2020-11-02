package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

@Component
public class TransactionManager implements PlatformTransactionManager {

  @Autowired
  PriceCategoryRepository dao1;

  @Autowired
  UserRepository dao2;

  @Autowired
  MovieRepository dao3;

  @Autowired
  RentalRepository dao4;

  @PostConstruct
  private void resetData() {
    // The order in which the daos are invoked is important as they depend on each other, otherweise a Repository array
    // could have been defined.
    for (Repository<?, ?> dao : new Repository[]{dao1, dao2, dao3, dao4}) {
      try {
        Method method = dao.getClass().getDeclaredMethod("initData");
        method.setAccessible(true);
        method.invoke(dao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {

    return new TransactionStatus() {

      @Override
      public Object createSavepoint() throws TransactionException {
        throw new UnsupportedOperationException();
      }

      @Override
      public void rollbackToSavepoint(Object savepoint) throws TransactionException {
        throw new UnsupportedOperationException();
      }

      @Override
      public void releaseSavepoint(Object savepoint) throws TransactionException {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean isNewTransaction() {
        return false;
      }

      @Override
      public boolean hasSavepoint() {
        return false;
      }

      @Override
      public void setRollbackOnly() {
      }

      @Override
      public boolean isRollbackOnly() {
        return false;
      }

      @Override
      public void flush() {
      }

      @Override
      public boolean isCompleted() {
        return false;
      }
    };
  }

  @Override
  public void commit(TransactionStatus status) throws TransactionException {
  }

  @Override
  public void rollback(TransactionStatus status) throws TransactionException {
    resetData();
  }

}
