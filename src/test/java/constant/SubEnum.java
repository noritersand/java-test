package constant;

public enum SubEnum implements SuperEnum {
    DUMMY {
        @Override
        public String overrideMe() {
            return "5678";
        }
    }
}
