package eu.gebes;

/**
 * The Gebes Number is named after my nickname
 * I found this out on my own and did not find
 * any name for this.
 * <p>
 * Starting from two x = x * 3 - 2
 * 2, 4, 10, 28, 82...
 */
public class ColorPyramidCalculator {


    public static byte R = 1;
    public static byte G = 2;
    public static byte B = 3;


    /**
     * Bytes are the fastest with the calculation
     */
    private static byte[] toByteArray(String input) {
        byte[] bytes = new byte[input.length()];

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'R') bytes[i] = 1;
            if (c == 'G') bytes[i] = 2;
            if (c == 'B') bytes[i] = 3;
        }
        return bytes;
    }

    private static char byteToChar(byte b) {
        switch (b) {
            case 1 -> {
                return 'R';
            }
            case 2 -> {
                return 'G';
            }
            case 3 -> {
                return 'B';
            }
            default -> {
                throw new RuntimeException("This should not happen. Invalid code");
            }
        }
    }

    /**
     * @param colorOne
     * @param colorTwo
     * @return the char which is left over. If both are the same, it will return the same.
     */
    private static byte mixColors(byte colorOne, byte colorTwo) {
        if (colorOne == colorTwo) return colorOne;
        return (byte) (6 - colorOne - colorTwo); // 6 is the sum of R, G, B
    }

    private static byte[] mixRow(byte[] row) {
        byte[] newRow = new byte[row.length - 1];

        for (int i = 0; i < newRow.length; i++) {
            newRow[i] = mixColors(row[i], row[i + 1]);
        }

        return newRow;
    }

    private static byte[] mixRow(byte[] row, int gebesNumber) {
        byte[] newRow = new byte[row.length - (gebesNumber - 1)];

        for (int i = 0; i < newRow.length; i++) {
            newRow[i] = mixColors(row[i], row[i + (gebesNumber - 1)]);
        }

        return newRow;
    }

    /**
     * the number which allows as to so much.
     * Returns the closest number [2, 4, 10, 28,...] as long as it is below the number
     */
    private static int closestGebesNumber(int number) {
        int target = 2;

        while (true) {
            int newTarget = target * 3 - 2;

            if (newTarget > number)
                break;

            target = newTarget;
        }
        return target;
    }

    String input;

    public ColorPyramidCalculator(String input) {
        this.input = input;
        if (input.length() < 2) throw new RuntimeException("Input length needs to be at least 2");
    }


    public static char calculateUnoptimized(String input) {
        // This mixes every row until there is only
        // one char left - this function is to proof
        // the other functions.

        byte[] bytes = toByteArray(input);

        while (bytes.length != 1) {
            bytes = mixRow(bytes);
        }

        return byteToChar(bytes[0]);
    }

    public static char calculateSimpleOptimization(String input) {
        // I found out that every color string which is based on the formula x*3-2
        // can be mixed instantly, by mixing the first and the last one
        // 2, 4, 10, 28, 82...

        // so we are going to shorten it until it has a length which is in
        // a pre calculated list. Then we instantly shorten it.

        byte[] bytes = toByteArray(input);

        long target = closestGebesNumber(bytes.length);

        // useful for debugging
        // System.out.println(target + " - " + bytes.length + " = " + (bytes.length - target) + " rows to calculated");

        // now the shorten part

        while (bytes.length != target) {
            bytes = mixRow(bytes);
        }

        // now the instant mix part of the first and last color
        // It just skips colors which do not have influence
        // on the last one. Try it on your own with RGBR. You'll
        // see that no matter what G and B are, it does not affect
        // the last char

        return byteToChar(mixColors(bytes[0], bytes[bytes.length - 1]));
    }

    public static char calculateOptimized(String input) {

        // The simple calculation method is only fast,
        // when N is a Gebes Number. Otherwhise it has
        // to crush very large amount of numbers if we
        // are in the billions. So we just use the
        // Gebes number in way, were we skip
        // many more numbers. It is like the
        // slow calculation method but with most of
        // the numbers skipped.


        byte[] bytes = toByteArray(input);

        int current = closestGebesNumber(bytes.length);

        while (bytes.length > 4) {
            bytes = mixRow(bytes, current);
            current = closestGebesNumber(bytes.length);
        }

        while (bytes.length != 1)
            bytes = mixRow(bytes);

        return byteToChar(bytes[0]);
    }

}
