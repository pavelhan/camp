package api.clients.web.urls;

public enum CIDEndpoints {
    login("/api/v1/login"),
    dashboard("/api/metagenid/v3/dashboard");

    private String resource;

    CIDEndpoints(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
