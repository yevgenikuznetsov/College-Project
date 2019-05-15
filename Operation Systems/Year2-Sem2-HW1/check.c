/*
 * check.c
 *
 *  Created on: May 11, 2019
 *      Author: yevgeni
 */

#include "check.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char **argv) {
	int res;

	int fdp_father = atoi(argv[3]);
	int sudoku_array[81];

	read(fdp_father, sudoku_array, sizeof(sudoku_array) + 1);

	int** s = convert_to_matrix_child(sudoku_array);

	int fdp_child = atoi(argv[1]);
	char* ch = argv[2];

	if (strcmp("c", ch) == 0) {
		res = CheckCol_child(s);
		write(fdp_child, &res, sizeof(int));
		close(fdp_child);

	} else if (strcmp("r", ch) == 0) {
		res = CheckRow_child(s);
		write(fdp_child, &res, sizeof(int));
		close(fdp_child);

	} else if (strcmp("g", ch) == 0) {
		res = CheckBox_child(s);
		write(fdp_child, &res, sizeof(int));
		close(fdp_child);

	} else {
		perror("Failed");
	}

	return 1;
}

int CheckBox_child(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray_child(array);

	int i, j, k, h, num;

	for (k = 0; k < 9; k++) {
		for (h = 0; h < 9; h++) {
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					num = Matrix[i + k][j + h];
					if (array[num - 1] == 1) {
						return 0;
					}
					array[num - 1]++;
				}
			}
			initialArray_child(array);
			h = h + 2;
		}
		k = k + 2;
	}
	free(array);

	return 1;
}

int CheckRow_child(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray_child(array);

	int i, j, num;

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			num = Matrix[i][j];
			if (array[num - 1] == 1) {
				return 0;
			}
			array[num - 1]++;
		}
		initialArray_child(array);
	}
	free(array);
	return 1;
}

int** convert_to_matrix_child(int array[81]) {
	int i, j;
	int** sudoku = (int**) malloc(sizeof(int*) * 9);

	for (i = 0; i < 9; i++) {
		sudoku[i] = (int*) malloc(sizeof(int) * 9);
		for (j = 0; j < 9; j++) {
			sudoku[i][j] = *(array + i * 9 + j);
		}
	}

	return sudoku;

}

int CheckCol_child(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray_child(array);

	int i, j, num;

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			num = Matrix[j][i];
			if (array[num - 1] == 1) {
				return 0;
			}
			array[num - 1]++;
		}
		initialArray_child(array);
	}
	free(array);

	return 1;
}

void initialArray_child(int* array) {
	int i;
	for (i = 0; i < 9; i++) {
		array[i] = 0;
	}
}
