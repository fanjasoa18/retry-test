package com.example.prog4.repository.cnaps.entity;


import com.example.prog4.repository.employee.entity.enums.Csp;
import com.example.prog4.repository.employee.entity.enums.Sex;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "\"cnaps_employee\"")
public class CnapsEmployee implements Serializable {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private String id;
  private String cin;
  private String cnaps;
  private String image;
  private String address;
  private String lastName;
  private String firstName;
  private String personalEmail;
  private String professionalEmail;
  private String registrationNumber;
  private String endToEndId;

  private LocalDate birthDate;
  private LocalDate entranceDate;
  private LocalDate departureDate;

  private Integer childrenNumber;

  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST(sex AS varchar)", write = "CAST(? AS sex)")
  private Sex sex;
  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST(csp AS varchar)", write = "CAST(? AS csp)")
  private Csp csp;

  @ManyToMany
  @JoinTable(
    name = "have_position",
    joinColumns = @JoinColumn(name = "cnaps_employee_id"),
    inverseJoinColumns = @JoinColumn(name = "cnaps_position_id")
  )
  private List<CnapsPosition> positions;
  @OneToMany
  @JoinColumn(name = "cnaps_employee_id", referencedColumnName = "id")
  private List<CnapsPhone> phones;
}
