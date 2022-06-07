package bcp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud")
public class Config {
    private String httpbin = "http://httpbin.org:80";
    private String endpoint;
    private String preauthreq;

    public String getPreauthreq() {
        return preauthreq;
    }

    public void setPreauthreq(String preauthreq) {
        this.preauthreq = preauthreq;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
