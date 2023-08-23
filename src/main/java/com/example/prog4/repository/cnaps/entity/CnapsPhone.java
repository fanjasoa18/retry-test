package com.example.prog4.repository.cnaps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"cnaps_phone\"")
@EqualsAndHashCode
@ToString
public class CnapsPhone {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private String id;
  private String value;
  @ManyToOne
  @JoinColumn(name = "cnaps_employee_id", referencedColumnName = "id")
  @JsonIgnore
  private CnapsEmployee employee;
}
