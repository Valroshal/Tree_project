package maman16;

public class programm {


	public static void main(String[] args) {
		Node root = new Node(13, "ccd", null);

		ThreadedTree tree = new ThreadedTree(root);
		System.out.println(" the median is " + tree.median.getKey());
		Node zzz = tree.Insert(new Node(5, "bbb", null));
		tree.printInOrder(root);

		System.out.println(" the median is " + tree.median.getKey());
		 tree.Delete(5);
		 tree.printInOrder(root);

		System.out.println(" the median is " + tree.median.getKey());
		tree.Insert(new Node(15, "bbb", null));
		tree.printInOrder(root);

		System.out.println(" the median is " + tree.median.getKey());
		tree.Delete(15);
		 tree.printInOrder(root);

		System.out.println(" the median is " + tree.median.getKey());
		
		Node zzx = tree.Insert(new Node(6, "cck", null));
		Node aaa = tree.Insert(new Node(1, "cce", null));
		
		tree.Insert(new Node(3, "bbb", null));
		tree.Insert(new Node(4, "ccg", null));

		Node asd = tree.Insert(new Node(12, "ccs", null));
		Node zxc = tree.Insert(new Node(13, "ccl", null));
		tree.Insert(new Node(8, "valerie", null));
		tree.Insert(new Node(4, "ccf", null));
		tree.Insert(new Node(5, "cch", null));

		tree.printInOrder(root);

//		 System.out.println(" the median is " + tree.median.getKey());
//
//
//	    tree.Delete(4);
//		tree.Delete(8);
//		tree.Delete(45);
//		
		System.out.println("search: " + tree.Search(45));
//		
//		tree.Delete(152);
//		
//		tree.Insert(new Node(10, "ccj", null));
//
//		tree.printInOrder(root);
//		System.out.println("median is:" + tree.median.getKey());
//		System.out.println(" ");
//		tree.Delete(1);
//		tree.Delete(3);
//		tree.printInOrder(root);
//		System.out.println("median is:" + tree.median.getKey());
//		System.out.println(" ");
//		
//		tree.Delete(20);
////		System.out.println(tree.Search(15));
//		tree.Delete(5);
//		tree.printInOrder(root);
//		System.out.println(" ");
//		System.out.println("median is:" + tree.median);
//		
//		tree.Delete(12);
//		
//		tree.Delete(6);
//		tree.printInOrder(root);
//		System.out.println(" ");
//		System.out.println("median is:" + tree.median);
//		System.out.println(" ");
//
//		System.out.println(" ");
//
//		System.out.println(" ");
//		tree.printPreOrder(root);
//		System.out.println(" ");
//		tree.printPostOrder(root);
//		Node n = tree.Successor(zzx);
//		if(n != null)
//			System.out.println("successor is: " + n.getKey());
//		System.out.println("successor is: " + tree.Successor(zzz));
	}
}
