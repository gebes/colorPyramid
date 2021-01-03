package eu.gebes;

public class Main {

    public static void main(String[] args) {

        /*

        RGB
        BR
        G

         */

        System.out.println(ColorPyramidCalculator.calculateUnoptimized("RGB")); // Prints out G



        /*

        RGBG
        BRR
        GR
        B

         */

        System.out.println(ColorPyramidCalculator.calculateUnoptimized("RGBG")); // Prints out B

        /*

        Now a hard test

         */

        int base = 2;
        for (int i = 0; i < 15; i++) {
            System.out.println(base);
            var input = randomInput(base);
            if(base < 100000) { // We do not want to get a OutOfMemoryException
                System.out.println(" SLOW: " + ColorPyramidCalculator.calculateUnoptimized(input));
                System.out.println(" FAST: " + ColorPyramidCalculator.calculateSimpleOptimization(input));
            }
            System.out.println(" THE FASTEST: " +  ColorPyramidCalculator.calculateOptimized(input));
            System.out.println();
            base = base * 3 - 2;
        }

    }

    private static String randomInput(int length) {

        String chars = "RGB";
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < length; i++) {
            input.append(chars.charAt(getRandom(0, 2)));
        }
        return input.toString();

    }

    public static int getRandom(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

}
