class TNode{
	String Data;
	TNode P,LC,RC; // P = Parent  RC= Right Child      LC= LeftChild
	TNode(String d){
		this.Data=d;
		this.P=null;
		this.LC=null;
                this.RC=null;
	}
}