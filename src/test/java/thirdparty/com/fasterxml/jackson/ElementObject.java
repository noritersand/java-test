package thirdparty.com.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
class ElementObject {
    private String ccc;

    public String getCcc() {
        return this.ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
}
