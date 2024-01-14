package com.example.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(schema = "category_and_product", name = "category")
@Where(clause = "is_deleted=false")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToOne
  @JoinColumn(name = "father_id")
  private Category fatherCategory;
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;
}
