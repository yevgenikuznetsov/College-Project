/*
 * function.c
 *
 *  Created on: Nov 16, 2018
 *      Author: yevgeni
 */

#include "function.h"
#include <stdlib.h>
#include <time.h>
#include "numberGame.h"
#include "function.h"

/*rotate matrix to 90 degree counter clockwise */
void CounterClockWise(int* mat, int size) {
	int i, j;

	for (i = 0; i < size / NUMBERTODIVIDE; i++) {
		for (j = i; j < size - i - 1; j++) {

			swapForRound(mat + i * size + j, mat + j * size + size - 1 - i,
					mat + (size - 1 - i) * size + size - 1 - j,
					mat + (size - 1 - j) * size);

		}

	}

}
/*rotate matrix to 90 degree clockwise */
void CLockWise(int* mat, int size) {
	int i, j;
	for (i = 0; i < size / NUMBERTODIVIDE; i++) {
		for (j = i; j < size - i - 1; j++) {

			swapForRound(mat + i * size + j, mat + (size - 1 - j) * size + i,
					mat + (size - 1 - i) * size + size - 1 - j,
					mat + j * size + size - 1 - i);

		}
	}
}

/*flip horizontal matrix */
void flipHorizonatal(int* mat, int size) {
	int i, j;

	for (i = 0; i < size / NUMBERTODIVIDE; i++) {
		for (j = 0; j < size; j++) {

			swap(mat + i * size + j, mat + (size - 1 - i) * size + j);
		}
	}
}

/*flip vertical matrix */
void flipVertical(int* mat, int size) {
	int i, j;
	for (i = 0; i < size; i++) {
		for (j = 0; j < size / NUMBERTODIVIDE; j++) {
			swap(mat + i * size + j, mat + i * size + size - 1 - j);

		}
	}
}
/*print matrix */
void PrintMat(int* mat, int rows, int cols) {
	int i, j;
	for (i = 0; i < rows; i++) {
		for (j = 0; j < cols; j++) {
			printf("%5d", *mat);
			mat++;
		}
		printf("\n");
	}

}
/*create random numbers in matrix*/
void initRandomArr(int* mat, int size) {

	int i, j;
	for (i = 0; i < size; i++) {
		for (j = 0; j < size; j++) {
			*mat = MIN + (rand() % (MAX - MIN + 1));
			mat++;
		}
	}
}
/*create new matrix with ascending order number */
/*set zero in last index */
void createMat(int* mat, int rows, int cols) {
	int i;
	int count = 0;

	for (i = 0; i < rows * cols; i++) {
		if (i == 0) {
			*(mat + rows * cols - 1) = 0;
		} else {
			*(mat + i - 1) = count;
		}

		count++;

	}

}
/*mixing numbers in a matrix */
void randomMAt(int* mat, int rows, int colums) {

	int i, rows1, colums1, colums2, rows2;

	for (i = MINFORRANDOM; i < MAXNUMBEROFTIME; i++) {

		rows1 = MINFORRANDOM + (rand() % (rows - 1));
		colums1 = MINFORRANDOM + (rand() % (colums));

		rows2 = MINFORRANDOM + (rand() % (rows - 1));
		colums2 = MINFORRANDOM + (rand() % (colums));

		swap(mat + rows1 * rows + colums1, mat + rows2 * rows + colums2);

	}

}

/*find index by number*/
int indexInMat(int* mat, int rows, int cols, int number) {
	int i;

	for (i = 0; i < cols * rows; i++) {
		if (*(mat + i) == number) {
			return i;
		}
	}
	return -1;

}

/*check if number in matrix is ordered in ascending order */
int checkMat(int* mat, int row, int cols) {
	int i;
	if (*(mat + row * cols - 1) != 0) {
		return -1;
	}

	for (i = 0; i < row * cols - 2; i++) {
		if (*(mat + i) > *(mat + i + 1))
			return -1;
	}
	return 0;

}

/*check if the selected number is near the empty space */
/*if the number is near - do swap with the empty space */
int moveIndex(int* mat, int row, int col, int currentEmptyIndex,
		int playerIndex) {

	if (playerIndex + col == currentEmptyIndex) {

		swap(mat + currentEmptyIndex, mat + playerIndex);
		return 1;

	} else if (playerIndex == currentEmptyIndex - 1) {

		swap(mat + currentEmptyIndex, mat + playerIndex);
		return 1;

	} else if (playerIndex - col == currentEmptyIndex) {

		swap(mat + currentEmptyIndex, mat + playerIndex);
		return 1;

	} else if (playerIndex - 1 == currentEmptyIndex) {

		swap(mat + currentEmptyIndex, mat + playerIndex);
		return 1;

	} else {
		return -1;
	}
}

/*swap to number  */
void swap(int* source, int* change) {
	int temp;
	int *addrestemp;
	temp = *(source);

	addrestemp = source;
	*(addrestemp) = *(change);

	addrestemp = change;
	*(addrestemp) = temp;

}

/*swap 4 numbers in matrix */
void swapForRound(int* place1, int* place2, int* place3, int* place4) {
	int temp;
	int *tempAddr;

	temp = *(place1);

	tempAddr = place1;
	*tempAddr = *(place2);

	tempAddr = place2;
	*tempAddr = *(place3);

	tempAddr = place3;
	*tempAddr = *(place4);

	tempAddr = place4;
	*tempAddr = temp;
}


int getRandomNumber() {
	return MINTORANDOM + (rand() % (MAXTORANDOM - 1));
}

/*random number for matrix size */
int metrixSize() {

	int num = MINTORANDOM + rand() % (MAXTORANDOM - 1);
	return num;

}
/*get char from player for main menu */
char getCharFromPerson() {
	char c;
	scanf("%c", &c);

	return c;
}
/*get number from player for second menu */
int getNumber() {
	int num;
	scanf("%d", &num);
	return num;
}

