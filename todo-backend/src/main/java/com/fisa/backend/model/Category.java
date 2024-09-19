package com.fisa.backend.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity{
    private String name;
}
