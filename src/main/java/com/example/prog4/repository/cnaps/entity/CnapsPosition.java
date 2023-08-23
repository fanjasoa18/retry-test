package com.example.prog4.repository.cnaps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"cnaps_position\"")
@EqualsAndHashCode
@ToString
public class CnapsPosition {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private String id;
  private String name;
}
