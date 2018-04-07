package com.vironit.onlinevisacenter.entity;

public class ClientDocument implements Identified<Integer> {

    private Integer id;
    private Document document;
    private String pathOnServer;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
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

        if (document != null ? !document.equals(that.document) : that.document != null) return false;
        return pathOnServer != null ? pathOnServer.equals(that.pathOnServer) : that.pathOnServer == null;
    }

    @Override
    public int hashCode() {
        int result = document != null ? document.hashCode() : 0;
        result = 31 * result + (pathOnServer != null ? pathOnServer.hashCode() : 0);
        return result;
    }
}
