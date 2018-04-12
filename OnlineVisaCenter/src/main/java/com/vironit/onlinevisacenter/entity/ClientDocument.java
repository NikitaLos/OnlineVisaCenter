package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_document", schema = "visa_center")
public class ClientDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(name = "path_on_server")
    private String pathOnServer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        documentType.addClientDocument(this);
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

        if (documentType != null ? !documentType.equals(that.documentType) : that.documentType != null) return false;
        return pathOnServer != null ? pathOnServer.equals(that.pathOnServer) : that.pathOnServer == null;
    }

    @Override
    public int hashCode() {
        int result = documentType != null ? documentType.hashCode() : 0;
        result = 31 * result + (pathOnServer != null ? pathOnServer.hashCode() : 0);
        return result;
    }
}
