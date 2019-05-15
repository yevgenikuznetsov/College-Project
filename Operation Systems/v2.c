/*
 * v2.c
 *
 *  Created on: May 12, 2019
 *      Author: yevgeni
 */

#include "hw1.h"
#include <stdlib.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
	int** sudoku;
	array array_sukuko;

	if (argc == 1) {
		sudoku = createMatrix();

		with_mmap(sudoku, "your suduko");

	} else {

		int i;
		for (i = 1; i < argc; i++) {

			sudoku = read_From_File(argv[i]);

			with_mmap(sudoku, argv[i]);

		}

	}

	return 1;
}
