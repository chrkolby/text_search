import java.util.*;
import java.io.*;
import java.lang.*;

class Find{

    char[] needle;
    char[] haystack;
    boolean wildCard = false;
    int offput = 0;
    int charMax = 256;

    Find(String n, String h){
	needle = n.toCharArray();
	haystack = h.toCharArray();
	checkForWild();
	algo();
    }

    void checkForWild(){

	int temp = 0;

	for(int i = 0; i < needle.length; i++){

	    if(needle[i] == '_'){

		wildCard = true;

	       
		temp = needle.length-(i+1);
		if(temp == 0)
		    temp = 1;
		

		if(offput > temp){
		    offput = temp;
		    temp = offput;
		}
	    }
	    offput = temp;
	}
    }

    void algo(){

	int[] badShift = new int[charMax];

	if(wildCard == true){

	    for(int i = 0; i < charMax; i++){
		badShift[i] = offput;
	    }
	}

	else{

	    for(int i = 0; i < charMax; i++){
		badShift[i] = needle.length;
	    }
	}
	    	

	int offset = 0;
	int scan = 0;
	int last = needle.length - 1;
	int maxoffset = haystack.length - needle.length;
	int i = 0;    
	
	for(int l = 0; l<last; l++){

	    badShift[needle[l]] = last-l;
	} 

	HashMap<Integer, String> t = new HashMap<Integer, String>();

		
	while(offset <= maxoffset){

	    boolean temp = false;
	    scan = last;
	    char[] tempWord = new char[needle.length];

	    while(needle[scan] == haystack[scan+offset] || needle[scan] == '_'){

		if(scan == 0){

		    for(int x = 0; x<tempWord.length; x++){ 
			tempWord[scan+x] = haystack[(scan+x)+offset];
		    }
		    String a = new String(tempWord);
		    t.put(offset,a);
		    temp = true;
		    offset++;
		    break;
		}

		if(offset > maxoffset){
		    break;
		}
		scan--;
	
	    }
	    if(temp == false)
		offset += badShift[haystack[offset+last]];
	}
	try {
	    File fileout = new File("utskrift.txt");
	    FileWriter fwriter = new FileWriter(fileout, true);
	    PrintWriter pwriter = new PrintWriter(fwriter);

	    System.out.println("For needle: " + new String(needle) + " and haystack: " + new String(haystack) + " the output is: ");
	    pwriter.println("For needle: " + new String(needle) + " and haystack: " + new String(haystack) + " the output is: ");
	    
	    for(Map.Entry<Integer, String>entry : t.entrySet()){
	
		System.out.println("Index = " + entry.getKey() + " Word = " + entry.getValue());
		pwriter.println("Index = " + entry.getKey() + " Word = " + entry.getValue());
	    }
	    pwriter.close();
	}
	catch(IOException e){
	    System.out.println(e);
	}
    }
}
