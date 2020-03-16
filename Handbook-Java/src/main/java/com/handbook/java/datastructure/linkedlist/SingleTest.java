package com.handbook.java.datastructure.linkedlist;

public class SingleTest {
    public static void main(String[] args) {
//        SingleLinkedList linkedList=new SingleLinkedList();
//        linkedList.addHead(1);
//        linkedList.addHead(2);
//        linkedList.addHead(3);
//        linkedList.addHead(4);
//        linkedList.addHead(5);
//        linkedList.addHead(5);
//        System.out.println(linkedList.getSize());
//        linkedList.display();
//        linkedList.delete(5);
//        linkedList.display();
//        System.out.println(linkedList.find(5));



        SingleLinkedList2 linkedList=new SingleLinkedList2();
        linkedList.insertToHead("A");
        linkedList.insertToHead("B");
        linkedList.insertToHead("C");
        linkedList.insertToHead("D");
        linkedList.insertToHead("E");
        System.out.println(linkedList.getSize());
        linkedList.display();
        linkedList.insertByIndex(0,"F");
        linkedList.display();

//        //删除
//        linkedList.removeHead();
//        System.out.println(linkedList.getSize());
//        linkedList.display();
//        //查找
//        System.out.println(linkedList.contain("A"));
//        System.out.println(linkedList.getHead());
//        System.out.println(linkedList.getByIndex(4));


    }
}
