package com.jags;

/**
 * Created by JParvathaneni on 1/28/16.
 */
public class ReflectionTest {

    public static void main(String[] args) {
        String className = args[0];
        try {
            Class klass = Class.forName(className);
            Object obj = klass.newInstance();
            System.out.println(obj);

            for(int i = 0; i<klass.getConstructors().length; i++){
                System.out.println(klass.getConstructors()[i]);
            }
            for(int i = 0; i<klass.getMethods().length; i++){
                System.out.println(klass.getMethods()[i]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
