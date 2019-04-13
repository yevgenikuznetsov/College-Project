/*
 * generalfuc.c
 *
 *  Created on: Dec 25, 2018
 *      Author: yevgeni
 */

#include "generalfuc.h"
#include <stdio.h>

int menu() {

	int userChoice;
	printMenu();

	scanf("%d" , &userChoice);
	getchar();

	return userChoice ;
}

void printMenu() {
	printf("\nSelect:\n"
			"1. Read City information from file.\n"
			"2. Show all Kindergartens.\n"
			"3. Show a specific Kindergarten.\n"
			"4. Save City information to file.\n"
			"5. Add a Kindergarten.\n"
			"6. Add a Child.\n"
			"7. Birthday to a Child.\n"
			"8. Count Hova childres.\n"
			"0. Exit.\n");

}

