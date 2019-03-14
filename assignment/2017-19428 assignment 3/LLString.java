 public class LLString
{
	/* Add internal field variables here. */
	public class node
	{
		public  char key;
		public  node next;

		public node(char key, node next)
		{
			this.key = key;
			this.next = next;
		}
		public  node()
		{
			this.next = null; // for dummy
		}
	}
	public  class Linked
	{
		public node head; // dummy head
		int num;

		public Linked()
		{
			head = new node();
			num = 0;
		}

		public void insert(int index, node newone)
		{//index starts from zero
			if(index > num )
				return;
			node last = head;
			for(int i=0; i<index ; i++)
			{
				last = last.next;
			}
			newone.next = last.next;
			last.next = newone;
			num++;
		}

		public int find(char key2)
		{
			node last = head;
			for(int i = 0; i<= num ; i++)
			{
				if(last.key == key2)
					return  i-1; // dummy head
				last = last.next;
			}
			return -1;
		}

		public int[] findall(char key2)
		{
			int res[] = new int[num];
			int len = 0;
			node last = head;
			for(int i=0; i<=num; i++)
			{
				if(last.key == key2)
					res[len++] = i-1;
				last = last.next;
			}

			int[] f = new int[len];
			for(int i=0; i<len ; i++)
				f[i] = res[i];

			return f;
		}

		public void delete(int index)
		{
			if(num <= index)
				return;

			node last = head;
			for(int i = 0; i<index; i++)
			{
				last = last.next;
			}
			last.next = last.next.next;
			num--;
		}

		public char getchar(int index)
		{
			if(num <= index)
				return 0;
			else{
				node last = head;
				for(int i=0; i<index; i++)
				{
					last = last.next;
				}
				return  last.next.key;
			}
		}

	}
	public Linked string = new Linked();

	/* Constructor. */
	public LLString(String str)
	{
		for(int i =0; i<str.length(); i++)
		{
			string.insert(i, new node(str.charAt(i), null));
		}
	}

	/* Add methods here. */
	/* For example. */
	public char charAt(int index) throws IndexOutOfBoundsException
	{
		if(index >= string.num)
			throw new IndexOutOfBoundsException();
		else
			return string.getchar(index);
	}

	int compareTo(String anotherString)
	{
		return compareTo(new LLString(anotherString));
	}

	int compareTo(LLString anotherLLString)
	{
		int i = 0;

		while(i < string.num && i< anotherLLString.string.num) // different length?
		{
			if(string.getchar(i) - anotherLLString.string.getchar(i) == 0)
			{
				i++;
				continue;
			}

			return string.getchar(i) - anotherLLString.string.getchar(i);
		}

			return string.num - anotherLLString.string.num;

	}
	int compareToIgnoreCase(String str)
	{
		return compareToIgnoreCase(new LLString(str));
	}

	int compareToIgnoreCase(LLString llstr)
	{
		int i = 0;
		while(i < string.num) // different length?
		{
			if(Character.toUpperCase(string.getchar(i)) - Character.toUpperCase(llstr.string.getchar(i)) == 0 )
			{
				i++;
				continue;
			}

			return Character.toUpperCase(string.getchar(i)) - Character.toUpperCase(llstr.string.getchar(i));
		}
		return 0;

	}
	LLString concat(String str)
	{
		return concat(new LLString(str));
	}
	LLString concat(LLString llstr)
	{//linked list
		LLString res = new LLString(this.toString());
		for(int i=0; i<llstr.string.num; i++)
		{
			res.string.insert(res.string.num, new node(llstr.string.getchar(i), null) );
		}
		return res;
	}


	int indexOf(int ch)
	{
		return indexOf(ch,0);
	}

	int indexOf(int ch, int fromIndex)
	{
		LLString tempstring = this.substring(fromIndex);

		int res = tempstring.string.find((char)ch);

		if(res == -1)
			return res;
		else
			return fromIndex + res;
	}

	int indexOf(String str)
	{
		LLString temp = new LLString(str);
		for(int i=0; i<=string.num - temp.string.num; i++)
		{
			boolean flag = true;
			for(int j=0; j<temp.string.num; j++)
			{
				if(string.getchar(i+j) != temp.string.getchar(j))
					flag = false;
			}
			if(flag)
				return i;
		}
		return  -1;
	}

	int indexOf(String str, int fromIndex)
	{
		LLString temp = new LLString(str);

		for(int i=fromIndex; i<=string.num - temp.string.num; i++)
		{
			boolean flag = true;
			for(int j=0; j<temp.string.num; j++)
			{
				if(string.getchar(i+j) != temp.string.getchar(j))
					flag = false;
			}
			if(flag)
				return i;
		}
		return  -1;

	}

	public int length()
	{
		return string.num;
	}

	LLString replace(char oldChar, char newChar)
	{
		int[] index = string.findall(oldChar);
		LLString newone = new LLString(this.toString());

		for(int i = 0; i<index.length; i++)
		{
			newone.string.delete(index[i]);
			newone.string.insert(index[i], new node(newChar,null));
		}

		return newone;
	}

	public LLString substring(int beginIndex) throws IndexOutOfBoundsException
	{
		if(beginIndex < 0 || beginIndex > this.length())
			throw new IndexOutOfBoundsException();

		LLString newone = new LLString(this.toString());

		for(int i=0; i<beginIndex ; i++)
		{
			newone.string.delete(0);
		}
		return newone;
	}

	public LLString substring(int beginIndex, int endIndex) throws IndexOutOfBoundsException
	{
		if(beginIndex < 0 || endIndex > string.num || beginIndex > endIndex)
			throw new IndexOutOfBoundsException();

		LLString newone = new LLString(this.toString());

		int len = newone.string.num;
		for(int i = endIndex; i<len; i++) {
			newone.string.delete(endIndex);
		}
		for(int i=0 ; i<beginIndex; i++) {
			newone.string.delete(0);
		}
		return newone;
	}


	public String toString()
	{
		String res = "";
		for(int i=0; i<string.num; i++)
		{
			res += string.getchar(i);
		}
		return res;
	}

}
