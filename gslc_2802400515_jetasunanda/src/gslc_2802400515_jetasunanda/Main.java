/* 
 * Ini adalah program untuk menyimpan dan memodifikasi tanaman 
 * Terdapat 5 menu, yaitu:
 * 1. View plant: Melihat tanaman yang sudah disimpan baik dalam kategori All, Flower, Tree, dan Houseplant
 * 2. Insert plant: Memasukkan data tanaman baru
 * 3. Delete plant: Menghapus data tanaman yang diinginkan
 * 4. Water plants: Menyiram semua tanaman dan mengurangi waktu tumbuh nya (Growth time)
 * 5. Exit: Keluar dari program
 */

package gslc_2802400515_jetasunanda;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Plant> plant = new ArrayList<Plant>();

	public static void main(String[] args) {
		boolean statusRun = true;
		int userOption = 0;

		do {
			System.out.println("=============================");
			System.out.println("--Binus Plant Collections--");
			System.out.println("1. View Plants");
			System.out.println("2. Insert Plant");
			System.out.println("3. Delete Plant");
			System.out.println("4. Water Plants");
			System.out.println("5. Exit");
			System.out.print(">> ");

			// input dan validasi menu yang dipilih user
			try {
				userOption = scan.nextInt();
				scan.nextLine();

				if (userOption < 1 || userOption > 5) {
					throw new Exception("Please input a number between 1 - 5.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// press enter untuk melanjutkan ke menu lain
				System.out.print("Press enter to coninue...");
				scan.nextLine();
			}

			switch (userOption) {
			case 1:
				menu1();
				break;
			case 2:
				menu2();
				break;
			case 3:
				menu3();
				break;
			case 4:
				menu4();
				break;
			case 5:
				statusRun = false;
				break;
			default:
				break;
			}

		} while (statusRun);
		System.out.println("Program end, thank you :)");
	}

	// menu 1: melihat tanaman yang disimpan
	private static void menu1() {
		// deklarasi variabel untuk validasi
		String chooseType = null;
		boolean valid = false;

		// validasi input user sesuai dengan yang ditentukan
		do {
			try {
				System.out.print("Choose type (All/Flower/Tree/Houseplant): ");
				chooseType = scan.nextLine();

				if (!chooseType.equalsIgnoreCase("All") && !chooseType.equalsIgnoreCase("Flower")
						&& !chooseType.equalsIgnoreCase("Tree") && !chooseType.equalsIgnoreCase("Houseplant")) {
					throw new Exception(
							"Invalid plant type. Please insert between 'All', 'Flower', 'Tree', or 'Houseplant'");
				}

				valid = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!valid);
		valid = false;

		// print sesuai input user
		int i = 0;
		if (plant.isEmpty()) {
			System.out.println("No plants!");
		} else if (chooseType.equalsIgnoreCase("all")) {
			for (Plant temp : plant) {
				i++;
				System.out.printf("%02d. %s - %s - %s - mature", i, temp.plantType, temp.sciName, temp.name);
				if (temp.growthTime > 0) {
					System.out.println(" in " + temp.growthTime + " day(s)");
				} else {
					System.out.println();
				}
			}
		} else {
			for (Plant temp : plant) {
				if (temp.plantType.equalsIgnoreCase(chooseType)) {
					i++;
					System.out.printf("%02d. %s - %s - %s - mature", i, temp.plantType, temp.sciName, temp.name);
					if (temp.growthTime > 0) {
						System.out.println(" in " + temp.growthTime + " day(s)");
					} else {
						System.out.println();
					}
				}
			}
			// jika tanaman tidak ditemukan, print keterangan "No plants!"
			if (i == 0) {
				System.out.println("No plants!");
			}
		}

		// press enter untuk melanjutkan ke menu lain
		System.out.print("Press enter to coninue...");
		scan.nextLine();
	}

	// menu 2: menambahkan plant
	private static void menu2() {
		// Deklarasi variable untuk menyimpan data agar dapat dimasukkan ke dalam
		String plantType = null, sciName = null, name = null;
		Integer growthTime = 0;
		// Deklarasi variable untuk validasi
		boolean valid = false;

		// Input & validasi plant type
		do {
			try {
				System.out.print("Type (Flower/Tree/Houseplant): ");
				plantType = scan.nextLine();

				if (!plantType.equals("Flower") && !plantType.equals("Tree") && !plantType.equals("Houseplant")) {
					throw new Exception("Insert the right type (Case sensitive)");
				}

				valid = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!valid);
		valid = false;

		// Input & validasi scientific plant name
		do {
			try {
				System.out.print("Scientific name (5-30 characters): ");
				sciName = scan.nextLine();

				if (sciName.length() < 5 || sciName.length() > 30) {
					throw new Exception("Insert scientific name with length 5 - 30 characters.");
				}

				valid = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!valid);
		valid = false;

		// Input & validasi plant name
		do {
			try {
				System.out.printf("Name (end with %s): ", plantType);
				name = scan.nextLine();

				String[] words = name.split("\\s+");

				if (words.length < 2) {
					throw new Exception("Name must consist of 2 or more words.");
				}

				if (!name.endsWith(plantType)) {
					throw new Exception("Name must end with '" + plantType + "' (case sensitive)");
				}

				valid = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!valid);
		valid = false;

		// Input & validasi growth time
		do {
			try {
				System.out.print("Growth time (1-10 day(s)): ");
				growthTime = scan.nextInt();
				scan.nextLine();

				if (growthTime < 1 || growthTime > 10) {
					throw new Exception("Growth time should be between 1 - 10 days.");
				}

				valid = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!valid);

		// Memasukkan data ke dalam array plant
		Plant temp = new Plant();
		temp.plantType = plantType;
		temp.sciName = sciName;
		temp.name = name;
		temp.growthTime = growthTime;
		plant.add(temp);
		System.out.println("Plant successfully inserted!");

		// press enter untuk lanjut ke menu lainnya
		System.out.print("Press enter to continue...");
		scan.nextLine();
	}

	// menu 3: hapus tanaman dari daftar plant
	private static void menu3() {
		// validasi apakah data ada
		if (plant.isEmpty()) {
			System.out.println("No plants!");
		} else {
			// deklarasi variable untuk validasi dan menyimpan index yang akan dihapus
			int i = 0;
			int idxChoose = 0;
			boolean valid = false;

			// print semua data plant
			for (Plant temp : plant) {
				i++;
				System.out.printf("%02d. %s - %s - %s - mature", i, temp.plantType, temp.sciName, temp.name);
				if (temp.growthTime > 0) {
					System.out.println(" in " + temp.growthTime + " day(s)");
				} else {
					System.out.println();
				}
			}

			// validasi input
			// jika input tidak di luar dari range tanaman, maka user akan diminta
			// memasukkan ulang input yang sesuai dengan range index yang tersedia
			do {
				try {
					System.out.print("Choose plant to delete (1 - " + i + "): ");
					idxChoose = scan.nextInt();
					scan.nextLine();

					if (idxChoose < 1 || idxChoose > i) {
						throw new Exception("Select a plant from the available range 1 - " + i + ".");
					}

					valid = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (!valid);

			// remove plant sesuai input user
			plant.remove(idxChoose - 1);
			System.out.println("Plant is removed successfully!");
		}

		// press enter untuk lanjut ke menu lainnya
		System.out.print("Press enter to continue...");
		scan.nextLine();
	}

	// menu 4: untuk menyiram tanaman dan mengurangi growth time sebanyak 1 pada
	// masing-masing tanaman
	private static void menu4() {
		int i = 0;

		// validasi apakah ada tanaman
		if (plant.isEmpty()) {
			System.out.println("No plants!");
		} else {
			for (Plant temp : plant) {
				// kurangi growth time pada tanaman saat ini
				temp.reduceGrowthTime();
				i++;

				// print data tanaman saat ini setelah dikurangi growth timenya
				System.out.printf("%02d. %s - %s - %s - mature", i, temp.plantType, temp.sciName, temp.name);
				if (temp.growthTime > 0) {
					System.out.println(" in " + temp.growthTime + " day(s)");
				} else {
					System.out.println();
				}
			}
			System.out.println("Finish watering!");
		}
		// press enter untuk lanjut ke menu lainnya
		System.out.print("Press enter to continue...");
		scan.nextLine();
	}

}
