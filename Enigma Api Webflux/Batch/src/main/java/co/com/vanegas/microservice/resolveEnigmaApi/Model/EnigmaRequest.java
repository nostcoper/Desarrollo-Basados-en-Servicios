package co.com.vanegas.microservice.resolveEnigmaApi.Model;

import java.util.List;

public class EnigmaRequest {
    private List<EnigmaData> data;

    // Getters y setters

    public List<EnigmaData> getData() {
        return data;
    }

    public void setData(List<EnigmaData> data) {
        this.data = data;
    }

    public static class EnigmaData {
        private Header header;
        private String enigma;

        // Getters y setters

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public String getEnigma() {
            return enigma;
        }

        public void setEnigma(String enigma) {
            this.enigma = enigma;
        }
    }

    public static class Header {
        private String id;
        private String type;

        // Getters y setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
