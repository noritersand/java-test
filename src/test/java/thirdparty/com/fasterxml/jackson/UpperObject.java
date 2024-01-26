package thirdparty.com.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class UpperObject {
    private String aaa;
    private List<ElementObject> bbb;

    public String getAaa() {
        return this.aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public List<ElementObject> getBbb() {
        return this.bbb;
    }

    public void setBbb(List<ElementObject> bbb) {
        this.bbb = bbb;
    }
}
