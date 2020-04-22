package maman16;

/* This program allows interaction with the user
* the program ask the user first if he want to insert text file?
* if the user press "Yes", the program opens the text file and runs on the orders that the user gave in the text file. text file need to locate with java class file.
* if the user press "No" or any other iput, then input will be from the keyboard. A menu will be opened for the user where he can choose the desired action using the numbers
* after finishing desired action, the program will return to the start menu and ask the user again what action he wants to do
* to exit the program the user need to press 12 (exit option in the menu)
*/

import java.io.File;
import java.util.Scanner;

public class scanner {
	public static void main(String[] args) {
		Node root = new Node(4, "val", null);
		ThreadedTree tree = new ThreadedTree(root);
		//Node aaa = tree.Insert(new Node(1, "aa", null));
		//Node bbb = tree.Insert(new Node(2, "bb", aaa));
		
		Scanner scanner = new Scanner(System.in);
		String answer;
		 System.out.println("Do you want to take info from text file?Yes/No");
		 answer = scanner.next(); //Yes/NO. if not Yes, each input get into the keyboard input
	 	 if (answer.equals("Yes"))
	 	 {
	 		try {
	            System.out.print("Enter the file name: ");

	            Scanner input = new Scanner(System.in);

	            File file = new File(input.nextLine());//text file name

	            input = new Scanner(file);
	       String line;  // command from text file   
           do
            {
        	    line = input.next();
	            System.out.println(line);
	            if (line.equals("insert"))  
	            {
	            	int num = input.nextInt();// key of the node(student ID)
	            	String name = input.next();// student name
            		tree.Insert(new Node(num, name, null));// inserting node
	            	}
	            
	            else if (line.equals("delete"))
	            {
	            	int num = input.nextInt();// key of the node(student ID), no need to input student name, because student's ID(the key of the node) is unique
	            	Node n = tree.Search(num);
	            	if(n != null) // node is in the tree
	            	{
	            		tree.Delete(n.key);
	            	}
	            	else // node is not in the tree
	            		System.out.println("no key in the tree, no deletion");
	            }
	            if (line.equals("search"))
	            {
	            	int num = input.nextInt();// key of the node(student ID), no need to input student name, because student's ID(the key of the node) is unique
	            	tree.Search(num);
	            	Node n = tree.Search(num);
	                if(n != null) // node is in the tree
	                {
	                	System.out.println("search" +  tree.Search(num));
	                }
	                else // node is not in the tree
	                {
	                	System.out.println("null");
	                }

	            }
	            if (line.equals("successor"))
	            {
	            	int num = input.nextInt();// key of the node(student ID)
	            	// print successor of given node
	            	Node n = tree.Search(num);
	                if(n!= null && tree.Successor(n)!= null) // node is in tree and successor is in the tree
	                {
	                	System.out.println("Successor of the node is:" + tree.Successor(n).getKey());
	                }
	                else //node is not in the tree
	                {
	                	System.out.println("Successor of the node is: null");
	                }
	            }
	            if (line.equals("predecessor"))
	            {
	            	int num = input.nextInt();// key of the node(student ID)
	            	// print predecessor of given node
	            	Node n = tree.Search(num);
	                if(n!= null && tree.Predecessor(n)!= null)// node is in tree and predecessor is in the tree
	                {
	                	System.out.println("Predecessor of the node is:" + tree.Predecessor(n).getKey());
	                }
	                else //node is not in the tree
	                {
	                	System.out.println("Predecessor of the node is: null");
	                }
	            }
	            else if (line.equals("minimum"))
	            {
	            	int num = input.nextInt();// key of the node(student ID)
	            	// print minimum in subtree, that node is root 
	                tree.Search(num);
	                if (tree.Search(num)!= null)// node is in tree
	                {
	                	Node n = tree.Search(num);
	                	System.out.println("minimum of the subtree is:" + tree.Min(n).getKey());
	                }
	                else// node is not in tree
	                {
	                	System.out.println("minimum of the subtree is: null");
	                }
	            }
	            else if (line.equals("maximum"))
	            {
	            	int num = input.nextInt();// key of the node(student ID)
	            	// print maximum in subtree, that node is root 
	            	tree.Search(num);
	                if (tree.Search(num)!= null)// node is in tree
	                {
	                	Node n = tree.Search(num);
	                	System.out.println("maximum of the subtree is:" + tree.Max(n).getKey());
	                }
	                else// node is not in tree
	                {
	                	System.out.println("maximum of the subtree is: null");
	                }
	            }
	            else if (line.equals("median"))
	            {
	            	if(tree.getMedian()!= null) //median not null
	            	{
	            		System.out.println("Median of the tree is:" + tree.getMedian().getKey());
	            	}
	            	else //median is null
	            	{
	            		System.out.println("Median of the tree is: null");
	            	}
	            }
	            else if (line.equals("printinorder"))
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printInOrder(n);
	            	}
	            }
	            else if (line.equals("printpostorder"))
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printPostOrder(n);
	            	}
	            }
	            else if (line.equals("printpreorder"))
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printPreOrder(n);
	            	}
	            }
            } while (!line.equals("exit")); //exiting when order is exit
	            System.out.println("DONE");
	            input.close();// close file

	        } catch (Exception ex) 
	 		{
	            ex.printStackTrace();
	        }
	 	 }
	 	 else //if (answer.equals("No") or any other input)
	 	 {
	 		 Scanner sc = new Scanner(System.in);
		 int order = 0; // input number of command
		 while (order != 12) 
		 {
	        System.out.println("please enter order:" + "\n" + "1 for insert node \n2 for delete node \n3 for search node by key \n4 for successor of given node \n5 for predecessor of given node \n6 for minimum node on subtree with input root\n7 for maximum node on subtree with input root \n8 for median \n9 for inorder \n10 for postorder \n11 for preorder \n12 for exit");
	        order = sc.nextInt();

	            if (order == 1) //insert
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();
	                System.out.println("please enter name");
	                String name = sc.next();
	                tree.Insert(new Node(num, name, null));
	            }
	            else if (order == 2) // delete
	            {
	                System.out.println("please enter number"); 
	                int num = sc.nextInt();// key of the node(student ID), no need to input student name, because student's ID(the key of the node) is unique
	                Node n = tree.Search(num);
	                if(n != null)
	            	{
	            		tree.Delete(n.key);
	            	}
	            	else
	            		System.out.println("no key in the tree, no deletion");
	            }
	            else if (order == 3) // search
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();// key of the node(student ID), no need to input student name, because student's ID(the key of the node) is unique
	                tree.Search(num);
	                if(tree.Search(num)!= null)
	                {
	                	System.out.println("search" +  tree.Search(num));
	                }
	                else
	                {
	                	System.out.println("null");
	                }
	            }
	            else if (order == 4) // successor
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();// key of the node(student ID)
	                Node n = tree.Search(num);
	                if(n!= null && tree.Successor(n)!= null)
	                {
	                	System.out.println("Successor of the node is:" + tree.Successor(n).getKey());
	                }
	                else
	                {
	                	System.out.println("Successor of the node is: null");
	                }
	            }
	            else if (order == 5) // predecessor 
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();// key of the node(student ID)
	                Node n = tree.Search(num);
	                if(n!= null && tree.Predecessor(n)!= null)
	                {
	                	System.out.println("Predecessor of the node is:" + tree.Predecessor(n).getKey());
	                }
	                else
	                {
	                	System.out.println("Predecessor of the node is: null");
	                }
                }
	            else if (order == 6) // find minimum
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();// key of the node(student ID)
	                tree.Search(num);
	                if (tree.Search(num)!= null)
	                {
	                	Node n = tree.Search(num);
	                	System.out.println("minimum of the subtree is:" + tree.Min(n).getKey());
	                }
	                else
	                {
	                	System.out.println("minimum of the subtree is: null");
	                }
	            }
	            else if (order == 7) // find maximum
	            {
	                System.out.println("please enter number");
	                int num = sc.nextInt();// key of the node(student ID)
	                tree.Search(num);
	                if (tree.Search(num)!= null)
	                {
	                	Node n = tree.Search(num);
	                	System.out.println("maximum of the subtree is:" + tree.Max(n).getKey());
	                }
	                else
	                {
	                	System.out.println("maximum of the subtree is: null");
	                }
	            }
	            else if (order == 8) // return median node
	            {
	            	if(tree.getMedian()!= null)
	            	{
	            		System.out.println("Median of the tree is:" + tree.getMedian().getKey());
	            	}
	            	else
	            	{
	            		System.out.println("Median of the tree is: null");
	            	}
	            }
	            else if (order == 9) // printInOrder
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printInOrder(n);
	            	}
	            }
	            else if (order == 10) // printPostOrder
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printPostOrder(n);
	            	}
	            }
	            else if (order == 11) // printPreOrder
	            {
	            	Node n = tree.Search(root.key);
	            	if(n == null)
	            	{
	            		System.out.println("no nodes in the tree");
	            	}
	            	else
	            	{
	            		tree.printPreOrder(n);
	            	}
	            }
	            else // exiting
	            {
	                System.out.println("exit"); // end of the loop to input commands
	            }
		 }
	            System.out.println("DONE");
	            sc.close();// close console to input commands
	 	 }
	 	 scanner.close();	 
	}
	}

