package noritersand;

enum SystemDivision {
	BACK_OFFICE, 
	FRONT_OFFICE("front office system");
	
	private SystemDivision() {} // 기본 생성자
	
	private String optionalField;
	
	private SystemDivision(final String option) {
		this.optionalField = option;
	}
	
	public String getOptionalField() {
		return this.optionalField;
	}
}
 
public class EnumTest {
    public static void main(String[] args) {
    	SystemDivision var1 = SystemDivision.BACK_OFFICE;
    	SystemDivision var2 = SystemDivision.FRONT_OFFICE;
    	
    	System.out.println(var1); // BACK_OFFICE
    	System.out.println(var2); // FRONT_OFFICE
    	System.out.println(var2.name()); // FRONT_OFFICE
    	System.out.println(var2.getOptionalField()); // front office system
    }
}
