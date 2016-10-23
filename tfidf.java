import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class tfidf
{
	public static void main(String arg[])
	{
		int nodoc;
		nodoc=Integer.parseInt(JOptionPane.showInputDialog("Enter number of document:"));
		String[] docs=new String[nodoc];
		for(int i=0;i<nodoc;i++)
		{
			docs[i]=JOptionPane.showInputDialog("Enter string for document"+(i+1));
		}
		System.out.println("Documents are:");
		for(int i=0;i<nodoc;i++)
			System.out.println("[Doc "+(i+1)+"]:"+docs[i]);
		String terms=JOptionPane.showInputDialog("Enter term:");
		System.out.println("  Doc\t  Term\tTFvalue       IDFvalue     TF*IDFvalue");
		for(String term:terms.split(" "))
		{

			double idfval=idf(nodoc,docs,term);
			for(int i=0;i<nodoc;i++)
			{
				double tfval=tf(docs[i],term);
				System.out.println("[Doc"+(i+1)+"]  "+term+"  \t"+new DecimalFormat("##.##").format(tfval)+" \t"+new DecimalFormat("##.##").format(idfval)+" \t"+new DecimalFormat("##.##").format(tfval*idfval));
			}
		}
	}
	public static double idf(int nodoc,String[] docs,String term)
	{
		double dwt=0;
		for(String doc:docs)
		{
			if(doc.contains(term))
				dwt++;
		}
		return (1+Math.log(((double)nodoc/dwt)));
	}
	public static double tf(String doc,String term)
	{
		HashMap<String,Integer> hmap=new HashMap<String,Integer>();
		int total=0,tcount=0;
		for(String word:doc.split(" "))
		{
			total++;
			if(hmap.containsKey(word))
			{
				hmap.put(word,hmap.get(word)+1);
			}				
			else
			{
				hmap.put(word,1);
			}
		}
		if(hmap.containsKey(term))
		{
			tcount=hmap.get(term);
			return ((double)tcount/total);
		}
		else
		{
		return 0;
		}
	}
}
