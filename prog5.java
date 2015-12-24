/* /*Programmer: Michael Habib
Create an IntNode class that has an int value and two pointers.
This class contains constructors for binary search trees as well as
getters and setters.
Also create a IntBST class which implements these trees. Some methods 
in this class are delete,insert,search,rotate left,rotate right, and height;
*/ 
class IntNode {
   private int num;
   private IntNode left, right;
   public IntNode(int num) {
      this.num=num;left=null;right=null;
   }
   public IntNode(int num, IntNode lf, IntNode rt) {
      this.num=num;left=lf;right=rt;
   }
   public int getNum() {
      return num;}
   public void setLeft(IntNode pt) {left=pt;}
   public void setRight(IntNode pt) {right=pt;}
   public IntNode getLeft() {
      return left;}
   public IntNode getRight() {
      return right;}
}
class IntBST {
// member variable pointing to the root of the BST
   private IntNode root;
// default constructor
   public IntBST() {
      root=null;
   }
// copy constructor
   public IntBST(IntBST t) {
      IntNode tCopy =t.root;
      root=IntBST(tCopy);
   
   }
	//O(n)
   private static IntNode IntBST(IntNode copiedTree){
      IntNode tree;
      if(copiedTree==null)
         tree=null;
      else{
      //set root to a leaf with value num, copy left side, copy right side
         tree=new IntNode(copiedTree.getNum());
         tree.setLeft(IntBST(copiedTree.getLeft()));
         tree.setRight(IntBST(copiedTree.getRight()));
      }
      return tree;
   }

// for output purposes -- override Object version

   public String toString() {
      return toString(root, 1);
   }
   private static String toString(IntNode l, int depth) {
      String s = "";
      if (l == null)
         ; // nothing to output
      else { 
         if (l.getLeft() != null) // don't output empty subtrees
            s = '(' + toString(l.getLeft(), depth + 1) + ')';
         s = s + l.getNum() + "-" + depth;
         if (l.getRight() != null) // don't output empty subtrees
            s = s + '(' + toString(l.getRight(), depth + 1) + ')';
      }
      return s;
   }
	//driver method
   public boolean search(int num) {
      return search(num,root);
   }
	//O(logn)
   private static boolean search(int num,IntNode tree){
      boolean searchResult;
      if(tree==null)//num not found/empty tree
         searchResult=false;
      else if(num==tree.getNum())//num found
         searchResult=true;
      else if(num>tree.getNum())//search right subtree
         searchResult=search(num,tree.getRight());
      else//search left subtree
         searchResult=search(num,tree.getLeft());
      return searchResult;
   }//driver method
   public void insert(int num) {
      root=insert(num,root);
   }
	//O(logn)
   private static IntNode insert(int num,IntNode tree){
      if(tree==null)//empty tree, insert leaf
         tree=new IntNode(num,null,null);
      else if(num==tree.getNum()){}//node already exists-do nothing
      else if(num<tree.getNum())//insert to left side
         tree.setLeft(insert(num,tree.getLeft()));
      else
         tree.setRight(insert(num,tree.getRight()));
   		//insert to right side
      return tree;
   }
//driver method
   public int height() {
      return height(root);
   }
	//O(n)
   private static int height(IntNode tree){
      int height,leftHeight,rightHeight;
      if(tree==null)//empty tree,height is 0
         height=0;
      else{
      //initialize left and right height of tree
         leftHeight=height(tree.getLeft());
         rightHeight=height(tree.getRight());
       //left height > right height 
         if(leftHeight>rightHeight){
            height=leftHeight+1;
         }
         //right height>Left height
         else if(leftHeight<rightHeight){
            height=rightHeight+1;
         }
         else//left height=right height
            height=leftHeight+1;
      }
      return height;
   }
	//driver method
   public int closeLeaf() {
      return closeLeaf(root);
   }
	//O(n)
   private static int closeLeaf(IntNode tree){
      int closeLeafDepth;
      if(tree==null)//empty tree no leaves
         closeLeafDepth=0;
      else if(tree.getRight()==null&&tree.getLeft()==null)
         closeLeafDepth=1;//leaf depth is 1
      else if(tree.getLeft()==null)//no left child find close leaf
      										//on right side,add 1 for root
         closeLeafDepth=closeLeaf(tree.getRight())+1;
      else if(tree.getRight()==null)//no right child find close leaf
      										//on left side, add 1 for root
         closeLeafDepth=closeLeaf(tree.getLeft())+1;
      	//find close leaf on right side if left side height is bigger than the right
      	//add 1 for root
      else if(closeLeaf(tree.getLeft())>closeLeaf(tree.getRight()))
         closeLeafDepth=closeLeaf(tree.getRight())+1;
      else//find close leaf on left side if right side height is bigger than the right
      	//add 1 for root
         closeLeafDepth=closeLeaf(tree.getLeft())+1;
    
    
      return closeLeafDepth;
   }
 //driver method
   public int leafCt() {
      return leafCt(root);
   }
	//O(n)
   private static int leafCt(IntNode tree){
      int leafCounter;
      if(tree==null)
         leafCounter=0;
         //root is a leaf, leaf count is 1
      else if(tree.getLeft()==null&&tree.getRight()==null)
         leafCounter=1;
      else//add the leaves on the right and left side
         leafCounter=leafCt(tree.getLeft())+leafCt(tree.getRight());
      return leafCounter;
   }
//driver method
   public void delete(int num) {
      root=delete(num,root);
   }
	//O(logn)
   private static IntNode delete(int num, IntNode tree){
      IntNode tempTree;
      int value;
      if(tree==null){}//empty tree/no match , do nothing
      else{
         if(num==tree.getNum()){//num matches tree.getNum()
            if(tree.getRight()==null)//1 child node with null right point,skip over
               tree=tree.getLeft();
            else if(tree.getLeft()==null)//1 child node with null left pointer,skip over
               tree=tree.getRight();
            else{//node with two children
            //tempTree is the inOrderPredecessor
               tempTree=findRightMostNode(tree.getLeft());
            	//delete the inOrderPredecessr from tree
               tree=delete(tempTree.getNum(),tree);
            	//set temp tree to tree's left and right
               tempTree.setLeft(tree.getLeft());
               tempTree.setRight(tree.getRight());
            	//then set tree to temp
               tree=tempTree;
            }
         }//set root to left subtree with deleted node
         else if(num<tree.getNum())
            tree.setLeft(delete(num,tree.getLeft()));
         else//set root to right subtree with deleted node
            tree.setRight(delete(num,tree.getRight()));
      }
      return tree;
   }
 //finds and returns the right most node 
 //O(logn)
   private static IntNode findRightMostNode(IntNode tree){
      IntNode temp;
      if(tree.getRight()==null){
      //temp is a leaf with right most value of tree
         temp=new IntNode(tree.getNum());
      }
      else{//sets temp to rightmost node of tree
         temp=findRightMostNode(tree.getRight());
      
      }
      return temp;
   } 
// Rotate the node containing val to the left -- do nothing if not possible,
   public void rotateLeft(int num) {
      root=rotateLeft(num,root);
   }
	//O(logn)
   private static IntNode rotateLeft(int num,IntNode tree){
      IntNode tempTree;
      if(tree==null){}//empty tree
        //leaf, can't rotate
      else if(tree.getRight()==null&&tree.getLeft()==null){}
      //rotated value found
      else if(num==tree.getNum()){
      //no right subtree, can't rotate left
         if(tree.getRight()==null){}
         else{
         //set temp tree to left child of "root" right child
            tempTree=tree.getRight().getLeft();
            //set root's left child's pointer to "root"
            tree.getRight().setLeft(tree);
            //set tree to right child
            tree=tree.getRight();   
            //set rotated value's right child to temp
            tree.getLeft().setRight(tempTree);   
         } 
      }
      //num bigger than root, set root to the rotated_left, left subtree
      else if(num>tree.getNum())
         tree.setRight(rotateLeft(num,tree.getRight()));
      else //num bigger than root, set root to the rotated_left, right subtree
         tree.setLeft(rotateLeft(num,tree.getLeft()));
      return tree;
   }
// Rotate the node containing val to the right -- do nothing if not possible
   public void rotateRight(int num) {
      root=rotateRight(num,root);
   }//O(logn)
   private static IntNode rotateRight(int num,IntNode tree){
      IntNode tempTree;
      if(tree==null){}//empty tree
        //leaf, can't rotate
      else if(tree.getRight()==null&&tree.getLeft()==null){}
      //rotated value found
      else if(num==tree.getNum()){
      //no left subtree, can't rotate right
         if(tree.getLeft()==null){}
         else{
         //set temp tree to right child of "root" left child
            tempTree=tree.getLeft().getRight();
            //set roots right child's  pointer to "root"
            tree.getLeft().setRight(tree);
            //set tree to left child
            tree=tree.getLeft();  
            //set  rotated value's left child to temp
            tree.getRight().setLeft(tempTree);   
         } 
      }
      //num bigger than root, set root to the rotated_right, left subtree
      else if(num>tree.getNum())
         tree.setRight(rotateRight(num,tree.getRight()));
      else //num bigger than root, set root to the rotated_right, right subtree
         tree.setLeft(rotateRight(num,tree.getLeft()));
      return tree;
   }
// remove all of the leaves from the tree
   public void removeLeaves() {
      root=removeLeaves(root);
   }
	//O(n)
   private static IntNode removeLeaves(IntNode tree){
      if(tree==null){}//empty tree
      else if(tree.getLeft()==null&&tree.getRight()==null)
         tree=null;//root is leaf , remove and set tree to null
      else{//remove leaves from the right and left
         tree.setLeft(removeLeaves(tree.getLeft()));
         tree.setRight(removeLeaves(tree.getRight()));
      }
      return tree;
   }
   public static String myName() {
      return "Michael Habib";
   }
}