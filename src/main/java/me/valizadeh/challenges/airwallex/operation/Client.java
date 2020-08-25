package me.valizadeh.challenges.airwallex.operation;

public class Client {

    public static void main(String[] args) {
        Memory memory = new Memory();

        System.out.println("*************firstEquation1");
        firstEquation1(memory);
        System.out.println("*************firstEquation2");
        firstEquation2(memory);
        System.out.println("*************firstEquation3");
        firstEquation3(memory);
        System.out.println("*************firstEquation4");
        firstEquation4(memory);
        System.out.println("*************firstEquation5");
        firstEquation5(memory);
        System.out.println("*************firstEquation6");
        firstEquation6(memory);
        System.out.println("*************firstEquation7");
        firstEquation7(memory);
        System.out.println("*************firstEquation8");
        firstEquation8(memory);

        /*
        Thread t1 = new Thread(()-> {
        });

        Thread t2 = new Thread(()-> {
            firstEquation1(memory);
        });

        t1.start();
        t2.start();*/


    }

    private static void firstEquation1(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();
        System.out.println(calculator.execute("5 2"));
    }

    private static void firstEquation2(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("2 sqrt"));
        System.out.println(calculator.execute("clear 9 sqrt"));
    }

    private static void firstEquation3(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("5 2 -"));
        System.out.println(calculator.execute("3 -"));
        System.out.println(calculator.execute("clear"));
    }

    private static void firstEquation4(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("5 4 3 2"));
        System.out.println(calculator.execute("undo undo *"));
        System.out.println(calculator.execute("5 *"));
        System.out.println(calculator.execute("undo"));
    }

    private static void firstEquation5(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("7 12 2 /"));
        System.out.println(calculator.execute("*"));
        System.out.println(calculator.execute("4 /"));
    }

    private static void firstEquation6(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("1 2 3 4 5"));
        System.out.println(calculator.execute("*"));
        System.out.println(calculator.execute("clear 3 4 -"));
    }

    private static void firstEquation7(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("1 2 3 4 5"));
        System.out.println(calculator.execute("* * * *"));
    }

    private static void firstEquation8(Memory memory) {
        Calculator calculator = new Calculator(memory);
        calculator.clear();

        System.out.println(calculator.execute("1 2 3 * 5 + * * 6 5"));
    }
}
