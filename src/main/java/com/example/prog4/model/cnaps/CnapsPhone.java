package com.example.prog4.model.cnaps;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CnapsPhone implements Serializable {
  private String id;
  private String value;
  private String countryCode;
}
