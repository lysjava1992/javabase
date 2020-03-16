package com.handbook.java.datastructure.linkedlist;

public class SingleLinkedList2 {
    private Node head;
    private int size;

    public SingleLinkedList2() {
        head=null;
        size=0;
    }

    public int getSize() {
        return size;
    }


    //链头插入
    public void insertToHead(Object object){
       Node newNode=new Node(object);
       if (size==0){
           head=newNode;
           size++;
       }else {
          newNode.next=head;
          head=newNode;
          size++;
       }
    }
    //指定位置插入
    public String insertByIndex(int index,Object object){
        if(index>size){
            return "无效位置";
        }else{
            Node node=new Node(object);
            if(index==0){
                node.next=head;
                head=node;
                size++;
            }else {
                Node pre=head;
                int i=0;
                while (i<(index-1)){
                    i++;
                    pre=pre.next;
                }
                node.next=pre.next;
                pre.next=node;
            }
            return "success";
        }
    }
    //链头删除
    public void removeHead(){
        head=head.next;
        size--;
    }
    //获取链头数据
    public Object getHead(){
        return head.data;
    }
    //通过下标获取数据
    public Object getByIndex(int index){
        if(index>=size){
            return "下标越界";
        }else {
            int currentIndex=0;
            Node currentNode=head;
            while (currentIndex<index){
                currentNode=currentNode.next;
            }
            return currentNode.data;
        }
    }
    //查询是否包含某值，返回下标
    public int contain(Object object){
        int result=-1;
        int currentIndex=0;
        Node currentNode=head;
        if (head.data.equals(object)){
            return 0;
        }else {
            while (currentIndex<size){
                 if(currentNode.data.equals(object)){
                     return currentIndex;
                 }else {
                     currentNode=currentNode.next;
                     currentIndex++;
                 }
            }
        }
        return result;
    }
    //输出
    public void display(){
        Node currentNode=head;
        System.out.print("[");
        while (currentNode!=null){
            System.out.print(currentNode.data+"->");
            currentNode=currentNode.next;
        }
        System.out.print("]");
        System.out.println();
    }
    public static class Node{
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }
    }
}
