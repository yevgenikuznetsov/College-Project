/*
 * numberGame.c
 *
 *  Created on: Nov 17, 2018
 *      Author: yevgeni
 */

#include "numberGame.h"
#include "function.h"
#include <stdio.h>

void createMatToForTheGame() {
	int rows = getRandomNumber();
	int cols = getRandomNumber();
	int mat[rows][cols];

	createMat(mat, rows, cols);
	randomMAt(mat, rows, cols);

	theGame(mat, rows, cols);
}

void theGame(int*mat, int rows, int cols) {
	int choice, currentEmptyIndex, playerIndex, move, order;

	do {
		PrintMat(mat, rows, cols);
		currentEmptyIndex = indexInMat(mat, rows, cols, 0);

		do {
			printf("\nYour step: ");
			choice = getNumber();
			playerIndex = indexInMat(mat, rows, cols, choice);

			move = moveIndex(mat, rows, cols, currentEmptyIndex, playerIndex);
/*check if the player number is invalid */
			if (playerIndex == -1 || move == -1) {
				printf("Invalid step!");
			}

		} while (playerIndex == -1 || move == -1);

		order = checkMat(mat, rows, cols);
	} while (order != 0);
	printf("You win! The game is over!\n");
	getchar();

}

