public class Main {
    public static void main(String[] args) {
        int arabic = 0;
        System.out.println("int: " + arabic);
        Rome myConverter = new Rome();
        // try {
        String romeNum = myConverter.getRomeNum(arabic);
        System.out.println("Roman: " + romeNum);
//        } catch (Exception e) {
//            System.err.println(arabic + " is not in range (1,3999]");
//        }
    }

}
