package ch.fhnw.eaf.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue
  private int id;

  public Order() {
  }

  public int getId() {
    return id;
  }
}
