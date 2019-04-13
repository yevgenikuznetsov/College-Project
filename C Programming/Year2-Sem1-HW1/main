/*
 * main.c
 *
 *  Created on: Nov 16, 2018
 *      Author: yevgeni
 */

#include "pictureManipulation.h"
#include "function.h"
#include <stdio.h>
#include <ctype.h>

int main() {
	srand(time(0));
	char ch;
	int nextGame;

	do {

		menu();
		ch = getCharFromPerson();

		switch (tolower(ch)) {
		case ('p'):
		/* start to play first game*/
			startPicture();
			break;
		case ('n'):
		/* start to play second game*/
			createMatToForTheGame();
			break;
		case ('e'):
		/* Exit*/
			printf("Bye Bye");
			nextGame = 1;
			break;
		}
		printf("\n");
	} while (nextGame != 1);
	return 0;
}

void menu() {
	printf("Please choose one of the following options\n");
	printf("P/p - Picture Manipulation\n");
	printf("N/n - Number Game\n");
	printf("E/e - Quit\n");
	printf("Your choose:");
}
