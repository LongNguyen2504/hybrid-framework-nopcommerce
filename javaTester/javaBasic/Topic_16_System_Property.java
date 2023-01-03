package javaBasic;

public class Topic_16_System_Property {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    //Window/MAC/Linux
    public static final String OS_NAME = System.getProperty("os.name");

    public static void main(String[] args) {
        System.out.println(PROJECT_PATH);
        System.out.println(OS_NAME);
    }


}
