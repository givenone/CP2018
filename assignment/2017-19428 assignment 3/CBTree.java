public class CBTree {

	public class stack
	{
		CBNode [] st;
		int top;
		public stack(int max)
		{
			st = new CBNode[max];
			top = 0;
		}
		public CBNode Pop()
		{
			return st[top--];
		}
		public void Push(CBNode node) {
			st[++top] = node;
		}
	}

	public CBNode root = null;

	/* Helper method. */
	public CBNode getRoot()
	{
		return root;
	}

	public CBTree(String st, String con)
	{
		if(st.isEmpty())
			return;

		stack Stack = new stack(st.length());
		root = new CBNode();
		Stack.Push(root);
		int j=0;
		for(int i=0; i<st.length(); i++)
		{
			CBNode fortemp = Stack.Pop();
			fortemp.label = st.charAt(i);
			if (fortemp.label == '0')
			{
				fortemp.character = con.charAt(j++);
				fortemp.left = new CBNode();
				fortemp.right = new CBNode();
				Stack.Push(fortemp.right);
				Stack.Push(fortemp.left);
			}
		}
	}



	public  String post(CBNode now)
	{
		if(now == null || now.label == '1')
			return "";

		return post(now.left) + post(now.right) + now.character;

	}

	public String postOrderTraversal()
	{
		return post(root);
	}

	public String in(CBNode now)
	{
		if(now == null || now.label == '1')
			return "";
		return in(now.left) +now.character +  in(now.right);
	}

	public String inOrderTraversal()
	{
		return  in(root);
	}

	public String posts(CBNode now)
	{
		if(now == null)
			return  "";
		else if(now.left != null && now.right != null)
			return posts(now.left) + posts(now.right) + "0";
		else
			return posts(now.left) + posts(now.right) + "1";
	}
	public String postOrderStructure()
	{
		return posts(root);
	}

	public String ins(CBNode now)
	{
		if(now == null)
			return  "";
		else if(now.left != null && now.right != null)
			return  ins(now.left) + "0" + ins(now.right);
		else
			return ins(now.left) + "1" + ins(now.right) ;
	}
	public String inOrderStructure()
	{
		return ins(root);
	}

}
