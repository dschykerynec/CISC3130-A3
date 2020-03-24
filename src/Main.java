import java.io.*;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		
		File file = new File("../movieFiles/movies.csv");
		BufferedReader in = new BufferedReader(new FileReader(file));
		PrintWriter out = new PrintWriter(new File("../outputFile.txt"));
		
		MovieBST films = new MovieBST();
		
		readFile(in, films);
		out.println(films.display());
		out.println("-----------------------------------------------");
		out.flush();
		
		out.println(films.subset("Nixon", "Young guns"));
		out.println("-----------------------------------------------");
		
		out.println(films.subset("Superman", "Supernova"));
		out.println("-----------------------------------------------");
		
		out.println(films.subset("100", "102"));
		out.println("-----------------------------------------------");
		
		out.flush();
		out.close();
	}
	public static void readFile(BufferedReader in, MovieBST films) throws IOException
	{
		in.readLine();    // clears column titles from buffer
		
		// variables to be used in processing each line of data; each line is 1 movie
		String line[];
		int id, year, n;
		String temp, title, yearString;
		String[] genres = new String[10];	// assumes no movie will have more than 10 genres
							
		while (in.ready())
		{
			line = in.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			id = Integer.parseInt(line[0]);
			
			// takes care of inconsistencies in the format of the data
			// eg: extra spaces, quotation marks, no dates,
			temp = line[1].replace("\"", "").trim();
			if (temp.charAt(temp.length()-1) != ')')
				temp += " (9999)";
			
			n = temp.length();
			yearString = temp.substring(n-5, n-1);	// a string representing the year
			year = Integer.parseInt(yearString);
			title = temp.substring(0, n-7); // a string representing the title without the year
			
			genres = line[2].split("\\|");
			
			films.insert(title, year, genres, id);
		}
	}

}
