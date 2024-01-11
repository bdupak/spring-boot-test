package com.example.database.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "product")
@Where(clause = "is_deleted=false")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String overview;
  private BigDecimal price;
  private String currency;
  private Integer weight;
  @Column(name = "img_url")
  private String imageUrl;
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "category_fk")
  private Category category;
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;
}
