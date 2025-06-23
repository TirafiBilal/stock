package stock;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class stock {
	int p_id, quantity;
	String name;
	double price;

	public stock(int p_id, int quantity, String name, double price) {
		this.p_id = p_id;
		this.quantity = quantity;
		this.name = name;
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void show() {
		System.out.print("\nproduct id : " + p_id);
		System.out.print("\nquantity : " + quantity);
		System.out.print("\nname : " + name);
		System.out.print("\nprice : " + price + " DH");
		System.out.print("\n------------------");
	}

	public String toFileString() {
		return p_id + "," + quantity + "," + name + "," + price;
	}

	public static stock fromFileString(String line) {
		String[] parts = line.split(",");
		int p_id = Integer.parseInt(parts[0]);
		int quantity = Integer.parseInt(parts[1]);
		String name = parts[2];
		double price = Double.parseDouble(parts[3]);
		return new stock(p_id, quantity, name, price);
	}

	public static void saveToFile(ArrayList<stock> products) {
		try {
			PrintWriter writer = new PrintWriter("stock.txt");
			for (stock p : products) {
				writer.println(p.toFileString());
			}
			writer.close();
			System.out.println("\nProducts saved to file.");
		} catch (Exception e) {
			System.out.println("Error saving file: " + e.getMessage());
		}
	}

	public static ArrayList<stock> loadFromFile() {
		ArrayList<stock> list = new ArrayList<>();
		try {
			File file = new File("stock.txt");
			if (!file.exists()) return list;

			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				list.add(fromFileString(line));
			}
			fileReader.close();
			System.out.println("Products loaded from file.");
		} catch (Exception e) {
			System.out.println("Error loading file: " + e.getMessage());
		}
		return list;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<stock> products = loadFromFile();

		int option;
		int count = products.size();
		int p_id = count > 0 ? products.get(count - 1).p_id + 1 : 1;
		int quantity = 0;
		boolean menu = true;

		while (menu) {
			System.out.print("\n1- add new product");
			System.out.print("\n2- update quantity");
			System.out.print("\n3- show all products");
			System.out.print("\n4- show low quantity products");
			System.out.print("\n5- exit and save");
			System.out.print("\nChoose an option: ");
			option = scan.nextInt();
			scan.nextLine();

			switch (option) {
				case 1:
					System.out.print("\nname : ");
					String name = scan.nextLine();
					System.out.print("\nquantity : ");
					quantity = scan.nextInt();
					scan.nextLine();
					System.out.print("\nprice : ");
					double price = scan.nextDouble();
					products.add(count, new stock(p_id, quantity, name, price));
					count++;
					p_id++;
					break;

				case 2:
					if (count == 0) {
						System.out.print("\nthere is no product");
					} else {
						System.out.print("\nenter the product's id to update: ");
						int id_up = scan.nextInt();
						System.out.print("\nthe new quantity: ");
						int n_quantity = scan.nextInt();
						for (int i = 0; i < count; i++) {
							if (id_up == products.get(i).p_id) {
								products.get(i).setQuantity(n_quantity);
							}
						}
					}
					break;

				case 3:
					if (count == 0) {
						System.out.print("\nthere is no product");
					} else {
						for (int i = 0; i < count; i++) {
							products.get(i).show();
						}
					}
					break;

				case 4:
					if (count == 0) {
						System.out.print("\nthere is no product");
					} else {
						for (int i = 0; i < count; i++) {
							if (products.get(i).quantity < 10) {
								products.get(i).show();
							}
						}
					}
					break;

				case 5:
					saveToFile(products);
					menu = false;
					break;
			}
		}
	}
}
