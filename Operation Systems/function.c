/*
 * function.c
 *
 *  Created on: May 3, 2019
 *      Author: yevgeni
 */

#include "function.h"
#include <stdlib.h>
#include <stdio.h>

int CheckRow(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray(array);

	int i, j, num;

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			num = Matrix[i][j];
			if (array[num - 1] == 1) {
				return 0;
			}
			array[num - 1]++;
		}
		initialArray(array);
	}
	free(array);
	return 1;
}

int CheckCol(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray(array);

	int i, j, num;

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			num = Matrix[j][i];
			if (array[num - 1] == 1) {
				return 0;
			}
			array[num - 1]++;
		}
		initialArray(array);
	}
	free(array);
	return 1;
}

int CheckBox(int** Matrix) {
	int* array = (int*) malloc(sizeof(int) * 9);
	if (!array) {
		printf("Failed");
		return 0;
	}
	initialArray(array);

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
			initialArray(array);
			h = h + 2;
		}
		k = k + 2;
	}
	free(array);

	return 1;
}

void initialArray(int* array) {
	int i;
	for (i = 0; i < 9; i++) {
		array[i] = 0;
	}
}
