package com.vironit.onlinevisacenter.dto;


import javax.validation.constraints.NotNull;

public class ClientDocumentDTO {

    private Integer id;

    @NotNull
    private String pathOnServer;

    private DocumentTypeDTO documentType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathOnServer() {
        return pathOnServer;
    }

    public void setPathOnServer(String pathOnServer) {
        this.pathOnServer = pathOnServer;
    }

    public DocumentTypeDTO getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeDTO documentType) {
        this.documentType = documentType;
    }
}
