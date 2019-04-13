/*
 * pictureManipulation.c
 *
 *  Created on: Nov 16, 2018
 *      Author: yevgeni
 */

#include "pictureManipulation.h"
#include "function.h"
#include <stdio.h>

void startPicture() {

	int size = metrixSize();
	int matrix[size][size];

	initRandomArr(matrix, size);
	PrintMat(matrix, size, size);

	choice(matrix, size, size);

}

void introduction() {
	printf("\nPlease choose one of the following option\n");
	printf(" 1 - 90 degree clockwise\n");
	printf(" 2 - 90 degree counter clockwise\n");
	printf(" 3 - Flip Horizontal\n");
	printf(" 4 - Flip Vertical\n");
	printf("-1 - Quit\n");
}

void choice(int* mat, int rows, int cols) {
	int numberOfChoice = 0;


	do {
		introduction();
		printf("\Your choose:");
		numberOfChoice = getNumber();

		switch (numberOfChoice) {
		case 1:
			printf("\n--------- picture after manipulation ---------\n");
			CLockWise(mat, rows);
			PrintMat(mat, rows, cols);
			break;
		case 2:
			printf("\n--------- picture after manipulation ---------\n");
			CounterClockWise(mat, rows);
			PrintMat(mat, rows, cols);
			break;
		case 3:
			printf("\n--------- picture after manipulation ---------\n");
			flipHorizonatal(mat, rows);
			PrintMat(mat, rows, cols);
			break;
		case 4:
			printf("\n--------- picture after manipulation ---------\n");
			flipVertical(mat, rows);
			PrintMat(mat, rows, cols);
			break;
		case -1:
			getchar();
			break;
		}
	} while (numberOfChoice != -1);

}

