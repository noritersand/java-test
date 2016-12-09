package noritersand;

public class MainClass {
	public static void main(String[] args) {
		// 등급별 회원 리스트를 출력
		// 일반,vip,vvip
		//
		// 공통CLASS,
		// 필드 : 이름,성별,전화번호
		// 메서드 : 회원 정보를 출력하는 로직
		//
		// 등급별 회원 CLASS (공통CLASS 상속)추가
		// 필드 : 특정 필드 1개씩 (vvip에는 혜택 class 추가)
		// 메서드 : 오버라이딩을통한 상속 필드 포함 특정필드 추가로 출력하는 로직
		//
		// vvip 혜택 CLASS
		// vvip에만 주어지는 혜택 클래스
		// 필드 : 혜택 내용, 포인트 점수
		//
		// main CLASS
		// 배열에 일반,vip,vvip 임의 회원 2명씩
		// 정의하여 정보를 출력하는 프로그램을
		// 만드세요.
		// 아래와 같이
		// 등급 이름 성별 전화번호 특정필드 혜택
		// vip 홍길동 남 010... 이메일 이마트할인
		
		MemberBase m = new MemberBase();
		m.setGender(Gender.MALE);
		m.setName("홍길동");
		m.setPhoneNumber("010-1234-1234");
		System.out.println(m);
	}
}

enum Gender {
	MALE, FEMALE
}

class VVipMember extends MemberBase {
	
}

class VipMember extends MemberBase {
	// need specific field
}

class GeneralMember extends MemberBase {
	// need specific field
}

class MemberBase {
	private String name;
	private Gender gender;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBase = [\n\tname=");
		builder.append(name);
		builder.append(", \n\tgender=");
		builder.append(gender);
		builder.append(", \n\tphoneNumber=");
		builder.append(phoneNumber);
		builder.append("\n]");
		return builder.toString();
	}
}