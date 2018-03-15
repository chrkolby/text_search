import java.util.*;
import java.io.*;
import java.lang.*;

class Textsearch {

    public static void main(String[]args){

	if(args.length != 2){
	    System.out.println("Error: Make sure you specify the location of the needle and the haystack. ");
	}

	else{

	    String needle = args[0];
	    String haystack = args[1];

	    try{

		Scanner scneed = new Scanner(new File(needle));
		Scanner schay = new Scanner(new File(haystack));

		String n = "";

		try{
		    File fileout = new File("Errors.txt");
		    FileWriter fwriter = new FileWriter(fileout, true);
		    PrintWriter pwriter = new PrintWriter(fwriter);

		    if(scneed.hasNext()){
			n = scneed.next();
		    }

		    else{

			System.out.println("Error: The needle is empty, please specify what word you want to search for.");
			pwriter.println("Error: The needle is empty, please specify what word you want to search for."); 
			pwriter.close();
			System.exit(1);
		    }
		    String h = "";

		    while(schay.hasNext()){

			h = h + schay.nextLine();

		    }

		    if(h.equals("")){
			System.out.println("Error: The haystack is empty, please specify what text you want to search through.");
			pwriter.println("Error: The haystack is empty, please specify what text you want to search through.");
			pwriter.close();
			System.exit(1);
		    }

		    if(n.length()>h.length()){

			System.out.println("Error: The needle is longer than the haystack.");
			pwriter.println("Error: The needle is longer than the haystack.");
			pwriter.close();
			System.exit(1);
		    }
		    pwriter.close();

		    Find f = new Find(n,h);


		}
		catch(IOException e){

		    System.out.println(e);
		}

	    }

	    catch(FileNotFoundException e){
		System.out.println("File not found");
		System.exit(1);
	    }
	}
    }
}
