package com.company;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Stack;

public class RemoteHelloServiceImpl implements RemoteHelloService {

    public static final String BINDING_NAME = "sample/HelloService";

    public String sayHello(String name) {
        String string = "Hello, " + name + "! It is " + System.currentTimeMillis() + " ms now";
        try {
            System.out.println(name + " from " + UnicastRemoteObject.getClientHost());
        } catch (ServerNotActiveException e) {
            System.out.println(e.getMessage());
        }
        if ("Killer".equals(name)) {
            System.out.println("Shutting down...");
            System.exit(1);
        }
        return string;
    }

    private static int sum(int a, int b) { return a+b; }

    private static int sub(int a, int b) { return b-a; }

    private static int mult(int a, int b) { return a*b; }

    private static int div(int a, int b) { return b/a; }

    private static int calc(String str)
    {
        Stack <Integer> stack = new Stack<>(); // стек с операндами, тут же и получим значение выражения
        ArrayList<Object> expression = new ArrayList<>(); // список для хранения выражения в обратной польской записи
        for (Object op : str.split(",")){
            expression.add(op);
        }
        for (Object s : expression){
            switch(s.toString()) {
                case "+":
                    // вызов удаленного метода
                    stack.push(sum(stack.pop(), stack.pop()));
                    break;
                case "-":
                    // вызов удаленного метода
                    stack.push(sub(stack.pop(), stack.pop()));
                    break;
                case "*":
                    // вызов удаленного метода
                    stack.push(mult(stack.pop(), stack.pop()));
                    break;
                case "/":
                    // вызов удаленного метода
                    stack.push(div(stack.pop(), stack.pop()));
                    break;
                default:
                    stack.push(Integer.valueOf(s.toString()));
                    break;
            }
         }
        return stack.pop();
    }

    public int Calculation(String exp) {
        int res = 0;
        String str = "";
        //String str = "6,10,+,4,-,1,1,2,*,+,/,1,+";
        //String str2 = "8,2,5,*,+,1,3,2,*,+,4,-,/";
        //str = ОбратнаяПолЗапись(exp);
        res = calc(str);
        return res;
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2099);
        System.out.println(" OK");

        final RemoteHelloService service = new RemoteHelloServiceImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}