package maman16;
/*
 * This constructs nose and include setters and getters(details are written in ThreadedTree class)
 * */
public class Node {
protected int key; // student's ID
protected String name; // student's name
protected Node left;//left son
protected Node right;//right son
protected Node parent; // parent of the node
protected boolean lthread; //left thread
protected boolean rthread; //right thread

public Node(int key, String name, Node parent) {
this.key = key;
this.name = name;
this.parent = parent;
}
public int getKey() {
return key;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public Node getLeft() {
return left;
}
public Node getRight() {
return right;
}

protected void setLeft(Node left) {
this.left = left;
}
protected void setRight(Node right) {
this.right = right;
}
protected Node getParent() {
return parent;
}
protected void setParent(Node parent) {
this.parent = parent;
}
protected boolean getRthread()
{
return rthread;
}
protected boolean getLthread()
{
return lthread;
}
protected void setRthread(boolean val)
{
this.rthread = val;
}
protected void setLthread(boolean val)
{
this.lthread = val;
}
public String toString()
{
return this.key +this.name +this.parent;
}
}