import java.util.*;
class BTree{
  private TNode root; // root of the BTree
  TNode getroot(){
      return this.root;
  }
  void setroot(TNode x){
      this.root=x;
  }
 BTree(){
     root=null;
 }
 void PreOrder(TNode x){ // x is a Node
     if(x!=null){
         System.out.print(" "+x.Data);
         PreOrder(x.LC);
         PreOrder(x.RC);
      }
 }
 void PostOrder(TNode x){
     if(x!=null){
         PostOrder(x.LC);
         PostOrder(x.RC);
         System.out.print(" "+x.Data);
     }
 }
 
}