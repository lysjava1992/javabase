package com.handbook.java.datastructure.linkedlist;

public class SingleLinkedList {
    private  int size;
    private  Node head; //头元素

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SingleLinkedList(){
        size=0;
        head=null;
    }
    private class  Node{
        private  Object data; //数据
        private  Node next;//每个节点指向下一个节点
        public Node(Object data){
            this.data=data;
        }

        @Override
        public String toString() {
            return
                    "data=" + data;
        }
    }
    //在链头添加元素
  public  Object addHead(Object obj){
        Node newHead=new Node(obj);
        if(size==0){
            head=newHead;
        }else {
            newHead.next=head;
            head=newHead;
        }
        size++;
     return   obj;
  }

 //链表头删除元素
    public  Object deleteHead(){
        Object obj=head.data;
        head=head.next;
        size--;
        return obj;
    }
    //根据数据（Object）查找指定元素，返回Node,否则返回null
    public Node find(Object obj){
        Node current=head;
        int tempSize=size;
        while (tempSize>0){
            if (obj.equals(current.data)){
                return  current;
            }else {
                current=current.next;
            }
            tempSize--;
        }
        return null;
    }
    //删除元素
    public boolean delete(Object value){
        if(size==0){
            return  false;
        }
        Node current=head;
        Node previous=head;
        while (current.data!=value){
            if (current.next==null){
                return false;
            }else {
                current=current.next;
                previous=current;
            }
        }

        if(current==head){//如果删除的元素是头元素
           head=current.next;
           size--;
        }else {//如果删除的元素不是头元素；则要将该元素的上一个Node的指向修改
             previous.next=current.next;
             size--;
        }
        return true;
    }
    //判断是否为空
    public boolean isEmpty(){

        return (size==0);
    }
    //显示节点信息
    public void display(){
        if(size>0){
           Node node=head;
           int tempSize=size;
           System.out.print("[");
           while (tempSize>0){
               if(node.next==null){
                   System.out.print(node.data+"]");
                   System.out.println();
                   return;
               }else {
                   System.out.print(node.data+"->");
                   node=node.next;
               }

               tempSize--;
           }
        } else {
            System.out.println("[]");
        }
    }
}
