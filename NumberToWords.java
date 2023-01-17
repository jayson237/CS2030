public class NumberToWords {
    public static void main(String[] args) {
        numberToWords(100);
    }
    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
            return;
        }
        int lastDigit;
        int reversed = reverse(number);
        int counter = getDigitCount(number);
        while (counter != 0) {
            lastDigit = reversed % 10;
            switch(lastDigit) {
            case 0:
                System.out.println("Zero");
                break;
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            case 4:
                System.out.println("Four");
                break;
            case 5:
                System.out.println("Five");
                break;
            case 6:
                System.out.println("Six");
                break;
            case 7:
                System.out.println("Seven");
                break;
            case 8:
                System.out.println("Eight");
                break;
            case 9:
                System.out.println("Nine");
                break;
            default:
                System.out.println("Zero");
                break;

            }
            reversed /= 10;
            counter--;
            
        }
    }
    
    public static int reverse(int number) {
        int original = number;
        int reverse = 0;
        int lastDigit;
        while(number != 0) {
            lastDigit = number % 10;
            reverse = reverse * 10 + lastDigit;
            number /= 10;
        }
        if (number >= 0) {
            return reverse;
        }
        return -1;
    }
    
    public static int getDigitCount(int number) {
        if (number < 0) {
            return -1;
        }
        if (number == 0) {
            return 1;
        }
        int counter = 0;
        while (number != 0) {
            counter++;
            number /= 10;
        }
        return counter;
    }
}