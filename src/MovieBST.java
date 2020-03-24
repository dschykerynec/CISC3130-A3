
public class MovieBST {

	private Movie root;

	public class Movie
	{
		private String title;
		private int year;
		private String[] genres;
		private int id;

		private Movie left;
		private Movie right;

		public Movie(String title, int year, String[] genres, int id)
		{
			this.title = title;
			this.year = year;
			this.genres = genres.clone();
			this.id = id;

			left = null;
			right = null;
		}

		public int compareTo(Movie flick)
		{
			return this.title.compareToIgnoreCase(flick.title);
		}
		public int compareTo(String title)
		{
			return this.title.compareToIgnoreCase(title);
		}
	}

	public MovieBST()
	{
		root = null;
	}

	public Movie find(String title)
	{
		Movie current = root;

		while (!current.title.equalsIgnoreCase(title))	// while current title != argument
		{
			if (current.title.compareToIgnoreCase(title) > 0)	// current comes after argument
				current = current.left;

			else	// current comes before argument
				current = current.right;

			if (current == null)	// title not found
				return null;
		}
		return current;
	}

	public void insert(String title, int year, String[] genres, int id)
	{
		Movie flick = new Movie(title, year, genres, id);

		if (root == null)	// if tree is empty
			root = flick;
		else	// if tree is not empty
		{
			Movie current = root;
			Movie parent;

			while (true)	// exits internally once flick is inserted
			{
				parent = current;

				if (current.compareTo(flick) > 0)	// if current comes after flick
				{
					current = current.left;

					if (current == null)
					{
						parent.left = flick;
						return;
					}
				}
				else	// if current comes before flick
				{
					current = current.right;

					if (current == null)
					{
						parent.right = flick;
						return;
					}
				}
			}
		}
	} 

	public void delete(String title)
	{

	}

	private String subset(Movie movie, String low, String high) {

		if (root == null)
			return "";

		String str = "";

		if (movie != null)
		{

			if (movie.compareTo(low) > 0)
				str += subset(movie.left, low, high);

			if (movie.compareTo(low) >= 0 && movie.compareTo(high) <= 0)
				str += movie.title + "\n";

			if (movie.compareTo(high) < 0)
				str += subset(movie.right, low, high);
		}

		return str;
	}

	// internal recursive method to display titles in alphabetic order
	private String display(Movie node)
	{
		String str = "";

		if (node != null)
		{

			str += display(node.left);
			
			str += "Title:  " + node.title + "\n";
			str += "Year:   " + node.year + "\n";
			str += "Genres: ";
			for (String e: node.genres) str += e + ", ";
			str += "\n\n";
			
			str += display(node.right);

		}
		return str;
	}

	// external method to be called by the user
	public String display() { return display(root); }
	public String subset(String low, String high) { return subset(root, low, high); }











}
