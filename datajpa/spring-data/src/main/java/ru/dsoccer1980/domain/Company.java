package ru.dsoccer1980.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Company {

  @Id
  @GeneratedValue
  private long id;

  private String phone;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride( name = "phone", column = @Column(name = "contact_phone"))
  })
  private Address address;


  public Company(String phone, Address address) {
    this.phone = phone;
    this.address = address;
  }

  public Company() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", phone='" + phone + '\'' +
        ", address=" + address +
        '}';
  }
}
