package br.edu.univas.controller;

import java.util.Scanner;

import br.edu.univas.dao.PetDAO;
import br.edu.univas.view.PetView;
import br.edu.univas.vo.Pet;

public class PetController {

	private PetDAO dao;
	private PetView view;
	
	public PetController() {
		dao = new PetDAO();
		view = new PetView();
	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		int option = 0;
		
		do {
			view.showMenu();
			option = scanner.nextInt();
			scanner.nextLine();
			
			if (option == 1) {
				newPet(scanner);
			} else if (option == 2) {
				list();
			} else if (option != 9) {
				view.showInvalidOption();
			}
			
		} while(option != 9);
		
		scanner.close();
	}
	
	private void newPet(Scanner scanner) {
		view.showOwnerName();
		String ownerName = scanner.nextLine();
		view.showCellPhone();
		String cellPhone = scanner.nextLine();
		view.showName();
		String name = scanner.nextLine();
		view.showBreed();
		String breed = scanner.nextLine();
		
		Pet pet = new Pet.Builder()
				.withOwnerName(ownerName)
				.withCellPhone(cellPhone)
				.withBreed(breed)
				.withName(name)
				.build();
		dao.insert(pet);
		
		view.showSuccessMessage();
	}
	
	private void list() {
		view.showAllPets(dao.list());
	}

	
}
