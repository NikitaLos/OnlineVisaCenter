package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_document")
public class ClientDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "path_on_server")
    private String pathOnServer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDocument that = (ClientDocument) o;

        return pathOnServer != null ? pathOnServer.equals(that.pathOnServer) : that.pathOnServer == null;
    }

    @Override
    public int hashCode() {
        return 31 *  (pathOnServer != null ? pathOnServer.hashCode() : 0);
    }
}
