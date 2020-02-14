package thirdparty.com.fasterxml.jackson;

import java.util.List;

public class UpperObject {
	private String aaa;
	private List<ElementObject> bbb;

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public List<ElementObject> getBbb() {
		return bbb;
	}

	public void setBbb(List<ElementObject> bbb) {
		this.bbb = bbb;
	}
}
