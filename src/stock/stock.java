package stock;
import java.util.Scanner;
import java.util.ArrayList;

public class stock {
	int p_id,quantity;
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
		System.out.print("\nproduct id : "+p_id);
		System.out.print("\nquantity : "+quantity);
		System.out.print("\nname : "+name);
		System.out.print("\nprice : "+price+" DH");
		System.out.print("\n------------------");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<stock> products = new ArrayList<>();
		int option ;
		int count = 0;
		int p_id = 1;
		int computer_q = 0;
		int phone_q = 0;
		int tv_q = 0;
		int laptop_q = 0;
		
		boolean menu = true;
		while(menu) {
			System.out.print("\n 1- add new product");
			System.out.print("\n 2- update quantity");
			System.out.print("\n 3- show all products");
			System.out.print("\n 4- show low quantity products");
			option = scan.nextInt();
			scan.nextLine();
			int quantity = 0;
		switch(option) {
		case 1:
			System.out.print("\nname : ");
			String name = scan.nextLine();
			switch(name) {
			case "computer":
				quantity = computer_q + 1;
				break;
			case "phone":
				quantity =  phone_q+1;
				break;
			case "tv":
				quantity =  tv_q+1;
				break;
			case "laptop":
				quantity = laptop_q+1;
				break;	
			}
			System.out.print("\nprice : ");
			double price = scan.nextDouble();
			products.add(count,new stock(p_id,quantity,name,price));
			count++;
			p_id++;
			break;
		case 2:
			if(count == 0) {
				System.out.print("\nthere is no product");
			}else {
				System.out.print("\nenter the product's id to update");
				int id_up = scan.nextInt();
				System.out.print("\n the new quantity : ");
				int n_quantity = scan.nextInt();
				for(int i=0;i<count;i++) {
					if(id_up == products.get(i).p_id) {
						products.get(i).setQuantity(n_quantity);
					}
				}
			}
			break;
		case 3:
			if(count == 0) {
				System.out.print("\nthere is no product");
			}else {
				for(int i=0;i<count;i++) {
					products.get(i).show();
				}
			}
		}
		}

	}


}
