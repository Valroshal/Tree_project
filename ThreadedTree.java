package maman16;

/* Valeria Kabishcher 
 * ID 328980701
 *
 * This program builds threaded binary tree (with root and nodes). Each node contains key (it will be an ID of student), 
 * value (it will be student's name), right son node, left son node, parent node, and left and right threads, which is boolean 
 * variable (contains information if the node has left/right thread or not). Program supports actions as insert node into the tree,
 * delete node from the tree, find successor, predecessor, minimum, maximum of given node, search node by given key, printing all
 * nodes from tree in inorder, postorder and preorder traversal, and return median node of the tree.   
 * The program has 2 ways of interaction with a user. It can receive commands from text file, or straight from user by keyboard 
 * (in this case user has instruction file how to input commands by keyboard).
 * There are 4 classes inside the program: class Node, class ThreadedTree, class scanner, class program.  
 * Class Node includes all operations for node: constructor, getters and setters, and override to toString.
 * Class ThreadedTree is class that includes constructor for threaded tree and all the methods that program contains (Insert, Delete,
 * Successor, Predecessor, Min, Max, Search, printInorder, printPostorder, printPreorder, getMedian. 
 * Class scanner includes code for input options that program supports(keyboard and text file). 
 * Class program includes main function test file to check if input was correctly obtained(this file is just example of the tester(driver)).   
 * */

public class ThreadedTree {
	 protected Node root; // root of threaded tree
	 protected Node median; // median node in the tree
	 int count = 0; // integer that counts nodes (+ for insert, - for delete) for shifting the median
	 
	// constructor of threaded tree. parameter is node that will be root of the tree
	 public ThreadedTree(Node root)
	 {
		 if(root == null) 
			 throw new IllegalArgumentException("root is null, crashing");// if root is null, the program will crash with exception(no tree can be with null root)
			 
		 this.root = root;
		 median = root;
		 count = 1;// adding 1 for count, because root is the first node on the tree
		 root.setLthread(true);
		 root.setRthread(true);
	 }

/* INSERT - method to insert new node into the tree. Can insert if node is not null.
*method checks if node is already on the tree - method doesn't add any node.
*method searches the location for new node and insert it there. it changes the location of other nodes if needed.
*parameter is node to insert 
*/	  
	    public Node Insert(Node node)
	    {
	    	// Searching for a Node with given value
	    	Node ptr = root;
	    	Node parent = null;
	    	while (ptr != null)
	        {
	            // If key already exists, return null
	    		if (node.key == ptr.key) 
	    		{
	    			System.out.println("key is already in the tree"); // key is unique, and if key is already exists in the tree, so it can't insert one more node with the same key  
	    			return null; 
	    		}
    			parent = ptr;
    			// Moving on left subtree. 
    			if (node.key < ptr.key)
    			{
    				if (ptr.lthread == false)
    				{
    						ptr = ptr.left;
    				}
    				else
    				{
    					break;
    				}
    			}
    			// Moving on right subtree.
    	        else
    	        {
    	            if (ptr.rthread == false)
    	            {
    	                ptr = ptr.right;
    	            }
    	            else
    	            {
    	                break;
    	            }
    	        }
	        }
	    	 // Create a new node
	        Node tmp = new Node(node.key,node.name,null); // new node to insert
	        tmp.setLthread(true);
	        tmp.setRthread(true);
	        if (parent == null) // is root
	        {
	            root = tmp;
	            tmp.setLeft(null);
	            tmp.setRight(null);
	        }
	        else if (tmp.key < (parent.key)) // left son
	        {
	            tmp.setLeft(parent.left); 
	            tmp.right = parent;
	            parent.lthread = false;
	            parent.left = tmp;
	            tmp.setParent(parent);
	        }
	        else // right son
	        {
	            tmp.left = parent; 
	            tmp.right = parent.right;
	            parent.rthread = false;
	            parent.right = tmp;
	            tmp.setParent(parent);
	        }
	        count++;// add inserted node to count
	        // finding new median node (checking if needed) 
	        if (count == 1)
	        {
	        	median = tmp;
	        }
	        if (count%2==0 && tmp.key>median.key) 
	        {
	        	median = Successor(median);
	        }
	        if (count%2!=0 && tmp.key<median.key)
	        {
	        	median = Predecessor(median);
	        }
	        return tmp; // return new inserted node
	    }

    /* DELETE- method to delete node from the tree.
     * method finds node (parameter of function is integer that is the key of node to be deleted), by Search method, 
     * if node is on the tree, the method will remove it and change the location of other nodes to save the tree.  
     * method uses 3 private methods(CaseA, CaseB, CaseC) for 3 different cases:
     * CaseA - to delete leaf
     * CaseB - to delete node with one child
     * CaseC - to delete node with 2 children 
     */
	    public Node Delete(int nodeKey)
	    {
	    	Node ptr = Search(nodeKey); //current node, found by search to be deleted
	    	
	    	if (ptr == null)
	    	{
	    		System.out.println("No node with this key in the tree, no deletion");
	            return null;
	    	}
	        // Two Children
	        else if (ptr.lthread == false && ptr.rthread == false)
	        {
		        root = caseC(root, ptr);
	        }
            // Only Left Child
	        else if (ptr.lthread == false && ptr.rthread == true)
	        {
	            root = caseB(root, ptr);
	        }
	        // Only Right Child
	        else if (ptr.rthread == false && ptr.lthread == true)
	        {
	            root = caseB(root, ptr);
	        }
	        // No children
	        else
	        {
	            root = caseA(root, ptr);
	        }
	    	count--; // decrease count when node deleted
	    	//code for median
	    	if (count == 0)
	        {
	        	median = null;
	        }
	    	if (median!= null && count%2!=0 && ptr.key>=median.key)
	        {
	        	median = Predecessor(median);
	        }
	        if (median!= null && count%2==0 && ptr.key<median.key)
	        {
	        	median = Successor(median);
	        }
	        
	        System.out.println("deleted " + ptr.key);
	        return ptr; // return deleted node
	    }
	    	//case 1: Leaf Node need to be deleted
	    private Node caseA (Node root, Node ptr) 
	    {
	    	// If Node to be deleted is root
	    	if (ptr.parent == null)
	    	{
	    		root = null;
	    	}
	    	// If Node to be deleted is left of its parent
	    	else if(ptr == ptr.parent.left)
	    	{
	    		ptr.parent.lthread = true;
	    		ptr.parent.setLeft(ptr.left);
	    	}
	    	else
	    	{
	    		ptr.parent.rthread = true;
	    		ptr.parent.setRight(ptr.right);
	    	}
	        return root;
	    }
	    	//Case 2: Node to be deleted has only one child
	    private Node caseB (Node root, Node ptr) 
	    {
	    	// Node to be deleted has left child.
	        Node rep; // node to replace the deleted node
	    	if(ptr.lthread == false) 
	        {
	    		rep = ptr.left;
	        }
	        // Node to be deleted has right child.
	        else
	        {
	        	rep = ptr.right;
	        }
	        // Node to be deleted is root Node.
	    	Node par = ptr.parent;
	        if(par==null)
	        {
	        	root = rep;
	        }
	        // Node is left child of its parent.
	        else if(par.left!= null && ptr.key == par.left.key)
	        {
	        	par.setLeft(rep);
	        	rep.setParent(par);
	        }
	        else
	        {
	        	par.setRight(rep);
	        	rep.setParent(par);
	        }
	        // Find successor and predecessor
	        Node s = Successor(ptr);
	        Node p = Predecessor(ptr);
	        // If root has left subtree.
	        if (ptr.lthread == false && p!= null && s!= null) //new
	        {
	        	p.setRight(s);
	        }
	        // If root has right subtree.
	        else if (ptr.rthread == false && s!= null && p!=null) //new
        	{
	        	p.setLeft(s);
        	}
        	return root;
	    }	
        	//Case 3: Node to be deleted has two children
        private Node caseC (Node root, Node ptr)
        {
        	// Find successor and its parent.
        	Node psucc = ptr;// parent of successor
        	Node succ = ptr.right;// successor
        	// Find leftmost child of successor
        	while(succ.left != null && succ.lthread == false)
        	{
        		psucc = succ;
        		succ = succ.left;
        	}
        	ptr.key = succ.key;
        	ptr.setName(succ.name);
        	if (succ.lthread == true && succ.rthread == true)
        	{
        		root = caseA(root, succ);
        	}
        	else
        	{
        		root = caseB(root, succ);
        	}
        	return root;
	    }

    //PREDESECCOR - find previous(by key) node of the node that method receives as parameter   
    public Node Predecessor(Node node)
    {
    	if (root == null || node.key == Min(root).key)
        {
        	return null; 
        }
    	if (node.lthread == false)
    	{
    		return Max(node.left);
    		
    	}
    	Node parent = node.parent; // parent will returned as predecessor
    	if (parent != null && node == parent.right)
    	{
    		return parent;	
    	}
    	else 
    	{
    		while(parent != null && node == parent.left)
    		{
    			node = parent;
                parent = parent.parent;
    		}
    		return parent;//predecessor
    	}
    }
    
 // SUCCESSOR - find following (by key) node of the node that method receives as parameter
    public Node Successor (Node node) 
    {
    	if (root == null)   
    	{
    		return root;
    	}
    	if (Max(root)!= null && node.key == Max(root).key)
    	{
    		return null;
    	}
        // step 1 of the above algorithm 
        if (node.rthread == false) 
        {
            return Min(node.right);
        }
        // step 2 of the above algorithm
        Node parent = node.parent; // parent will returned as successor
        while (parent != null && (node == parent.right || node.left ==parent.right)) 
        {
            node = parent;
            parent = parent.parent;
        }
        return parent; //successor
    }
    
    // GET MINIMUM - find leftmost node (it will be minimum in the tree)
    public Node Min(Node node) //(threaded node)
    {
    	Node current = node;
    	if (current == null)
    	{
    		return null;
    	}
    	//loop down to find the leftmost leaf
    	while (current.lthread == false && current.left != null) 
    	{
    		current = current.left;
    	}
		return current; // return the minimum
    }

    // GET MAXIMUM - find rightmost node (it will be maximum in the tree)
    public Node Max(Node node)
    {
    	Node current = node;
    	if (current == null)
    	{
    		return null;
    	}
    	//loop down to find the rightmost leaf
    	while (current.rthread == false && current.right != null)
    	{
    		current = current.right;
    	}
		return current; // return the maximum 
    }
    
/* SEARCH - method searches(by key, that method receives as parameter) if the node is in the tree.
  *if the node is in the tree - the node returned, if not, returned null.
  *the method uses the private function to search the node. 
  */
    public Node Search(int key)
    {
        return Search (root,key);
    }
    private Node Search(Node root, int key1)
    {
    	// Base Cases: root is null or key is present at root
        if (root==null || root.key==key1)
        {
            return root;
        }
        // value is greater than root's key
        if (root.key > key1 && root.lthread == false)
        {
            return Search(root.left, key1);
        }
        // value is less than root's key
        else if (root.key < key1 && root.rthread == false) 
        {
        	return Search(root.right, key1);
        }
        return null;
    }
    //the method prints its nodes according to INORDER 
    public void printInOrder (Node node)
    {
    	 if (node == null) 
    	 {
    	     return;
    	 }
    	 // if node has 2 children
    	 if (!node.lthread && !node.rthread)
         {
    	      printInOrder(node.left);
    	      System.out.print(node.key + " "); // the space added to do the output more readable 
    	      printInOrder(node.right);
         }
         // if node has left child
    	 if (!node.lthread && node.rthread)
    	 {
    	     printInOrder(node.left);
    	     System.out.print(node.key + " "); // the space added to do the output more readable
    	 } 
    	 //if node has right child
    	 if (node.lthread && !node.rthread)
    	 {
    	     System.out.print(node.key + " "); // the space added to do the output more readable
    	     printInOrder(node.right);
    	 }
    	 // if node has no children
    	 if (node.lthread && node.rthread)
    	 {
    	 System.out.print(node.key + " "); // the space added to do the output more readable
    	 }
    }
    //the method prints its nodes according to POSTORDER 
    public void printPostOrder(Node node)
    {
    	if (node == null) 
    	{
    		return;
    	}
    	 // first recur on left subtree
    	if(!node.lthread)
    	{
    		printPostOrder(node.left);
    	}
    	if(!node.rthread)
    	{
    		printPostOrder(node.right);
    	}
    	System.out.print(node.key + " "); // the space added to do the output more readable
    }
    //the method prints its nodes according to PREORDER 
    public void printPreOrder(Node node)
    {
        if (node == null)
        	{
        		return;
        	} 
        // first print data of node 
        System.out.print(node.key + " "); // the space added to do the output more readable
        // then recur on left subtree 
        if(!node.lthread)
    	{
    		printPreOrder(node.left);
    	}
        // now recur on right subtree
        if(!node.rthread)
    	{
    		printPreOrder(node.right);
    	}
    }
    // method getMedian returns the median node in the tree.
    public Node getMedian()
    {
    	if(root == null)
    	{
    		return null;
    	}
    	return median;
    }
}