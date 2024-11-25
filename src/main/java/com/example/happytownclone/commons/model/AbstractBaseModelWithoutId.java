package com.example.happytownclone.commons.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AbstractBaseModelWithoutId implements Serializable{
    @Serial
    private static final long serialVersionUID = 1020883403L;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
