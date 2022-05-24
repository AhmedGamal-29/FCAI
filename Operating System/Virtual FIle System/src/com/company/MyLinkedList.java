package com.company;

public class MyLinkedList
{
    Node head;

    public void insert(MyLinkedList list, int data)
        {
            Node new_node = new Node(data);
            new_node.next = null;

            if (list.head == null)
            {
                list.head = new_node;
            }
            else
            {
                Node last = list.head;
                while (last.next != null)
                {
                    last = last.next;
                }

                last.next = new_node;
            }
        }

        public void printList(MyLinkedList list)
        {
            Node currNode = list.head;
            System.out.print("File Indices: ");

            while (currNode != null)
            {
                System.out.print(currNode.block + " --> ");
                currNode = currNode.next;
            }
            System.out.print("null");
            System.out.println();
        }
}
