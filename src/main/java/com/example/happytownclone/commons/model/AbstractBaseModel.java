package com.example.happytownclone.commons.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class AbstractBaseModel<I extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1020883403L;

    protected I id;
    protected String createdBy;
    protected LocalDateTime createdDate;
    protected String lastModifedBy;
    protected LocalDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AbstractBaseModel<?> that)) {
            return false;
        }

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}