package ru.geedrains.javalevel2.homework.lesson_3;

public class Main {
    static final int size = 10000000;
    static final int half = size/2;
    float[] arr = new float[size];

    private  static void inputarr(float[] arr) {
        for (int i = 0; i<size; i++)
            arr[i] = 1;
    }

    private static long metod_1(float[] arr) {
        inputarr(arr);
        long a_begin = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float)(arr[i]*Math.sin(0.2f + i/5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return System.currentTimeMillis()-a_begin;
    }

    private static long metod_2 (float[] arr){
        float[] a1 = new float[half];
        float[] a2 = new float[half];
        inputarr(arr);
        long a_begin = System.currentTimeMillis();
        System.arraycopy(arr,0,a1,0,half-1);
        System.arraycopy(arr,half,a2,0,half-1);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                metod_1(a1);
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                metod_1(a2);
            }
        };
        Thread m1 = new Thread(r1);
        try {
            m1.join(); // while (m.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread m2 = new Thread(r2);
        try {
            m2.join(); // while (m.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1,0,arr,0,half-1);
        System.arraycopy(a2,0,arr,half,half);
        return System.currentTimeMillis() - a_begin;
    }

    public static void main(String[] args) {
	// write your code here
        float[] arr = new float[size];
     System.out.println("Время выполнения метода 1 "+ metod_1(arr));
     System.out.println("Время выполнения метода 2 "+ metod_2(arr));
    }
}
