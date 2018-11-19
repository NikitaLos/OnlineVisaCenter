package com.vironit.onlinevisacenter.dto.documenttype;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DocumentTypeDTO {

    private Integer id;

    @NotNull(message = "{document_type.name.null}")
    @Size(min = 2, message = "{document_type.name.size}")
    private String name;
}
