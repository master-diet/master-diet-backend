package pl.agh.edu.master_diet.util;

public class CommonFormatters {

    public static float roundFloatNumber(float number) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }

    public static float roundFloatNumber(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }
}
